package framework.taglib.file;

import framework.ressource.util.UtilRequest;
import framework.ressource.util.UtilSafe;
import framework.ressource.util.UtilString;
import framework.taglib.file.bean.BeanFTP;
import framework.taglib.file.bean.BeanFTPAddress;
import framework.taglib.file.bean.BeanFile;
import framework.trace.Trace;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URLEncoder;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * @author  HP_Administrateur
 */
public class TagFileReader extends BodyTagSupport {

  public static final String ENCODING_TYPE_HTML_SPECIAL_CHAR = "HTMLSPECIALCHAR";
  public static final String ENCODING_TYPE_HTML_ENTITIES = "HTMLENTITIES";
  public static final String ENCODING_TYPE_HTML = "HTML";

  private String path;
  private String encoding;

  private StringBuffer content = null;
  private Boolean printOut = Boolean.TRUE;
  private Boolean loop = Boolean.FALSE;
  private String startLine = null;
  private String nbLine = null;
  private String scopeFileOut = null;
  private String nameFileOut = null;
  private String reUseFileOut;

  public TagFileReader() {
  }

  public int doStartTag() {
	JspWriter out = pageContext.getOut();
	ServletRequest request = pageContext.getRequest();
	HttpSession session = pageContext.getSession();
	try {
		String aPath = UtilRequest.replaceParamByRequestValue(getPath(), request, session, "");
		String aEncoding = UtilRequest.replaceParamByRequestValue(getEncoding(), request, session, "");
		if (UtilString.isNotEmpty(aPath)) {
			BeanFile file = null;
			// Verifi si on est sur une adresse FTP
	    	if (aPath.toUpperCase().startsWith("FTP://")) {
	    		BeanFTPAddress address = new BeanFTPAddress(aPath);
	    		file = new BeanFTP(address, address.getPath());
	    		address.setPath("/");
	    	}
	    	else {
				file = new BeanFile(aPath);
	    	}
	    	// Verifi l'existance du fichier
			if ((file != null) && (file.exists()) && (file.isFile())) {
				BeanFile reUseFile = null;
				String ret = null;
				// Test si l'option de réutilisation du fichier de sorti est initialisé
				if ("true".equalsIgnoreCase(reUseFileOut)) {
					Object obj = null;
			        if ("session".equalsIgnoreCase(getScopeFileOut()))
			        	obj = pageContext.getSession().getAttribute(getNameFileOut());
			        else
			        	obj = pageContext.getRequest().getAttribute(getNameFileOut());
			        if (obj instanceof BeanFile) {
			        	reUseFile = (BeanFile)obj;
			        	// Verifi si le fichier est toujours à jour
			        	if (reUseFile.isUpToDate(file)) {
			        		file = reUseFile;
			        		// Récupere le buffer
			        		ret = reUseFile.getBuffer();
			        	}
			        }
				}

				if (ret==null)
					ret = file.read();

				// Récuperation d'une partie seulement du fichier
				String aStartLine = UtilRequest.replaceParamByRequestValue(getStartLine(), request, session, "");
				String aNbLine = UtilRequest.replaceParamByRequestValue(getNbLine(), request, session, "");
				if (UtilString.isNotEmpty(aStartLine) || UtilString.isNotEmpty(aNbLine)) {
					int iStartLine = (UtilString.isNotEmpty(aStartLine) ? Integer.parseInt(aStartLine) : 1);
					int iNbLine = (UtilString.isNotEmpty(aNbLine) ? Integer.parseInt(aNbLine) : -1);
					ret = UtilString.readLine(ret, iStartLine, iNbLine);
				}

				// Encodage du fichier
				if (UtilString.isNotEmpty(aEncoding)){
					String uEncoding = aEncoding.toUpperCase();
                    if (uEncoding.equals(ENCODING_TYPE_HTML_SPECIAL_CHAR))
                    	content = new StringBuffer(encodeHTMLSpecialChars(ret.toString()));
                    else if (uEncoding.equals(ENCODING_TYPE_HTML_ENTITIES))
                    	content = new StringBuffer(encodeHTMLEntities(ret.toString()));
                    else if (uEncoding.equals(ENCODING_TYPE_HTML))
                    	content = new StringBuffer(encodeHTML(ret.toString()));
                    else
                    	content = new StringBuffer(URLEncoder.encode(ret.toString(), aEncoding));
				}
				else {
					content = new StringBuffer(ret.toString());
				}

				// Stock le fichier dans la session de l'utilisateur
				if (UtilString.isNotEmpty(getNameFileOut())){
			        if ("session".equalsIgnoreCase(getScopeFileOut()))
				          pageContext.getSession().setAttribute(getNameFileOut(), file);
			        else
				          pageContext.getRequest().setAttribute(getNameFileOut(), file);
				}
	    	}
		}
	} catch (FileNotFoundException ex) {
		ex.printStackTrace();
	} catch (IOException ex) {
		ex.printStackTrace();
	} catch (Exception ex) {
		ex.printStackTrace();
	}
    return ((content!=null) ? EVAL_BODY_BUFFERED : SKIP_BODY);
  }

  public int doAfterBody() {
    BodyContent bc = getBodyContent();
    if (bc != null) {
      try {
        JspWriter out = bc.getEnclosingWriter();
    	out.println(bc.getString());
        bc.clearBody();
      }
      catch (IOException ioe) {
        Trace.ERROR("Error in BodingTag: ", ioe);
      }
    }
    return ((loop.booleanValue()) ? EVAL_BODY_BUFFERED : SKIP_BODY);
  }

  public int doEndTag() {
    if (printOut.booleanValue() && (content!=null)) {
    	try {
			pageContext.getOut().write(content.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    return EVAL_PAGE;
  }

  public String encodeHTMLSpecialChars(String str){
	StringBuffer ret = new StringBuffer();
	if (UtilString.isNotEmpty(str)){
		char c;
		char[] charArray = str.toCharArray();
		int size = charArray.length;
		for(int i=0 ; i<size ; i++){
			c = charArray[i];
			switch(c) {
				case '&' : ret.append("&amp;");  break;
				case '"' : ret.append("&quot;"); break;
				case '\'': ret.append("&#039;"); break;
				case '<' : ret.append("&lt;");   break;
				case '>' : ret.append("&gt;");   break;
				default  : ret.append(c);
			}
		}
	}
	return ret.toString();
  }

  public String encodeHTMLEntities(String str){
    return encode(str, false);
  }

  public String encodeHTML(String str){
    return encode(str, true);
  }

  protected String encode(String str, boolean htmlTag){
	StringBuffer ret = new StringBuffer();
	if (UtilString.isNotEmpty(str)){
		char c;
		char[] charArray = str.toCharArray();
		int size = charArray.length;
		for(int i=0 ; i<size ; i++){
			c = charArray[i];
			switch(c) {
				case '&' : ret.append("&amp;");  break;
				case '<' : ret.append("&lt;");  break;
				case '>' : ret.append("&gt;");  break;
				case ' ' : ret.append("&nbsp;");  break;
				case '¡' : ret.append("&iexcl;");  break;
				case '¢' : ret.append("&cent;");  break;
				case '£' : ret.append("&pound;");  break;
				case '¤' : ret.append("&curren;");  break;
				case '¥' : ret.append("&yen;");  break;
				case '¦' : ret.append("&brvbar;");  break;
				case '§' : ret.append("&sect;");  break;
				case '¨' : ret.append("&uml;");  break;
				case '©' : ret.append("&copy;");  break;
				case 'ª' : ret.append("&ordf;");  break;
				case '«' : ret.append("&laquo;");  break;
				case '¬' : ret.append("&not;");  break;
				case '­' : ret.append("&shy;");  break;
				case '®' : ret.append("&reg;");  break;
				case '¯' : ret.append("&macr;");  break;
				case '°' : ret.append("&deg;");  break;
				case '±' : ret.append("&plusmn;");  break;
				case '²' : ret.append("&sup2;");  break;
				case '³' : ret.append("&sup3;");  break;
				case '´' : ret.append("&acute;");  break;
				case 'µ' : ret.append("&micro;");  break;
				case '¶' : ret.append("&para;");  break;
				case '·' : ret.append("&middot;");  break;
				case '¸' : ret.append("&cedil;");  break;
				case '¹' : ret.append("&sup1;");  break;
				case 'º' : ret.append("&ordm;");  break;
				case '»' : ret.append("&raquo;");  break;
				case '¼' : ret.append("&frac14;");  break;
				case '½' : ret.append("&frac12;");  break;
				case '¾' : ret.append("&frac34;");  break;
				case '¿' : ret.append("&iquest;");  break;
				case 'À' : ret.append("&Agrave;");  break;
				case 'Á' : ret.append("&Aacute;");  break;
				case 'Â' : ret.append("&Acirc;");  break;
				case 'Ã' : ret.append("&Atilde;");  break;
				case 'Ä' : ret.append("&Auml;");  break;
				case 'Å' : ret.append("&Aring;");  break;
				case 'Æ' : ret.append("&AElig;");  break;
				case 'Ç' : ret.append("&Ccedil;");  break;
				case 'È' : ret.append("&Egrave;");  break;
				case 'É' : ret.append("&Eacute;");  break;
				case 'Ê' : ret.append("&Ecirc;");  break;
				case 'Ë' : ret.append("&Euml;");  break;
				case 'Ì' : ret.append("&Igrave;");  break;
				case 'Í' : ret.append("&Iacute;");  break;
				case 'Ï' : ret.append("&Iuml;");  break;
				case 'Ð' : ret.append("&ETH;");  break;
				case 'Ñ' : ret.append("&Ntilde;");  break;
				case 'Ò' : ret.append("&Ograve;");  break;
				case 'Ó' : ret.append("&Oacute;");  break;
				case 'Ô' : ret.append("&Ocirc;");  break;
				case 'Õ' : ret.append("&Otilde;");  break;
				case 'Ö' : ret.append("&Ouml;");  break;
				case '×' : ret.append("&times;");  break;
				case 'Ø' : ret.append("&Oslash;");  break;
				case 'Ù' : ret.append("&Ugrave;");  break;
				case 'Ú' : ret.append("&Uacute;");  break;
				case 'Û' : ret.append("&Ucirc;");  break;
				case 'Ü' : ret.append("&Uuml;");  break;
				case 'Ý' : ret.append("&Yacute;");  break;
				case 'Þ' : ret.append("&THORN;");  break;
				case 'ß' : ret.append("&szlig;");  break;
				case 'à' : ret.append("&agrave;");  break;
				case 'á' : ret.append("&aacute;");  break;
				case 'â' : ret.append("&acirc;");  break;
				case 'ã' : ret.append("&atilde;");  break;
				case 'ä' : ret.append("&auml;");  break;
				case 'å' : ret.append("&aring;");  break;
				case 'æ' : ret.append("&aelig;");  break;
				case 'ç' : ret.append("&ccedil;");  break;
				case 'è' : ret.append("&egrave;");  break;
				case 'é' : ret.append("amp;eacute;");  break;
				case 'ê' : ret.append("&ecirc;");  break;
				case 'ë' : ret.append("&euml;");  break;
				case 'ì' : ret.append("&igrave;");  break;
				case 'í' : ret.append("&iacute;");  break;
				case 'î' : ret.append("&icirc;");  break;
				case 'ï' : ret.append("&iuml;");  break;
				case 'ð' : ret.append("&eth;");  break;
				case 'ñ' : ret.append("&ntilde;");  break;
				case 'ò' : ret.append("&ograve;");  break;
				case 'ó' : ret.append("&oacute;");  break;
				case 'ô' : ret.append("&ocirc;");  break;
				case 'õ' : ret.append("&otilde;");  break;
				case 'ö' : ret.append("&ouml;");  break;
				case '÷' : ret.append("&divide;");  break;
				case 'ø' : ret.append("&oslash;");  break;
				case 'ù' : ret.append("&ugrave;");  break;
				case 'ú' : ret.append("&uacute;");  break;
				case 'û' : ret.append("&ucirc;");  break;
				case 'ü' : ret.append("&uuml;");  break;
				case 'ý' : ret.append("&yacute;");  break;
				case 'þ' : ret.append("&thorn;");  break;
				case 'ÿ' : ret.append("&yuml;");  break;
				case 'Î' : ret.append("&Icirc;");  break;
                               case '"' : ret.append("&quot;");  break;
//                               case '(' : ret.append("&lsaquo;");  break;
//                               case ')' : ret.append("&rsaquo;");  break;
                               case '{' : ret.append("&#123;");  break;
                               case '}' : ret.append("&#125;");  break;
                               case '\\' : ret.append("&#92;");  break;
                               case '/' : ret.append("&#47;");  break;
                               case '*' : ret.append("&#42;");  break;
                               case '\'' : ret.append("&#39;");  break;
                               default  : {
                                  if (htmlTag) {
                                    switch(c) {
                                      case '\n' : ret.append("<br>"); break;
                                      case '\r' : break;
                                      case '\t' : ret.append("&nbsp;&nbsp;&nbsp;&nbsp;"); break;
                                      default   : ret.append(c);
                                    }
                                  }
                                  else
                                    ret.append(c);
                                }
			}
		}
	}
	return ret.toString();
  }
	/**
	 * @return
	 * @uml.property  name="path"
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param  string
	 * @uml.property  name="path"
	 */
	public void setPath(String string) {
		path = string;
	}

	/**
	 * @return
	 * @uml.property  name="encoding"
	 */
	public String getEncoding() {
		return encoding;
	}

	/**
	 * @param  string
	 * @uml.property  name="encoding"
	 */
	public void setEncoding(String string) {
		encoding = string;
	}

	/**
	 * @return  the content
	 * @uml.property  name="content"
	 */
	public StringBuffer getContent() {
		return content;
	}

	/**
	 * @param content  the content to set
	 * @uml.property  name="content"
	 */
	public void setContent(StringBuffer content) {
		this.content = content;
	}

	/**
	 * @return  the loop
	 * @uml.property  name="loop"
	 */
	public Boolean getLoop() {
		return loop;
	}

	/**
	 * @param loop  the loop to set
	 * @uml.property  name="loop"
	 */
	public void setLoop(Boolean loop) {
		this.loop = loop;
	}

	/**
	 * @return  the printOut
	 * @uml.property  name="printOut"
	 */
	public Boolean getPrintOut() {
		return printOut;
	}

	/**
	 * @param printOut  the printOut to set
	 * @uml.property  name="printOut"
	 */
	public void setPrintOut(Boolean printOut) {
		this.printOut = printOut;
	}

	  /**
	 * @return  the nbLine
	 * @uml.property  name="nbLine"
	 */
	public String getNbLine() {
		return nbLine;
	}

	/**
	 * @param nbLine  the nbLine to set
	 * @uml.property  name="nbLine"
	 */
	public void setNbLine(String nbLine) {
		this.nbLine = nbLine;
	}

	/**
	 * @return  the startLine
	 * @uml.property  name="startLine"
	 */
	public String getStartLine() {
		return startLine;
	}

	/**
	 * @param startLine  the startLine to set
	 * @uml.property  name="startLine"
	 */
	public void setStartLine(String startLine) {
		this.startLine = startLine;
	}

	/**
	 * @return  the nameFileOut
	 * @uml.property  name="nameFileOut"
	 */
	public String getNameFileOut() {
		return nameFileOut;
	}

	/**
	 * @param nameFileOut  the nameFileOut to set
	 * @uml.property  name="nameFileOut"
	 */
	public void setNameFileOut(String nameFileOut) {
		this.nameFileOut = nameFileOut;
	}

	/**
	 * @return  the scopeFileOut
	 * @uml.property  name="scopeFileOut"
	 */
	public String getScopeFileOut() {
		return scopeFileOut;
	}

	/**
	 * @param scopeFileOut  the scopeFileOut to set
	 * @uml.property  name="scopeFileOut"
	 */
	public void setScopeFileOut(String scopeFileOut) {
		this.scopeFileOut = scopeFileOut;
	}

	/**
	 * @return  the reUseFileOut
	 * @uml.property  name="reUseFileOut"
	 */
	public String getReUseFileOut() {
		return reUseFileOut;
	}

	/**
	 * @param reUseFileOut  the reUseFileOut to set
	 * @uml.property  name="reUseFileOut"
	 */
	public void setReUseFileOut(String reUseFileOut) {
		this.reUseFileOut = reUseFileOut;
	}
}
