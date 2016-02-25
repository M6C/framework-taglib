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
public class TagTr extends BodyTagSupport {

  public static final String CONST_TYPE="TR";
  public static final boolean CONST_HAS_ENDTAG=true;

  private String content=null;
  private String stringCharactere=null;
  private String attrHeight;
  private String attrBackground;
  private String attrMethod;
  private String attrContent;
  private String attrAlign;
  private String attrAction;
  private String attrLanguage;
  private String attrBgcolor;
  private String attrOnsubmit;
  private String attrStringCharactere;
  private String attrName;
  private String attrTarget;
  private String attrWidth;
  private String attrValign;
  private String attrClass;

  public TagTr() {
  }

  public int doStartTag() {
    JspWriter out = pageContext.getOut();
    ServletRequest request = pageContext.getRequest();
    HttpSession session = pageContext.getSession();
    try {
      out.print("<TR");
      if (UtilString.isNotEmpty(getAttrHeight()))
        out.print(" HEIGHT="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrHeight(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrBackground()))
        out.print(" BACKGROUND="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrBackground(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrMethod()))
        out.print(" METHOD="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrMethod(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrContent()))
        out.print(" CONTENT="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrContent(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrAlign()))
        out.print(" ALIGN="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrAlign(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrAction()))
        out.print(" ACTION="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrAction(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrLanguage()))
        out.print(" LANGUAGE="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrLanguage(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrBgcolor()))
        out.print(" BGCOLOR="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrBgcolor(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrOnsubmit()))
        out.print(" ONSUBMIT="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrOnsubmit(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrStringCharactere()))
        out.print(" STRINGCHARACTERE="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrStringCharactere(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrName()))
        out.print(" NAME="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrName(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrTarget()))
        out.print(" TARGET="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrTarget(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrWidth()))
        out.print(" WIDTH="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrWidth(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrValign()))
        out.print(" VALIGN="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrValign(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrClass()))
        out.print(" CLASS="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrClass(), request, session, "")+getStringCharactere());
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
      out.print("</TR>");
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
 * @return  the attrMethod
 * @uml.property  name="attrMethod"
 */
public String getAttrMethod() {
    return attrMethod;
  }
  /**
 * @param attrMethod  the attrMethod to set
 * @uml.property  name="attrMethod"
 */
public void setAttrMethod(String attrMethod) {
    this.attrMethod = attrMethod;
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
 * @return  the attrAction
 * @uml.property  name="attrAction"
 */
public String getAttrAction() {
    return attrAction;
  }
  /**
 * @param attrAction  the attrAction to set
 * @uml.property  name="attrAction"
 */
public void setAttrAction(String attrAction) {
    this.attrAction = attrAction;
  }
  /**
 * @return  the attrLanguage
 * @uml.property  name="attrLanguage"
 */
public String getAttrLanguage() {
    return attrLanguage;
  }
  /**
 * @param attrLanguage  the attrLanguage to set
 * @uml.property  name="attrLanguage"
 */
public void setAttrLanguage(String attrLanguage) {
    this.attrLanguage = attrLanguage;
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
 * @return  the attrOnsubmit
 * @uml.property  name="attrOnsubmit"
 */
public String getAttrOnsubmit() {
    return attrOnsubmit;
  }
  /**
 * @param attrOnsubmit  the attrOnsubmit to set
 * @uml.property  name="attrOnsubmit"
 */
public void setAttrOnsubmit(String attrOnsubmit) {
    this.attrOnsubmit = attrOnsubmit;
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
 * @return  the attrName
 * @uml.property  name="attrName"
 */
public String getAttrName() {
    return attrName;
  }
  /**
 * @param attrName  the attrName to set
 * @uml.property  name="attrName"
 */
public void setAttrName(String attrName) {
    this.attrName = attrName;
  }
  /**
 * @return  the attrTarget
 * @uml.property  name="attrTarget"
 */
public String getAttrTarget() {
    return attrTarget;
  }
  /**
 * @param attrTarget  the attrTarget to set
 * @uml.property  name="attrTarget"
 */
public void setAttrTarget(String attrTarget) {
    this.attrTarget = attrTarget;
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

  public String toHtml() {
    StringBuffer ret = new StringBuffer("<TR");
    if(UtilString.isNotEmpty(getAttrHeight())) ret.append(" height=\"").append(getAttrHeight()).append("\"");
    if(UtilString.isNotEmpty(getAttrBackground())) ret.append(" background=\"").append(getAttrBackground()).append("\"");
    if(UtilString.isNotEmpty(getAttrMethod())) ret.append(" method=\"").append(getAttrMethod()).append("\"");
    if(UtilString.isNotEmpty(getAttrContent())) ret.append(" content=\"").append(getAttrContent()).append("\"");
    if(UtilString.isNotEmpty(getAttrAlign())) ret.append(" align=\"").append(getAttrAlign()).append("\"");
    if(UtilString.isNotEmpty(getAttrAction())) ret.append(" action=\"").append(getAttrAction()).append("\"");
    if(UtilString.isNotEmpty(getAttrLanguage())) ret.append(" language=\"").append(getAttrLanguage()).append("\"");
    if(UtilString.isNotEmpty(getAttrBgcolor())) ret.append(" bgcolor=\"").append(getAttrBgcolor()).append("\"");
    if(UtilString.isNotEmpty(getAttrOnsubmit())) ret.append(" onsubmit=\"").append(getAttrOnsubmit()).append("\"");
    if(UtilString.isNotEmpty(getAttrStringCharactere())) ret.append(" stringCharactere=\"").append(getAttrStringCharactere()).append("\"");
    if(UtilString.isNotEmpty(getAttrName())) ret.append(" name=\"").append(getAttrName()).append("\"");
    if(UtilString.isNotEmpty(getAttrTarget())) ret.append(" target=\"").append(getAttrTarget()).append("\"");
    if(UtilString.isNotEmpty(getAttrWidth())) ret.append(" width=\"").append(getAttrWidth()).append("\"");
    if(UtilString.isNotEmpty(getAttrValign())) ret.append(" valign=\"").append(getAttrValign()).append("\"");
    if(UtilString.isNotEmpty(getAttrClass())) ret.append(" class=\"").append(getAttrClass()).append("\"");
    ret.append(">");
    if (getContent()!=null) ret.append(getContent());
    ret.append("</TR>");
    return ret.toString();
  }
  public String toJsp() {
    StringBuffer ret = new StringBuffer("<html:TagTr");
    if(UtilString.isNotEmpty(getAttrHeight())) ret.append(" attrHeight=").append(getStringCharactere()).append(getAttrHeight()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrBackground())) ret.append(" attrBackground=").append(getStringCharactere()).append(getAttrBackground()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrMethod())) ret.append(" attrMethod=").append(getStringCharactere()).append(getAttrMethod()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrContent())) ret.append(" attrContent=").append(getStringCharactere()).append(getAttrContent()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrAlign())) ret.append(" attrAlign=").append(getStringCharactere()).append(getAttrAlign()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrAction())) ret.append(" attrAction=").append(getStringCharactere()).append(getAttrAction()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrLanguage())) ret.append(" attrLanguage=").append(getStringCharactere()).append(getAttrLanguage()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrBgcolor())) ret.append(" attrBgcolor=").append(getStringCharactere()).append(getAttrBgcolor()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrOnsubmit())) ret.append(" attrOnsubmit=").append(getStringCharactere()).append(getAttrOnsubmit()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrStringCharactere())) ret.append(" attrStringCharactere=").append(getStringCharactere()).append(getAttrStringCharactere()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrName())) ret.append(" attrName=").append(getStringCharactere()).append(getAttrName()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrTarget())) ret.append(" attrTarget=").append(getStringCharactere()).append(getAttrTarget()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrWidth())) ret.append(" attrWidth=").append(getStringCharactere()).append(getAttrWidth()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrValign())) ret.append(" attrValign=").append(getStringCharactere()).append(getAttrValign()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrClass())) ret.append(" attrClass=").append(getStringCharactere()).append(getAttrClass()).append(getStringCharactere());
    ret.append(">\r\n");
    if (getContent()!=null) ret.append(getContent());
    ret.append("</html:TagTr>\r\n");
    return ret.toString();
  }
}
