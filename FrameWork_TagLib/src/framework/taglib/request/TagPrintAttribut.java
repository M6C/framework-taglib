package framework.taglib.request;

import framework.ressource.util.UtilEncoder;
import framework.ressource.util.UtilRequest;
import framework.ressource.util.UtilString;
import framework.trace.Trace;

import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * @author  HP_Administrateur
 */
public class TagPrintAttribut extends TagSupport {
  private String name = null;
  private String scope = null;
  private String encoding = null;
  private String decoding = null;

  public TagPrintAttribut() {
  }

  public int doStartTag() {
    JspWriter out = pageContext.getOut();
    ServletRequest request = pageContext.getRequest();
    HttpSession session = pageContext.getSession();
    String szName = UtilRequest.replaceParamByRequestValue(getName(), request, session, "");
    String szScope = UtilRequest.replaceParamByRequestValue(getScope(), request, session, "");
    if(UtilString.isNotEmpty(szName)) {
      try {
        if (szName.indexOf('$')<0) {
          if ("session".equalsIgnoreCase(szScope))
            szName = "S$" + szName;
          else
            szName = "R$" + szName;
        }
//        Object value = UtilRequest.findObject(request, session, szName);
        Object value = UtilRequest.replaceParamByRequestValue("#"+szName+"#", request, session, "");;
        if(value!=null) {
          String txt = value.toString();
          if(UtilString.isNotEmpty(getEncoding())) {
            if ("HTML".equalsIgnoreCase(encoding))
              txt = UtilEncoder.encodeHTML(txt);
            else if ("HTMLSpecialChars".equalsIgnoreCase(encoding))
              txt = UtilEncoder.encodeHTMLSpecialChars(txt);
            else if ("HTMLEntities".equalsIgnoreCase(encoding))
              txt = UtilEncoder.encodeHTMLEntities(txt);
            else
              txt = URLEncoder.encode(txt, getEncoding());
          }
          if(UtilString.isNotEmpty(getDecoding()))
            txt = URLDecoder.decode(txt, getDecoding());
          out.write(txt);
        }
      }
      catch (Throwable t) {
      	Trace.ERROR(this, t);
      }
    }
    return SKIP_BODY;
  }

  /**
 * @param name  the name to set
 * @uml.property  name="name"
 */
public void setName(String name) {
    this.name = name;
  }

  /**
 * @param scope  the scope to set
 * @uml.property  name="scope"
 */
public void setScope(String scope) {
    this.scope = scope;
  }

  /**
 * @param encoding  the encoding to set
 * @uml.property  name="encoding"
 */
public void setEncoding(String encoding) {
    this.encoding = encoding;
  }

  /**
 * @param decoding  the decoding to set
 * @uml.property  name="decoding"
 */
public void setDecoding(String decoding) {
    this.decoding = decoding;
  }

  /**
 * @return  the name
 * @uml.property  name="name"
 */
public String getName() {
    return name;
  }

  /**
 * @return  the scope
 * @uml.property  name="scope"
 */
public String getScope() {
    return scope;
  }

  /**
 * @return  the encoding
 * @uml.property  name="encoding"
 */
public String getEncoding() {
    return encoding;
  }

  /**
 * @return  the decoding
 * @uml.property  name="decoding"
 */
public String getDecoding() {
    return decoding;
  }
}
