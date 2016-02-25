package framework.taglib.request;

import framework.ressource.util.UtilEvalJava;
import framework.ressource.util.UtilRequest;
import framework.ressource.util.UtilString;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * @author  HP_Administrateur
 */
public class TagDefineAttributeIf extends TagSupport {

  private String expression = null;
  private String replaceNullBy = null;
  private String ifTrue = null;
  private String ifFalse = null;
  private String name = null;
  private String scope;
  private String doEval = null;

  public TagDefineAttributeIf() {
  }

  public int doStartTag() {
    boolean isShow=false;
    String ifTrue = "";
    String ifFalse = "";
    String szShow = "";
    if(UtilString.isNotEmpty(getExpression())) {
      try {
        szShow = UtilRequest.replaceParamByRequestValue(getExpression(), pageContext.getRequest(), pageContext.getSession(), (getReplaceNullBy()==null) ? "" : getReplaceNullBy());
        ifTrue = UtilRequest.replaceParamByRequestValue(getIfTrue(), pageContext.getRequest(), pageContext.getSession(), (getReplaceNullBy()==null) ? "" : getReplaceNullBy());
        ifFalse = UtilRequest.replaceParamByRequestValue(getIfFalse(), pageContext.getRequest(), pageContext.getSession(), (getReplaceNullBy()==null) ? "" : getReplaceNullBy());
        // Evaluation de l'expression
        isShow = UtilEvalJava.resolveBooleanExpression(szShow);
      }
      catch (Exception ex) {}
    }
    if (Boolean.TRUE.toString().equals(getDoEval())) {
      ifTrue = UtilEvalJava.safeResolveStringExpression(ifTrue);
      ifFalse = UtilEvalJava.safeResolveStringExpression(ifFalse);
    }
    if ((getScope()!=null)&&(getScope().equalsIgnoreCase("request")))
      pageContext.getSession().setAttribute(getName(), (isShow ? ifTrue : ifFalse));
    else
      pageContext.getRequest().setAttribute(getName(), (isShow ? ifTrue : ifFalse));
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
 * @param ifFalse  the ifFalse to set
 * @uml.property  name="ifFalse"
 */
public void setIfFalse(String ifFalse) {
    this.ifFalse = ifFalse;
  }

  /**
 * @param ifTrue  the ifTrue to set
 * @uml.property  name="ifTrue"
 */
public void setIfTrue(String ifTrue) {
    this.ifTrue = ifTrue;
  }

  /**
 * @param replaceNullBy  the replaceNullBy to set
 * @uml.property  name="replaceNullBy"
 */
public void setReplaceNullBy(String replaceNullBy) {
    this.replaceNullBy = replaceNullBy;
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
 * @param doEval  the doEval to set
 * @uml.property  name="doEval"
 */
public void setDoEval(String doEval) {
    this.doEval = doEval;
  }

  /**
 * @return  the expression
 * @uml.property  name="expression"
 */
public String getExpression() {
    return expression;
  }

  /**
 * @return  the ifFalse
 * @uml.property  name="ifFalse"
 */
public String getIfFalse() {
    return ifFalse;
  }

  /**
 * @return  the ifTrue
 * @uml.property  name="ifTrue"
 */
public String getIfTrue() {
    return ifTrue;
  }

  /**
 * @return  the replaceNullBy
 * @uml.property  name="replaceNullBy"
 */
public String getReplaceNullBy() {
    return replaceNullBy;
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
 * @return  the doEval
 * @uml.property  name="doEval"
 */
public String getDoEval() {
    return doEval;
  }

}
