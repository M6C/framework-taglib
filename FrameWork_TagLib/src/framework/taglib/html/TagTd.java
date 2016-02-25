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
public class TagTd extends BodyTagSupport {

  public static final String CONST_TYPE="TD";
  public static final boolean CONST_HAS_ENDTAG=true;

  private String content=null;
  private String stringCharactere=null;
  private String attrHeight;
  private String attrBackground;
  private String attrContent;
  private String attrAlign;
  private String attrNowrap;
  private String attrId;
  private String attrBgcolor;
  private String attrStringCharactere;
  private String attrOnmouseover;
  private String attrWidth;
  private String attrColspan;
  private String attrValign;
  private String attrOnmouseout;
  private String attrRowspan;
  private String attrClass;
  private String attrOnclick;

  public TagTd() {
  }

  public int doStartTag() {
    JspWriter out = pageContext.getOut();
    ServletRequest request = pageContext.getRequest();
    HttpSession session = pageContext.getSession();
    try {
      out.print("<TD");
      if (UtilString.isNotEmpty(getAttrHeight()))
        out.print(" HEIGHT="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrHeight(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrBackground()))
        out.print(" BACKGROUND="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrBackground(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrContent()))
        out.print(" CONTENT="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrContent(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrAlign()))
        out.print(" ALIGN="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrAlign(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrNowrap()))
        out.print(" NOWRAP="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrNowrap(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrId()))
        out.print(" ID="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrId(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrBgcolor()))
        out.print(" BGCOLOR="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrBgcolor(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrStringCharactere()))
        out.print(" STRINGCHARACTERE="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrStringCharactere(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrOnmouseover()))
        out.print(" ONMOUSEOVER="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrOnmouseover(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrWidth()))
        out.print(" WIDTH="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrWidth(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrColspan()))
        out.print(" COLSPAN="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrColspan(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrValign()))
        out.print(" VALIGN="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrValign(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrOnmouseout()))
        out.print(" ONMOUSEOUT="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrOnmouseout(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrRowspan()))
        out.print(" ROWSPAN="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrRowspan(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrClass()))
          out.print(" CLASS="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrClass(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrOnclick()))
          out.print(" ONCLICK="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrOnclick(), request, session, "")+getStringCharactere());
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
      out.print("</TD>");
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
 * @return  the attrHeight
 * @uml.property  name="attrHeight"
 */
public String getAttrHeight() {
    return attrHeight;
  }
  /**
 * @param attrHeight  the attrHeight to set
 * @uml.property  name="attrHeight"
 */
public void setAttrHeight(String attrHeight) {
    this.attrHeight = attrHeight;
  }
  /**
 * @return  the attrBackground
 * @uml.property  name="attrBackground"
 */
public String getAttrBackground() {
    return attrBackground;
  }
  /**
 * @param attrBackground  the attrBackground to set
 * @uml.property  name="attrBackground"
 */
public void setAttrBackground(String attrBackground) {
    this.attrBackground = attrBackground;
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
 * @return  the attrAlign
 * @uml.property  name="attrAlign"
 */
public String getAttrAlign() {
    return attrAlign;
  }
  /**
 * @param attrAlign  the attrAlign to set
 * @uml.property  name="attrAlign"
 */
public void setAttrAlign(String attrAlign) {
    this.attrAlign = attrAlign;
  }
  /**
 * @return  the attrNowrap
 * @uml.property  name="attrNowrap"
 */
public String getAttrNowrap() {
    return attrNowrap;
  }
  /**
 * @param attrNowrap  the attrNowrap to set
 * @uml.property  name="attrNowrap"
 */
public void setAttrNowrap(String attrNowrap) {
    this.attrNowrap = attrNowrap;
  }
  /**
 * @return  the attrId
 * @uml.property  name="attrId"
 */
public String getAttrId() {
    return attrId;
  }
  /**
 * @param attrId  the attrId to set
 * @uml.property  name="attrId"
 */
public void setAttrId(String attrId) {
    this.attrId = attrId;
  }
  /**
 * @return  the attrBgcolor
 * @uml.property  name="attrBgcolor"
 */
public String getAttrBgcolor() {
    return attrBgcolor;
  }
  /**
 * @param attrBgcolor  the attrBgcolor to set
 * @uml.property  name="attrBgcolor"
 */
public void setAttrBgcolor(String attrBgcolor) {
    this.attrBgcolor = attrBgcolor;
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
 * @return  the attrOnmouseover
 * @uml.property  name="attrOnmouseover"
 */
public String getAttrOnmouseover() {
    return attrOnmouseover;
  }
  /**
 * @param attrOnmouseover  the attrOnmouseover to set
 * @uml.property  name="attrOnmouseover"
 */
public void setAttrOnmouseover(String attrOnmouseover) {
    this.attrOnmouseover = attrOnmouseover;
  }
  /**
 * @return  the attrWidth
 * @uml.property  name="attrWidth"
 */
public String getAttrWidth() {
    return attrWidth;
  }
  /**
 * @param attrWidth  the attrWidth to set
 * @uml.property  name="attrWidth"
 */
public void setAttrWidth(String attrWidth) {
    this.attrWidth = attrWidth;
  }
  /**
 * @return  the attrColspan
 * @uml.property  name="attrColspan"
 */
public String getAttrColspan() {
    return attrColspan;
  }
  /**
 * @param attrColspan  the attrColspan to set
 * @uml.property  name="attrColspan"
 */
public void setAttrColspan(String attrColspan) {
    this.attrColspan = attrColspan;
  }
  /**
 * @return  the attrValign
 * @uml.property  name="attrValign"
 */
public String getAttrValign() {
    return attrValign;
  }
  /**
 * @param attrValign  the attrValign to set
 * @uml.property  name="attrValign"
 */
public void setAttrValign(String attrValign) {
    this.attrValign = attrValign;
  }
  /**
 * @return  the attrOnmouseout
 * @uml.property  name="attrOnmouseout"
 */
public String getAttrOnmouseout() {
    return attrOnmouseout;
  }
  /**
 * @param attrOnmouseout  the attrOnmouseout to set
 * @uml.property  name="attrOnmouseout"
 */
public void setAttrOnmouseout(String attrOnmouseout) {
    this.attrOnmouseout = attrOnmouseout;
  }
  /**
 * @return  the attrRowspan
 * @uml.property  name="attrRowspan"
 */
public String getAttrRowspan() {
    return attrRowspan;
  }
  /**
 * @param attrRowspan  the attrRowspan to set
 * @uml.property  name="attrRowspan"
 */
public void setAttrRowspan(String attrRowspan) {
    this.attrRowspan = attrRowspan;
  }
/**
 * @return  the attrClass
 * @uml.property  name="attrClass"
 */
public String getAttrClass() {
    return attrClass;
  }
  /**
 * @param attrClass  the attrClass to set
 * @uml.property  name="attrClass"
 */
public void setAttrClass(String attrClass) {
    this.attrClass = attrClass;
  }
/**
 * @return  the attrOnclick
 * @uml.property  name="attrOnclick"
 */
public String getAttrOnclick() {
    return attrOnclick;
  }
  /**
 * @param attrOnclick  the attrOnClick to set
 * @uml.property  name="attrOnclick"
 */
public void setAttrOnclick(String attrOnclick) {
    this.attrOnclick = attrOnclick;
  }

  public String toHtml() {
    StringBuffer ret = new StringBuffer("<TD");
    if(UtilString.isNotEmpty(getAttrHeight())) ret.append(" height=\"").append(getAttrHeight()).append("\"");
    if(UtilString.isNotEmpty(getAttrBackground())) ret.append(" background=\"").append(getAttrBackground()).append("\"");
    if(UtilString.isNotEmpty(getAttrContent())) ret.append(" content=\"").append(getAttrContent()).append("\"");
    if(UtilString.isNotEmpty(getAttrAlign())) ret.append(" align=\"").append(getAttrAlign()).append("\"");
    if(UtilString.isNotEmpty(getAttrNowrap())) ret.append(" nowrap=\"").append(getAttrNowrap()).append("\"");
    if(UtilString.isNotEmpty(getAttrId())) ret.append(" id=\"").append(getAttrId()).append("\"");
    if(UtilString.isNotEmpty(getAttrBgcolor())) ret.append(" bgcolor=\"").append(getAttrBgcolor()).append("\"");
    if(UtilString.isNotEmpty(getAttrStringCharactere())) ret.append(" stringCharactere=\"").append(getAttrStringCharactere()).append("\"");
    if(UtilString.isNotEmpty(getAttrOnmouseover())) ret.append(" onmouseover=\"").append(getAttrOnmouseover()).append("\"");
    if(UtilString.isNotEmpty(getAttrWidth())) ret.append(" width=\"").append(getAttrWidth()).append("\"");
    if(UtilString.isNotEmpty(getAttrColspan())) ret.append(" colspan=\"").append(getAttrColspan()).append("\"");
    if(UtilString.isNotEmpty(getAttrValign())) ret.append(" valign=\"").append(getAttrValign()).append("\"");
    if(UtilString.isNotEmpty(getAttrOnmouseout())) ret.append(" onmouseout=\"").append(getAttrOnmouseout()).append("\"");
    if(UtilString.isNotEmpty(getAttrRowspan())) ret.append(" rowspan=\"").append(getAttrRowspan()).append("\"");
    if(UtilString.isNotEmpty(getAttrClass())) ret.append(" class=\"").append(getAttrClass()).append("\"");
    if(UtilString.isNotEmpty(getAttrOnclick())) ret.append(" onclick=\"").append(getAttrOnclick()).append("\"");
    ret.append(">");
    if (getContent()!=null) ret.append(getContent());
    ret.append("</TD>");
    return ret.toString();
  }
  public String toJsp() {
    StringBuffer ret = new StringBuffer("<html:TagTd");
    if(UtilString.isNotEmpty(getAttrHeight())) ret.append(" attrHeight=").append(getStringCharactere()).append(getAttrHeight()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrBackground())) ret.append(" attrBackground=").append(getStringCharactere()).append(getAttrBackground()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrContent())) ret.append(" attrContent=").append(getStringCharactere()).append(getAttrContent()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrAlign())) ret.append(" attrAlign=").append(getStringCharactere()).append(getAttrAlign()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrNowrap())) ret.append(" attrNowrap=").append(getStringCharactere()).append(getAttrNowrap()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrId())) ret.append(" attrId=").append(getStringCharactere()).append(getAttrId()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrBgcolor())) ret.append(" attrBgcolor=").append(getStringCharactere()).append(getAttrBgcolor()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrStringCharactere())) ret.append(" attrStringCharactere=").append(getStringCharactere()).append(getAttrStringCharactere()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrOnmouseover())) ret.append(" attrOnmouseover=").append(getStringCharactere()).append(getAttrOnmouseover()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrWidth())) ret.append(" attrWidth=").append(getStringCharactere()).append(getAttrWidth()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrColspan())) ret.append(" attrColspan=").append(getStringCharactere()).append(getAttrColspan()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrValign())) ret.append(" attrValign=").append(getStringCharactere()).append(getAttrValign()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrOnmouseout())) ret.append(" attrOnmouseout=").append(getStringCharactere()).append(getAttrOnmouseout()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrRowspan())) ret.append(" attrRowspan=").append(getStringCharactere()).append(getAttrRowspan()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrClass())) ret.append(" attrClass=").append(getStringCharactere()).append(getAttrClass()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrOnclick())) ret.append(" attrOnclick=").append(getStringCharactere()).append(getAttrOnclick()).append(getStringCharactere());
    ret.append(">\r\n");
    if (getContent()!=null) ret.append(getContent());
    ret.append("</html:TagTd>\r\n");
    return ret.toString();
  }
}
