package framework.taglib.file;

import framework.ressource.util.UtilRequest;
import framework.ressource.util.UtilString;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.URLEncoder;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * @author  HP_Administrateur
 */
public class TagFileReadLine extends TagSupport {

  public static final String ENCODING_TYPE_HTML_SPECIAL_CHAR = "HTMLSPECIALCHAR";
  public static final String ENCODING_TYPE_HTML_ENTITIES = "HTMLENTITIES";
  public static final String ENCODING_TYPE_HTML = "HTML";

  private String encoding;
  private String endWithNewLine;

  private static BufferedReader in = null;

  public TagFileReadLine() {
  }

  public int doStartTag() {
    JspWriter out = pageContext.getOut();
	ServletRequest request = pageContext.getRequest();
	HttpSession session = pageContext.getSession();
    TagFileReader tagParent = null;
    Tag parent = getParent();
    while (parent!=null) {
      if (parent instanceof TagFileReader) {
        tagParent = (TagFileReader)parent;
        break;
      }
      parent = parent.getParent();
    }
    if (tagParent != null) {
    	tagParent.setPrintOut(Boolean.FALSE);
    	if (tagParent.getContent()!=null) {
    		String aEncoding = UtilRequest.replaceParamByRequestValue(getEncoding(), request, session, "");
    		String content = tagParent.getContent().toString();
    		if (in==null)
    			in = new BufferedReader(new StringReader(content));
    		String line = null;
    		try {
    			line = in.readLine();
    			if (line!=null) {
    				boolean bNewLine = true;
    				try {bNewLine=new Boolean(endWithNewLine).booleanValue();} catch(Exception ex) {bNewLine = true;}
    				if (bNewLine) {
    					line += '\r'; line += '\n';
    				}
    				if (UtilString.isNotEmpty(aEncoding)){
    					String uEncoding = aEncoding.toUpperCase();
                        if (uEncoding.equals(ENCODING_TYPE_HTML_SPECIAL_CHAR))
                        	out.write(((TagFileReader)parent).encodeHTMLSpecialChars(line));
                        else if (uEncoding.equals(ENCODING_TYPE_HTML_ENTITIES))
                        	out.write(((TagFileReader)parent).encodeHTMLEntities(line));
                        else if (uEncoding.equals(ENCODING_TYPE_HTML))
                        	out.write(((TagFileReader)parent).encodeHTML(line));
                        else
                        	out.write(URLEncoder.encode(line, aEncoding));
    				}
    				else
    					out.write(line);
    				tagParent.setLoop(Boolean.TRUE);
    			}
    			else {
    				in.close();
    				in = null;
    				tagParent.setLoop(Boolean.FALSE);
    			}
    		} catch (IOException e) {
				try { in.close(); } catch (Exception e1) {}
				finally {
					in = null;
					tagParent.setLoop(Boolean.FALSE);
	    			e.printStackTrace();
				}
    		}
    	}
    }
    return EVAL_PAGE;
  }

/**
 * @return  the encoding
 * @uml.property  name="encoding"
 */
public String getEncoding() {
	return encoding;
}

/**
 * @param encoding  the encoding to set
 * @uml.property  name="encoding"
 */
public void setEncoding(String encoding) {
	this.encoding = encoding;
}

/**
 * @return  the endWithNewLine
 * @uml.property  name="endWithNewLine"
 */
public String getEndWithNewLine() {
	return endWithNewLine;
}

/**
 * @param endWithNewLine  the endWithNewLine to set
 * @uml.property  name="endWithNewLine"
 */
public void setEndWithNewLine(String withNewLine) {
	this.endWithNewLine = withNewLine;
}
}
