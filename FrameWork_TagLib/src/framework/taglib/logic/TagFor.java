package framework.taglib.logic;

import framework.ressource.util.UtilRequest;
import framework.ressource.util.UtilString;
import framework.trace.Trace;
import java.io.IOException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * @author  HP_Administrateur
 */
public class TagFor extends BodyTagSupport {
  private int index;
  private String from;
  private String to;
  private String step;
  private String name;
  private String scope;

  public TagFor() {
  }

  public int doStartTag() {
    int iFrom = UtilString.isNotEmpty(getFrom()) ? Integer.parseInt(getFromReplaceParamByRequestValue()) : 0;
    int iTo = Integer.parseInt(getToReplaceParamByRequestValue());
    index = 0;
    if (UtilString.isNotEmpty(getName())) {
        if ("session".equalsIgnoreCase(getScope()))
      	pageContext.getSession().setAttribute(getName(), new Integer(index));
        else
          pageContext.getRequest().setAttribute(getName(), new Integer(index));
      }
    return ((index>=iFrom)&&(index<iTo)) ? EVAL_BODY_BUFFERED : SKIP_BODY;
  }

  public int doAfterBody() {
    BodyContent bc = getBodyContent();
    int iStep = UtilString.isEmpty(getStep()) ? 1 : Integer.parseInt(getStepReplaceParamByRequestValue());
    int iFrom = UtilString.isNotEmpty(getFrom()) ? Integer.parseInt(getFromReplaceParamByRequestValue()) : 0;
    int iTo = Integer.parseInt(getToReplaceParamByRequestValue());
    index+=iStep;
    if (UtilString.isNotEmpty(getName())) {
      if ("session".equalsIgnoreCase(getScope()))
    	pageContext.getSession().setAttribute(getName(), new Integer(index));
      else
        pageContext.getRequest().setAttribute(getName(), new Integer(index));
    }
    if (bc != null) {
      try {
        JspWriter out = bc.getEnclosingWriter();
        out.println(bc.getString());
        bc.clearBody();
      }
      catch (IOException ioe) {
        Trace.ERROR("Error in BodingTag: ", ioe);
      }
    }
    return ((index>=iFrom)&&(index<iTo)) ? EVAL_BODY_BUFFERED : SKIP_BODY;
  }

  public int doEndTag() {
    return EVAL_PAGE;
  }

  public String getFromReplaceParamByRequestValue() {
    return UtilRequest.replaceParamByRequestValue(from, pageContext.getRequest(), pageContext.getSession(), "");
  }

  public String getToReplaceParamByRequestValue() {
    return UtilRequest.replaceParamByRequestValue(to, pageContext.getRequest(), pageContext.getSession(), "");
  }

  public String getStepReplaceParamByRequestValue() {
    return UtilRequest.replaceParamByRequestValue(step, pageContext.getRequest(), pageContext.getSession(), "");
  }

  /**
 * @return  the step
 * @uml.property  name="step"
 */
public String getStep() {
    return step;
  }

  /**
 * @return  the to
 * @uml.property  name="to"
 */
public String getTo() {
    return to;
  }

  /**
 * @return  the from
 * @uml.property  name="from"
 */
public String getFrom() {
    return from;
  }

  /**
 * @param step  the step to set
 * @uml.property  name="step"
 */
public void setStep(String step) {
    this.step = step;
  }

  /**
 * @param to  the to to set
 * @uml.property  name="to"
 */
public void setTo(String to) {
    this.to = to;
  }

  /**
 * @param from  the from to set
 * @uml.property  name="from"
 */
public void setFrom(String from) {
    this.from = from;
  }

  /**
 * @return  the name
 * @uml.property  name="name"
 */
public String getName() {
	return name;
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
 * @param scope  the scope to set
 * @uml.property  name="scope"
 */
public void setScope(String scope) {
	this.scope = scope;
  }
}
