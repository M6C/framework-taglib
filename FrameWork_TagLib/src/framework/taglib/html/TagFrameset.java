package framework.taglib.html;

import framework.beandata.*;
import framework.ressource.util.*;
import framework.trace.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

/**
 * @author  HP_Administrateur
 */
public class TagFrameset extends BodyTagSupport {

  public static final String CONST_TYPE="FRAMESET";
  public static final boolean CONST_HAS_ENDTAG=true;

  private String content=null;
  private String stringCharactere=null;
  private String attrFrameborder;
  private String attrContent;
  private String attrFramespacing;
  private String attrBorder;
  private String attrStringCharactere;
  private String attrRows;
  private String attrCols;

  public TagFrameset() {
  }

  public int doStartTag() {
    JspWriter out = pageContext.getOut();
    ServletRequest request = pageContext.getRequest();
    HttpSession session = pageContext.getSession();
    try {
      out.print("<FRAMESET");
      if (UtilString.isNotEmpty(getAttrFrameborder()))
        out.print(" FRAMEBORDER="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrFrameborder(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrContent()))
        out.print(" CONTENT="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrContent(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrFramespacing()))
        out.print(" FRAMESPACING="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrFramespacing(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrBorder()))
        out.print(" BORDER="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrBorder(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrStringCharactere()))
        out.print(" STRINGCHARACTERE="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrStringCharactere(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrRows()))
        out.print(" ROWS="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrRows(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrCols()))
        out.print(" COLS="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrCols(), request, session, "")+getStringCharactere());
      out.print(">");
    } catch(IOException ioe) {
      Trace.ERROR("Error in HeadingTag: ", ioe);
    }
    return EVAL_BODY_INCLUDE;
  }

  public int doEndTag() {
    JspWriter out = pageContext.getOut();
    ServletRequest request = pageContext.getRequest();
    HttpSession session = pageContext.getSession();
    try {
      if (UtilString.isNotEmpty(getContent()))
        out.print(UtilRequest.replaceParamByRequestValue(getContent(), request, session, ""));
      out.print("</FRAMESET>");
    } catch(IOException ioe) {
      Trace.ERROR("Error in FootingTag: ", ioe);
    }
    return EVAL_PAGE;
  }

  public int doAfterBody() {
    BodyContent bc = getBodyContent();
    ServletRequest request = pageContext.getRequest();
    HttpSession session = pageContext.getSession();
    if (bc != null) {
      try{
        JspWriter out = bc.getEnclosingWriter();
        out.println(bc.getString());
        bc.clearBody();
        return EVAL_BODY_TAG;
      } catch(IOException ioe) {
        Trace.ERROR("Error in BodingTag: ", ioe);
      }
    }
    return SKIP_BODY;
  }

  /**
 * @return  the content
 * @uml.property  name="content"
 */
public String getContent() {return content;}
  /**
 * @param content  the content to set
 * @uml.property  name="content"
 */
public void setContent(String content) {this.content=content;}

  /**
 * @return  the stringCharactere
 * @uml.property  name="stringCharactere"
 */
public String getStringCharactere() {return (stringCharactere==null) ? "\"" : stringCharactere;}
  /**
 * @param stringCharactere  the stringCharactere to set
 * @uml.property  name="stringCharactere"
 */
public void setStringCharactere(String stringCharactere) {this.stringCharactere=stringCharactere;}

  /**
 * @return  the attrFrameborder
 * @uml.property  name="attrFrameborder"
 */
public String getAttrFrameborder() {
    return attrFrameborder;
  }
  /**
 * @param attrFrameborder  the attrFrameborder to set
 * @uml.property  name="attrFrameborder"
 */
public void setAttrFrameborder(String attrFrameborder) {
    this.attrFrameborder = attrFrameborder;
  }
  /**
 * @return  the attrContent
 * @uml.property  name="attrContent"
 */
public String getAttrContent() {
    return attrContent;
  }
  /**
 * @param attrContent  the attrContent to set
 * @uml.property  name="attrContent"
 */
public void setAttrContent(String attrContent) {
    this.attrContent = attrContent;
  }
  /**
 * @return  the attrFramespacing
 * @uml.property  name="attrFramespacing"
 */
public String getAttrFramespacing() {
    return attrFramespacing;
  }
  /**
 * @param attrFramespacing  the attrFramespacing to set
 * @uml.property  name="attrFramespacing"
 */
public void setAttrFramespacing(String attrFramespacing) {
    this.attrFramespacing = attrFramespacing;
  }
  /**
 * @return  the attrBorder
 * @uml.property  name="attrBorder"
 */
public String getAttrBorder() {
    return attrBorder;
  }
  /**
 * @param attrBorder  the attrBorder to set
 * @uml.property  name="attrBorder"
 */
public void setAttrBorder(String attrBorder) {
    this.attrBorder = attrBorder;
  }
  /**
 * @return  the attrStringCharactere
 * @uml.property  name="attrStringCharactere"
 */
public String getAttrStringCharactere() {
    return attrStringCharactere;
  }
  /**
 * @param attrStringCharactere  the attrStringCharactere to set
 * @uml.property  name="attrStringCharactere"
 */
public void setAttrStringCharactere(String attrStringCharactere) {
    this.attrStringCharactere = attrStringCharactere;
  }
  /**
 * @return  the attrRows
 * @uml.property  name="attrRows"
 */
public String getAttrRows() {
    return attrRows;
  }
  /**
 * @param attrRows  the attrRows to set
 * @uml.property  name="attrRows"
 */
public void setAttrRows(String attrRows) {
    this.attrRows = attrRows;
  }
  /**
 * @return  the attrCols
 * @uml.property  name="attrCols"
 */
public String getAttrCols() {
    return attrCols;
  }
  /**
 * @param attrCols  the attrCols to set
 * @uml.property  name="attrCols"
 */
public void setAttrCols(String attrCols) {
    this.attrCols = attrCols;
  }

  public String toHtml() {
    StringBuffer ret = new StringBuffer("<FRAMESET");
    if(UtilString.isNotEmpty(getAttrFrameborder())) ret.append(" frameborder=\"").append(getAttrFrameborder()).append("\"");
    if(UtilString.isNotEmpty(getAttrContent())) ret.append(" content=\"").append(getAttrContent()).append("\"");
    if(UtilString.isNotEmpty(getAttrFramespacing())) ret.append(" framespacing=\"").append(getAttrFramespacing()).append("\"");
    if(UtilString.isNotEmpty(getAttrBorder())) ret.append(" border=\"").append(getAttrBorder()).append("\"");
    if(UtilString.isNotEmpty(getAttrStringCharactere())) ret.append(" stringCharactere=\"").append(getAttrStringCharactere()).append("\"");
    if(UtilString.isNotEmpty(getAttrRows())) ret.append(" rows=\"").append(getAttrRows()).append("\"");
    if(UtilString.isNotEmpty(getAttrCols())) ret.append(" cols=\"").append(getAttrCols()).append("\"");
    ret.append(">");
    if (getContent()!=null) ret.append(getContent());
    ret.append("</FRAMESET>");
    return ret.toString();
  }
  public String toJsp() {
    StringBuffer ret = new StringBuffer("<html:TagFrameset");
    if(UtilString.isNotEmpty(getAttrFrameborder())) ret.append(" attrFrameborder=").append(getStringCharactere()).append(getAttrFrameborder()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrContent())) ret.append(" attrContent=").append(getStringCharactere()).append(getAttrContent()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrFramespacing())) ret.append(" attrFramespacing=").append(getStringCharactere()).append(getAttrFramespacing()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrBorder())) ret.append(" attrBorder=").append(getStringCharactere()).append(getAttrBorder()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrStringCharactere())) ret.append(" attrStringCharactere=").append(getStringCharactere()).append(getAttrStringCharactere()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrRows())) ret.append(" attrRows=").append(getStringCharactere()).append(getAttrRows()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrCols())) ret.append(" attrCols=").append(getStringCharactere()).append(getAttrCols()).append(getStringCharactere());
    ret.append(">\r\n");
    if (getContent()!=null) ret.append(getContent());
    ret.append("</html:TagFrameset>\r\n");
    return ret.toString();
  }
}
