package framework.taglib.xml;

import framework.ressource.util.UtilRequest;
import framework.ressource.util.UtilString;
import framework.ressource.util.UtilXML;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;

/**
 * @author  HP_Administrateur
 */
public class TagXmlValue extends TagSupport {

  private String path;
  private String scope;
  private String name;

  /**
   * Objets interne au TagLib
   */
  TagXml tagParent = null;

  public TagXmlValue() {
  }

  public int doStartTag() {
    JspWriter out = pageContext.getOut();
    ServletRequest request = pageContext.getRequest();
    HttpSession session = pageContext.getSession();
    Tag parent = getParent();
    while (parent != null) {
      if (parent instanceof TagXml) {
        tagParent = (TagXml)parent;
        break;
      }
      parent = parent.getParent();
    }
    if (tagParent != null) {
      String val = elementValueFromXPath(tagParent.getCurrentNode());
      try {
        if (UtilString.isNotEmpty(getScope())) {
          if (UtilString.isEqualsIgnoreCase(getScope(), "session"))
            pageContext.getSession().setAttribute(name, val);
          else
            pageContext.getRequest().setAttribute(name, val);
        }
        else
          out.print(val);
      }
      catch (DOMException ex) {ex.printStackTrace(new PrintWriter(out));}
      catch (IOException ex) {ex.printStackTrace(new PrintWriter(out));}
    }
    return EVAL_BODY_INCLUDE;
  }

  public int doEndTag() {
	return EVAL_PAGE;
  }

  protected String elementValueFromXPath(Node currentNode) {
    String ret = "";
    JspWriter out = pageContext.getOut();
    ServletRequest request = pageContext.getRequest();
    HttpSession session = pageContext.getSession();
    try {
      if (UtilString.isNotEmpty(getPath()) && (currentNode!=null)) {
        String szPath = UtilRequest.replaceParamByRequestValue(getPath(), request, session, "");
        if (UtilString.isNotEmpty(szPath)) {
          ret = UtilXML.getXPathStringValue(tagParent.getDocument(), currentNode, szPath);
        }
      }
    }
    catch (Exception ex) {ex.printStackTrace(new PrintWriter(out));}
    return ret;
  }

  /**
 * @return  the path
 * @uml.property  name="path"
 */
public String getPath() {
    return path;
  }

  /**
 * @return  the scope
 * @uml.property  name="scope"
 */
public String getScope() {
    return scope;
  }

  /**
 * @return  the name
 * @uml.property  name="name"
 */
public String getName() {
    return name;
  }

  /**
 * @param path  the path to set
 * @uml.property  name="path"
 */
public void setPath(String path) {
    this.path = path;
  }

  /**
 * @param scope  the scope to set
 * @uml.property  name="scope"
 */
public void setScope(String scope) {
    this.scope = scope;
  }

  /**
 * @param name  the name to set
 * @uml.property  name="name"
 */
public void setName(String name) {
    this.name = name;
  }
}
