package framework.taglib.request;

import framework.ressource.util.UtilRequest;
import framework.ressource.util.UtilString;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * @author  HP_Administrateur
 */
public class TagRemoveAttribute extends TagSupport {

  private String name = null;
  private String scope = null;

  public TagRemoveAttribute() {
  }

  public int doStartTag() {
    if(UtilString.isNotEmpty(getName())) {
      try {
        String name = UtilRequest.replaceParamByRequestValue(getName(), pageContext.getRequest(), pageContext.getSession(), "");
        if ((getScope()!=null)&&(getScope().equalsIgnoreCase("session")))
          pageContext.getSession().removeAttribute(name);
        else
          pageContext.getRequest().removeAttribute(name);
      }
      catch (Exception ex) {}
    }
    return SKIP_BODY;
  }

  public int doEndTag() {
    return EVAL_PAGE;
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
}
