package framework.taglib.logic;

import framework.ressource.util.UtilEvalJava;
import framework.ressource.util.UtilRequest;
import framework.ressource.util.UtilString;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * @author  HP_Administrateur
 */
public class TagIf extends BodyTagSupport {

  private String expression =null;
  private String replaceNullBy=null;

  public TagIf() {
  }

  public int doStartTag() {
    boolean isShow=true;
    if(UtilString.isNotEmpty(getExpression())) {
      try {
        String szShow = UtilRequest.replaceParamByRequestValue(getExpression(), pageContext.getRequest(), pageContext.getSession(), (replaceNullBy==null) ? "" : replaceNullBy);
        isShow = UtilEvalJava.resolveBooleanExpression(szShow);
      }
      catch (Exception ex) {
        isShow = true;
      }
    }
    return (isShow) ? EVAL_BODY_INCLUDE : SKIP_BODY;
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
 * @param replaceNullBy  the replaceNullBy to set
 * @uml.property  name="replaceNullBy"
 */
public void setReplaceNullBy(String replaceNullBy) {
    this.replaceNullBy = replaceNullBy;
  }

  /**
 * @return  the expression
 * @uml.property  name="expression"
 */
public String getExpression() {
    return expression;
  }

  /**
 * @return  the replaceNullBy
 * @uml.property  name="replaceNullBy"
 */
public String getReplaceNullBy() {
    return replaceNullBy;
  }
}