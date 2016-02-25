package framework.taglib.logic;

import framework.ressource.util.UtilString;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * @author  HP_Administrateur
 */
public class TagIfDefine extends TagSupport {

  private String name = null;
  private String scope = null;
  private String checkNotEmpty = null;

  public TagIfDefine() {
  }

  public int doStartTag() {
    Object value = null;
    if(UtilString.isNotEmpty(getName())) {
      try {
        if (UtilString.isEqualsIgnoreCase(getScope(), "session"))
          value = pageContext.getSession().getAttribute(getName());
        else {
          value = pageContext.getRequest().getParameter(getName());
          if (value==null)
            value = pageContext.getRequest().getAttribute(getName());
        }
      }
      catch (Exception ex) {}
    }
    boolean ret = value!=null;
    if (ret && "true".equalsIgnoreCase(checkNotEmpty))
      ret = UtilString.isNotEmpty(value.toString());
    return ret ? EVAL_BODY_INCLUDE : SKIP_BODY;
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
 * @param checkNotEmpty  the checkNotEmpty to set
 * @uml.property  name="checkNotEmpty"
 */
public void setCheckNotEmpty(String checkNotEmpty) {
    this.checkNotEmpty = checkNotEmpty;
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
 * @return  the checkNotEmpty
 * @uml.property  name="checkNotEmpty"
 */
public String getCheckNotEmpty() {
    return checkNotEmpty;
  }
}
