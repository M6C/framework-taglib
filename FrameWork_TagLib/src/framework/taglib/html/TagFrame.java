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
public class TagFrame extends TagSupport {

  public static final String CONST_TYPE="FRAME";
  public static final boolean CONST_HAS_ENDTAG=false;

  private String stringCharactere=null;
  private String attrScrolling;
  private String attrNoresize;
  private String attrFrameborder;
  private String attrSrc;
  private String attrName;
  private String attrStringCharactere;
  private String attrMarginwidth;
  private String attrMarginheight;

  public TagFrame() {
  }

  public int doStartTag() {
    JspWriter out = pageContext.getOut();
    ServletRequest request = pageContext.getRequest();
    HttpSession session = pageContext.getSession();
    try {
      out.print("<FRAME");
      if (UtilString.isNotEmpty(getAttrScrolling()))
        out.print(" SCROLLING="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrScrolling(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrNoresize()))
        out.print(" NORESIZE="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrNoresize(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrFrameborder()))
        out.print(" FRAMEBORDER="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrFrameborder(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrSrc()))
        out.print(" SRC="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrSrc(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrName()))
        out.print(" NAME="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrName(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrStringCharactere()))
        out.print(" STRINGCHARACTERE="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrStringCharactere(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrMarginwidth()))
        out.print(" MARGINWIDTH="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrMarginwidth(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrMarginheight()))
        out.print(" MARGINHEIGHT="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrMarginheight(), request, session, "")+getStringCharactere());
      out.print("/>");
    } catch(IOException ioe) {
      Trace.ERROR("Error in HeadingTag: ", ioe);
    }
    return EVAL_BODY_INCLUDE;
  }

  public int doEndTag() {
    JspWriter out = pageContext.getOut();
    ServletRequest request = pageContext.getRequest();
    HttpSession session = pageContext.getSession();
    return EVAL_PAGE;
  }

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
 * @return  the attrScrolling
 * @uml.property  name="attrScrolling"
 */
public String getAttrScrolling() {
    return attrScrolling;
  }
  /**
 * @param attrScrolling  the attrScrolling to set
 * @uml.property  name="attrScrolling"
 */
public void setAttrScrolling(String attrScrolling) {
    this.attrScrolling = attrScrolling;
  }
  /**
 * @return  the attrNoresize
 * @uml.property  name="attrNoresize"
 */
public String getAttrNoresize() {
    return attrNoresize;
  }
  /**
 * @param attrNoresize  the attrNoresize to set
 * @uml.property  name="attrNoresize"
 */
public void setAttrNoresize(String attrNoresize) {
    this.attrNoresize = attrNoresize;
  }
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
 * @return  the attrMarginwidth
 * @uml.property  name="attrMarginwidth"
 */
public String getAttrMarginwidth() {
    return attrMarginwidth;
  }
  /**
 * @param attrMarginwidth  the attrMarginwidth to set
 * @uml.property  name="attrMarginwidth"
 */
public void setAttrMarginwidth(String attrMarginwidth) {
    this.attrMarginwidth = attrMarginwidth;
  }
  /**
 * @return  the attrMarginheight
 * @uml.property  name="attrMarginheight"
 */
public String getAttrMarginheight() {
    return attrMarginheight;
  }
  /**
 * @param attrMarginheight  the attrMarginheight to set
 * @uml.property  name="attrMarginheight"
 */
public void setAttrMarginheight(String attrMarginheight) {
    this.attrMarginheight = attrMarginheight;
  }

  public String toHtml() {
    StringBuffer ret = new StringBuffer("<FRAME");
    if(UtilString.isNotEmpty(getAttrScrolling())) ret.append(" scrolling=\"").append(getAttrScrolling()).append("\"");
    if(UtilString.isNotEmpty(getAttrNoresize())) ret.append(" noresize=\"").append(getAttrNoresize()).append("\"");
    if(UtilString.isNotEmpty(getAttrFrameborder())) ret.append(" frameborder=\"").append(getAttrFrameborder()).append("\"");
    if(UtilString.isNotEmpty(getAttrSrc())) ret.append(" src=\"").append(getAttrSrc()).append("\"");
    if(UtilString.isNotEmpty(getAttrName())) ret.append(" name=\"").append(getAttrName()).append("\"");
    if(UtilString.isNotEmpty(getAttrStringCharactere())) ret.append(" stringCharactere=\"").append(getAttrStringCharactere()).append("\"");
    if(UtilString.isNotEmpty(getAttrMarginwidth())) ret.append(" marginwidth=\"").append(getAttrMarginwidth()).append("\"");
    if(UtilString.isNotEmpty(getAttrMarginheight())) ret.append(" marginheight=\"").append(getAttrMarginheight()).append("\"");
    ret.append("\\>");
    return ret.toString();
  }
  public String toJsp() {
    StringBuffer ret = new StringBuffer("<html:TagFrame");
    if(UtilString.isNotEmpty(getAttrScrolling())) ret.append(" attrScrolling=").append(getStringCharactere()).append(getAttrScrolling()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrNoresize())) ret.append(" attrNoresize=").append(getStringCharactere()).append(getAttrNoresize()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrFrameborder())) ret.append(" attrFrameborder=").append(getStringCharactere()).append(getAttrFrameborder()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrSrc())) ret.append(" attrSrc=").append(getStringCharactere()).append(getAttrSrc()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrName())) ret.append(" attrName=").append(getStringCharactere()).append(getAttrName()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrStringCharactere())) ret.append(" attrStringCharactere=").append(getStringCharactere()).append(getAttrStringCharactere()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrMarginwidth())) ret.append(" attrMarginwidth=").append(getStringCharactere()).append(getAttrMarginwidth()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrMarginheight())) ret.append(" attrMarginheight=").append(getStringCharactere()).append(getAttrMarginheight()).append(getStringCharactere());
    ret.append("/>\r\n");
    return ret.toString();
  }
}
