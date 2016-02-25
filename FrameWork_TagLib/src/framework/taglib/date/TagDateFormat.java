package framework.taglib.date;

import framework.ressource.util.UtilRequest;
import framework.ressource.util.UtilString;
import framework.trace.Trace;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * @author  HP_Administrateur
 */
public class TagDateFormat extends TagSupport {
  private String time;
  private String pattern;

  public int doStartTag() {
    JspWriter out = pageContext.getOut();
    ServletRequest request = pageContext.getRequest();
    HttpSession session = pageContext.getSession();
    try {
      String aTime = UtilRequest.replaceParamByRequestValue(getTime(), request, session, "");
      String aPattern =  UtilRequest.replaceParamByRequestValue(getPattern(), request, session, "");
      if (UtilString.isNotEmpty(aTime)&&UtilString.isNotEmpty(aPattern))
        out.write(new SimpleDateFormat(aPattern).format(new Date(Long.parseLong(aTime))));
    }
    catch (Exception ex) {Trace.ERROR(ex);}
    return EVAL_PAGE;
  }

  /**
 * @param time  the time to set
 * @uml.property  name="time"
 */
public void setTime(String time) {
    this.time = time;
  }

  /**
 * @param pattern  the pattern to set
 * @uml.property  name="pattern"
 */
public void setPattern(String pattern) {
    this.pattern = pattern;
  }

  /**
 * @return  the time
 * @uml.property  name="time"
 */
public String getTime() {
    return time;
  }

  /**
 * @return  the pattern
 * @uml.property  name="pattern"
 */
public String getPattern() {
    return pattern;
  }

  public TagDateFormat() {
  }
}
