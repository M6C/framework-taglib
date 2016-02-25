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
public class TagImg extends TagSupport {

  public static final String CONST_TYPE="IMG";
  public static final boolean CONST_HAS_ENDTAG=false;

  private String stringCharactere=null;
  private String attrHeight;
  private String attrHspace;
  private String attrAlign;
  private String attrVspace;
  private String attrSrc;
  private String attrBorder;
  private String attrOnclick;
  private String attrStringCharactere;
  private String attrName;
  private String attrUsemap;
  private String attrWidth;
  private String attrAlt;
  private String attrValign;
  private String attrClass;

  public TagImg() {
  }

  public int doStartTag() {
    JspWriter out = pageContext.getOut();
    ServletRequest request = pageContext.getRequest();
    HttpSession session = pageContext.getSession();
    try {
      out.print("<IMG");
      if (UtilString.isNotEmpty(getAttrHeight()))
        out.print(" HEIGHT="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrHeight(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrHspace()))
        out.print(" HSPACE="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrHspace(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrAlign()))
        out.print(" ALIGN="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrAlign(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrVspace()))
        out.print(" VSPACE="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrVspace(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrSrc()))
        out.print(" SRC="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrSrc(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrBorder()))
        out.print(" BORDER="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrBorder(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrOnclick()))
        out.print(" ONCLICK="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrOnclick(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrStringCharactere()))
        out.print(" STRINGCHARACTERE="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrStringCharactere(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrName()))
        out.print(" NAME="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrName(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrUsemap()))
        out.print(" USEMAP="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrUsemap(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrWidth()))
        out.print(" WIDTH="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrWidth(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrAlt()))
        out.print(" ALT="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrAlt(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrValign()))
        out.print(" VALIGN="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrValign(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrClass()))
        out.print(" CLASS="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrClass(), request, session, "")+getStringCharactere());
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
 * @return  the attrHspace
 * @uml.property  name="attrHspace"
 */
public String getAttrHspace() {
    return attrHspace;
  }
  /**
 * @param attrHspace  the attrHspace to set
 * @uml.property  name="attrHspace"
 */
public void setAttrHspace(String attrHspace) {
    this.attrHspace = attrHspace;
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
 * @return  the attrVspace
 * @uml.property  name="attrVspace"
 */
public String getAttrVspace() {
    return attrVspace;
  }
  /**
 * @param attrVspace  the attrVspace to set
 * @uml.property  name="attrVspace"
 */
public void setAttrVspace(String attrVspace) {
    this.attrVspace = attrVspace;
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
 * @return  the attrUsemap
 * @uml.property  name="attrUsemap"
 */
public String getAttrUsemap() {
    return attrUsemap;
  }
  /**
 * @param attrUsemap  the attrUsemap to set
 * @uml.property  name="attrUsemap"
 */
public void setAttrUsemap(String attrUsemap) {
    this.attrUsemap = attrUsemap;
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
 * @return  the attrAlt
 * @uml.property  name="attrAlt"
 */
public String getAttrAlt() {
    return attrAlt;
  }
  /**
 * @param attrAlt  the attrAlt to set
 * @uml.property  name="attrAlt"
 */
public void setAttrAlt(String attrAlt) {
    this.attrAlt = attrAlt;
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
    StringBuffer ret = new StringBuffer("<IMG");
    if(UtilString.isNotEmpty(getAttrHeight())) ret.append(" height=\"").append(getAttrHeight()).append("\"");
    if(UtilString.isNotEmpty(getAttrHspace())) ret.append(" hspace=\"").append(getAttrHspace()).append("\"");
    if(UtilString.isNotEmpty(getAttrAlign())) ret.append(" align=\"").append(getAttrAlign()).append("\"");
    if(UtilString.isNotEmpty(getAttrVspace())) ret.append(" vspace=\"").append(getAttrVspace()).append("\"");
    if(UtilString.isNotEmpty(getAttrSrc())) ret.append(" src=\"").append(getAttrSrc()).append("\"");
    if(UtilString.isNotEmpty(getAttrBorder())) ret.append(" border=\"").append(getAttrBorder()).append("\"");
    if(UtilString.isNotEmpty(getAttrOnclick())) ret.append(" onclick=\"").append(getAttrOnclick()).append("\"");
    if(UtilString.isNotEmpty(getAttrStringCharactere())) ret.append(" stringCharactere=\"").append(getAttrStringCharactere()).append("\"");
    if(UtilString.isNotEmpty(getAttrName())) ret.append(" name=\"").append(getAttrName()).append("\"");
    if(UtilString.isNotEmpty(getAttrUsemap())) ret.append(" usemap=\"").append(getAttrUsemap()).append("\"");
    if(UtilString.isNotEmpty(getAttrWidth())) ret.append(" width=\"").append(getAttrWidth()).append("\"");
    if(UtilString.isNotEmpty(getAttrAlt())) ret.append(" alt=\"").append(getAttrAlt()).append("\"");
    if(UtilString.isNotEmpty(getAttrValign())) ret.append(" valign=\"").append(getAttrValign()).append("\"");
    if(UtilString.isNotEmpty(getAttrClass())) ret.append(" class=\"").append(getAttrClass()).append("\"");
    ret.append("\\>");
    return ret.toString();
  }
  public String toJsp() {
    StringBuffer ret = new StringBuffer("<html:TagImg");
    if(UtilString.isNotEmpty(getAttrHeight())) ret.append(" attrHeight=").append(getStringCharactere()).append(getAttrHeight()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrHspace())) ret.append(" attrHspace=").append(getStringCharactere()).append(getAttrHspace()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrAlign())) ret.append(" attrAlign=").append(getStringCharactere()).append(getAttrAlign()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrVspace())) ret.append(" attrVspace=").append(getStringCharactere()).append(getAttrVspace()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrSrc())) ret.append(" attrSrc=").append(getStringCharactere()).append(getAttrSrc()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrBorder())) ret.append(" attrBorder=").append(getStringCharactere()).append(getAttrBorder()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrOnclick())) ret.append(" attrOnclick=").append(getStringCharactere()).append(getAttrOnclick()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrStringCharactere())) ret.append(" attrStringCharactere=").append(getStringCharactere()).append(getAttrStringCharactere()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrName())) ret.append(" attrName=").append(getStringCharactere()).append(getAttrName()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrUsemap())) ret.append(" attrUsemap=").append(getStringCharactere()).append(getAttrUsemap()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrWidth())) ret.append(" attrWidth=").append(getStringCharactere()).append(getAttrWidth()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrAlt())) ret.append(" attrAlt=").append(getStringCharactere()).append(getAttrAlt()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrValign())) ret.append(" attrValign=").append(getStringCharactere()).append(getAttrValign()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrClass())) ret.append(" attrClass=").append(getStringCharactere()).append(getAttrClass()).append(getStringCharactere());
    ret.append("/>\r\n");
    return ret.toString();
  }
}
