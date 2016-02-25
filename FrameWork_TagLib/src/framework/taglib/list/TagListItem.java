package framework.taglib.list;

import framework.ressource.util.UtilString;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * @author  HP_Administrateur
 */
public class TagListItem extends TagSupport {
  private String scope;
  private String name;

  public TagListItem() {
  }

  public int doStartTag() {
    JspWriter out = pageContext.getOut();
    TagList tagParent = null;
    Tag parent = getParent();
    while (parent!=null && tagParent==null) {
      if (parent instanceof TagList) {
        tagParent = (TagList)parent;
      }
      parent = parent.getParent();
    }
    if (tagParent != null) {
      if (UtilString.isNotEmpty(getName())) {
        if ("session".equalsIgnoreCase(getScope()))
          pageContext.getSession().setAttribute(getName(), tagParent.getCurrentItem());
        else
          pageContext.getRequest().setAttribute(getName(), tagParent.getCurrentItem());
      }
    }
    return EVAL_PAGE;
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
}
