package framework.taglib.request;


import framework.ressource.util.UtilRequest;
import framework.ressource.util.UtilString;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * @author  HP_Administrateur
 */
public class TagDefineCount extends TagSupport {

  private String name = null;
  private String scope = null;

  public TagDefineCount() {
  }

  public int doStartTag() {
    if (UtilString.isNotEmpty(getName())) {
      try {
        String name = UtilRequest.replaceParamByRequestValue(getName(), pageContext.getRequest(), pageContext.getSession(), "");
        Object expr = null;
        if ((getScope()!=null)&&(getScope().equalsIgnoreCase("session")))
        	expr = pageContext.getSession().getAttribute(name);
          else
        	expr = pageContext.getRequest().getAttribute(name);
        
        if (expr==null)
        	expr = "0";
        expr = Integer.toString(Integer.parseInt(expr.toString())+1);
        if ((getScope()!=null)&&(getScope().equalsIgnoreCase("session")))
          pageContext.getSession().setAttribute(name, expr);
        else
          pageContext.getRequest().setAttribute(name, expr);
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
