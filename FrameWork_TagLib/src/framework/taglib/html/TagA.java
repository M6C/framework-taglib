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
public class TagA extends BodyTagSupport {

  public static final String CONST_TYPE="A";
  public static final boolean CONST_HAS_ENDTAG=true;

  private String content=null;
  private String stringCharactere=null;
  private String attrIpt;
  private String attrContent;
  private String attrSrc;
  private String attrId;
  private String attrRef;
  private String attrOnclick;
  private String attrFalse;
  private String attrStringCharactere;
  private String attrHref;
  private String attrTarget;
  private String attrStyle;
  private String attrOnmouseover;
  private String attrOnmouseout;
  private String attrClass;
  private String attrName;

  public TagA() {
  }

  public int doStartTag() {
    JspWriter out = pageContext.getOut();
    ServletRequest request = pageContext.getRequest();
    HttpSession session = pageContext.getSession();
    try {
      out.print("<A");
      if (UtilString.isNotEmpty(getAttrIpt()))
        out.print(" IPT="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrIpt(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrContent()))
        out.print(" CONTENT="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrContent(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrSrc()))
        out.print(" SRC="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrSrc(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrId()))
        out.print(" ID="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrId(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrRef()))
        out.print(" REF="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrRef(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrOnclick()))
        out.print(" ONCLICK="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrOnclick(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrFalse()))
        out.print(" FALSE="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrFalse(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrStringCharactere()))
        out.print(" STRINGCHARACTERE="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrStringCharactere(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrHref()))
        out.print(" HREF="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrHref(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrTarget()))
        out.print(" TARGET="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrTarget(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrStyle()))
        out.print(" STYLE="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrStyle(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrOnmouseover()))
        out.print(" ONMOUSEOVER="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrOnmouseover(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrOnmouseout()))
        out.print(" ONMOUSEOUT="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrOnmouseout(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrClass()))
        out.print(" CLASS="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrClass(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrName()))
        out.print(" NAME="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrName(), request, session, "")+getStringCharactere());
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
      out.print("</A>");
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
 * @return  the attrIpt
 * @uml.property  name="attrIpt"
 */
public String getAttrIpt() {
    return attrIpt;
  }
  /**
 * @param attrIpt  the attrIpt to set
 * @uml.property  name="attrIpt"
 */
public void setAttrIpt(String attrIpt) {
    this.attrIpt = attrIpt;
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
 * @return  the attrSrc
 * @uml.property  name="attrSrc"
 */
public String getAttrSrc() {
    return attrSrc;
  }
  /**
 * @param attrSrc  the attrSrc to set
 * @uml.property  name="attrSrc"
 */
public void setAttrSrc(String attrSrc) {
    this.attrSrc = attrSrc;
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
 * @return  the attrRef
 * @uml.property  name="attrRef"
 */
public String getAttrRef() {
    return attrRef;
  }
  /**
 * @param attrRef  the attrRef to set
 * @uml.property  name="attrRef"
 */
public void setAttrRef(String attrRef) {
    this.attrRef = attrRef;
  }
  /**
 * @return  the attrOnclick
 * @uml.property  name="attrOnclick"
 */
public String getAttrOnclick() {
    return attrOnclick;
  }
  /**
 * @param attrOnclick  the attrOnclick to set
 * @uml.property  name="attrOnclick"
 */
public void setAttrOnclick(String attrOnclick) {
    this.attrOnclick = attrOnclick;
  }
  /**
 * @return  the attrFalse
 * @uml.property  name="attrFalse"
 */
public String getAttrFalse() {
    return attrFalse;
  }
  /**
 * @param attrFalse  the attrFalse to set
 * @uml.property  name="attrFalse"
 */
public void setAttrFalse(String attrFalse) {
    this.attrFalse = attrFalse;
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
 * @return  the attrHref
 * @uml.property  name="attrHref"
 */
public String getAttrHref() {
    return attrHref;
  }
  /**
 * @param attrHref  the attrHref to set
 * @uml.property  name="attrHref"
 */
public void setAttrHref(String attrHref) {
    this.attrHref = attrHref;
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
 * @return  the attrStyle
 * @uml.property  name="attrStyle"
 */
public String getAttrStyle() {
    return attrStyle;
  }
  /**
 * @param attrStyle  the attrStyle to set
 * @uml.property  name="attrStyle"
 */
public void setAttrStyle(String attrStyle) {
    this.attrStyle = attrStyle;
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

  public String toHtml() {
    StringBuffer ret = new StringBuffer("<A");
    if(UtilString.isNotEmpty(getAttrIpt())) ret.append(" ipt=\"").append(getAttrIpt()).append("\"");
    if(UtilString.isNotEmpty(getAttrContent())) ret.append(" content=\"").append(getAttrContent()).append("\"");
    if(UtilString.isNotEmpty(getAttrSrc())) ret.append(" src=\"").append(getAttrSrc()).append("\"");
    if(UtilString.isNotEmpty(getAttrId())) ret.append(" id=\"").append(getAttrId()).append("\"");
    if(UtilString.isNotEmpty(getAttrRef())) ret.append(" ref=\"").append(getAttrRef()).append("\"");
    if(UtilString.isNotEmpty(getAttrOnclick())) ret.append(" onclick=\"").append(getAttrOnclick()).append("\"");
    if(UtilString.isNotEmpty(getAttrFalse())) ret.append(" false=\"").append(getAttrFalse()).append("\"");
    if(UtilString.isNotEmpty(getAttrStringCharactere())) ret.append(" stringCharactere=\"").append(getAttrStringCharactere()).append("\"");
    if(UtilString.isNotEmpty(getAttrHref())) ret.append(" href=\"").append(getAttrHref()).append("\"");
    if(UtilString.isNotEmpty(getAttrTarget())) ret.append(" target=\"").append(getAttrTarget()).append("\"");
    if(UtilString.isNotEmpty(getAttrStyle())) ret.append(" style=\"").append(getAttrStyle()).append("\"");
    if(UtilString.isNotEmpty(getAttrOnmouseover())) ret.append(" onmouseover=\"").append(getAttrOnmouseover()).append("\"");
    if(UtilString.isNotEmpty(getAttrOnmouseout())) ret.append(" onmouseout=\"").append(getAttrOnmouseout()).append("\"");
    if(UtilString.isNotEmpty(getAttrClass())) ret.append(" class=\"").append(getAttrClass()).append("\"");
    ret.append(">");
    if (getContent()!=null) ret.append(getContent());
    ret.append("</A>");
    return ret.toString();
  }
  public String toJsp() {
    StringBuffer ret = new StringBuffer("<html:TagA");
    if(UtilString.isNotEmpty(getAttrIpt())) ret.append(" attrIpt=").append(getStringCharactere()).append(getAttrIpt()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrContent())) ret.append(" attrContent=").append(getStringCharactere()).append(getAttrContent()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrSrc())) ret.append(" attrSrc=").append(getStringCharactere()).append(getAttrSrc()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrId())) ret.append(" attrId=").append(getStringCharactere()).append(getAttrId()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrRef())) ret.append(" attrRef=").append(getStringCharactere()).append(getAttrRef()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrOnclick())) ret.append(" attrOnclick=").append(getStringCharactere()).append(getAttrOnclick()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrFalse())) ret.append(" attrFalse=").append(getStringCharactere()).append(getAttrFalse()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrStringCharactere())) ret.append(" attrStringCharactere=").append(getStringCharactere()).append(getAttrStringCharactere()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrHref())) ret.append(" attrHref=").append(getStringCharactere()).append(getAttrHref()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrTarget())) ret.append(" attrTarget=").append(getStringCharactere()).append(getAttrTarget()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrStyle())) ret.append(" attrStyle=").append(getStringCharactere()).append(getAttrStyle()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrOnmouseover())) ret.append(" attrOnmouseover=").append(getStringCharactere()).append(getAttrOnmouseover()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrOnmouseout())) ret.append(" attrOnmouseout=").append(getStringCharactere()).append(getAttrOnmouseout()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrClass())) ret.append(" attrClass=").append(getStringCharactere()).append(getAttrClass()).append(getStringCharactere());
    ret.append(">\r\n");
    if (getContent()!=null) ret.append(getContent());
    ret.append("</html:TagA>\r\n");
    return ret.toString();
  }
}
