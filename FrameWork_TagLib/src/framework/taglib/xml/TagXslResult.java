package framework.taglib.xml;

import framework.ressource.util.UtilRequest;
import framework.ressource.util.UtilString;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 * @author  HP_Administrateur
 */
public class TagXslResult extends TagSupport {
  private String name;
  private String scope;

  public TagXslResult() {
  }

  public int doStartTag() {
    JspWriter out = pageContext.getOut();
    try {
      TagXsl tagParent = null;
      Tag parent = getParent();
      while (parent != null) {
        if (parent instanceof TagXsl) {
          tagParent = (TagXsl) parent;
          break;
        }
        parent = parent.getParent();
      }
      if (tagParent != null) {
	      tagParent.setTagXslResult(this);
      }
    }
    catch(Exception ex) {
      ex.printStackTrace(new PrintWriter(out));
    }
    return EVAL_PAGE;
  }

  public void processResult(String result) throws Exception {
    if (UtilString.isNotEmpty(getName())) {
	    ServletRequest request = pageContext.getRequest();
	    HttpSession session = pageContext.getSession();
	    String szName = UtilRequest.replaceParamByRequestValue(getName(), request, session, "");
	    String szScope = "request";
	    if (UtilString.isNotEmpty(getScope()))
	      szScope = UtilRequest.replaceParamByRequestValue(getScope(), request, session, "");
	    if (UtilString.isEqualsIgnoreCase("session", szScope))
	      session.setAttribute(szName, result);
	    else
	      request.setAttribute(szName, result);
    }
  }

  /**
 * @return  the name
 * @uml.property  name="name"
 */
public String getName(){
    return name;
  }
  /**
 * @param name  the name to set
 * @uml.property  name="name"
 */
public void setName(String name){
    this.name = name;
  }
  /**
 * @return  the scope
 * @uml.property  name="scope"
 */
public String getScope(){
    return scope;
  }
  /**
 * @param scope  the scope to set
 * @uml.property  name="scope"
 */
public void setScope(String scope){
    this.scope = scope;
  }
}
