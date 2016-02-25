package framework.taglib.request;

import framework.ressource.util.UtilEvalJava;
import framework.ressource.util.UtilRequest;
import framework.ressource.util.UtilString;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * @author  HP_Administrateur
 */
public class TagDefineAttribute extends TagSupport {

  private String expression = null;
  private String name = null;
  private String scope = null;
  private String doEval = null;
  private String keepObject = null;

  public TagDefineAttribute() {
  }

  public int doStartTag() {
    if(UtilString.isNotEmpty(getExpression())&&UtilString.isNotEmpty(getName())) {
      try {
        Object expr = null;
        if (UtilString.isEqualsIgnoreCase(Boolean.TRUE.toString(), getKeepObject())) {
          String szExpr = getExpression();
          if (szExpr.startsWith("#") && szExpr.endsWith("#"))
            szExpr = szExpr.substring(1, getExpression().length()-1);
          expr = UtilRequest.findObject(pageContext.getRequest(), pageContext.getSession(), szExpr);
        }
        else {
          expr = UtilRequest.replaceParamByRequestValue(getExpression(), pageContext.getRequest(), pageContext.getSession(), "");
        }
        String name = UtilRequest.replaceParamByRequestValue(getName(), pageContext.getRequest(), pageContext.getSession(), "");
        if (Boolean.TRUE.toString().equals(getDoEval()))
          expr = UtilEvalJava.safeResolveStringExpression(expr.toString());
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
 * @param expression  the expression to set
 * @uml.property  name="expression"
 */
public void setExpression(String expression) {
    this.expression = expression;
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
 * @param doEval  the doEval to set
 * @uml.property  name="doEval"
 */
public void setDoEval(String doEval) {
    this.doEval = doEval;
  }

  /**
 * @param keepObject  the keepObject to set
 * @uml.property  name="keepObject"
 */
public void setKeepObject(String keepObject) {
    this.keepObject = keepObject;
  }

  /**
 * @return  the expression
 * @uml.property  name="expression"
 */
public String getExpression() {
    return expression;
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
 * @return  the doEval
 * @uml.property  name="doEval"
 */
public String getDoEval() {
    return doEval;
  }

  /**
 * @return  the keepObject
 * @uml.property  name="keepObject"
 */
public String getKeepObject() {
    return keepObject;
  }
}
