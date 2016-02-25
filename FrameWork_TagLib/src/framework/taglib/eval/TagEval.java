package framework.taglib.eval;

import framework.ressource.util.UtilEvalJava;
import framework.ressource.util.UtilRequest;
import framework.ressource.util.UtilString;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * @author  HP_Administrateur
 */
public class TagEval extends TagSupport {
  private String expression =null;
  private String replaceNullBy=null;

  public TagEval() {
  }

  public int doStartTag() {
    JspWriter out = pageContext.getOut();
    if(UtilString.isNotEmpty(getExpression())) {
      try {
        String szShow = UtilRequest.replaceParamByRequestValue(getExpression(), pageContext.getRequest(), pageContext.getSession(), (replaceNullBy==null) ? "" : replaceNullBy);
        out.write(UtilEvalJava.safeResolveStringExpression(szShow));
      }
      catch (Exception ex) {}
    }
    return SKIP_BODY;
  }

  /**
 * @return  the replaceNullBy
 * @uml.property  name="replaceNullBy"
 */
public String getReplaceNullBy() {
    return replaceNullBy;
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
}
