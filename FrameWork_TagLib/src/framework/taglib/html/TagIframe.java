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
public class TagIframe extends TagSupport {

  public static final String CONST_TYPE="IFRAME";
  public static final boolean CONST_HAS_ENDTAG=false;

  private String stringCharactere=null;
  private String attrHeight;
  private String attrMarginwidth;
  private String attrHspace;
  private String attrBordercolor;
  private String attrVspace;
  private String attrSrc;
  private String attrEndtag;
  private String attrFrameborder;
  private String attrStringCharactere;
  private String attrScrolling;
  private String attrNoresize;
  private String attrWidth;
  private String attrMarginheight;

  public TagIframe() {
  }

  public int doStartTag() {
    JspWriter out = pageContext.getOut();
    ServletRequest request = pageContext.getRequest();
    HttpSession session = pageContext.getSession();
    try {
      out.print("<IFRAME");
      if (UtilString.isNotEmpty(getAttrHeight()))
        out.print(" HEIGHT="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrHeight(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrMarginwidth()))
        out.print(" MARGINWIDTH="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrMarginwidth(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrHspace()))
        out.print(" HSPACE="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrHspace(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrBordercolor()))
        out.print(" BORDERCOLOR="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrBordercolor(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrVspace()))
        out.print(" VSPACE="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrVspace(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrSrc()))
        out.print(" SRC="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrSrc(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrEndtag()))
        out.print(" ENDTAG="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrEndtag(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrFrameborder()))
        out.print(" FRAMEBORDER="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrFrameborder(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrStringCharactere()))
        out.print(" STRINGCHARACTERE="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrStringCharactere(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrScrolling()))
        out.print(" SCROLLING="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrScrolling(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrNoresize()))
        out.print(" NORESIZE="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrNoresize(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrWidth()))
        out.print(" WIDTH="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrWidth(), request, session, "")+getStringCharactere());
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
 * @return  the attrBordercolor
 * @uml.property  name="attrBordercolor"
 */
public String getAttrBordercolor() {
    return attrBordercolor;
  }
  /**
 * @param attrBordercolor  the attrBordercolor to set
 * @uml.property  name="attrBordercolor"
 */
public void setAttrBordercolor(String attrBordercolor) {
    this.attrBordercolor = attrBordercolor;
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
 * @return  the attrEndtag
 * @uml.property  name="attrEndtag"
 */
public String getAttrEndtag() {
    return attrEndtag;
  }
  /**
 * @param attrEndtag  the attrEndtag to set
 * @uml.property  name="attrEndtag"
 */
public void setAttrEndtag(String attrEndtag) {
    this.attrEndtag = attrEndtag;
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
    StringBuffer ret = new StringBuffer("<IFRAME");
    if(UtilString.isNotEmpty(getAttrHeight())) ret.append(" height=\"").append(getAttrHeight()).append("\"");
    if(UtilString.isNotEmpty(getAttrMarginwidth())) ret.append(" marginwidth=\"").append(getAttrMarginwidth()).append("\"");
    if(UtilString.isNotEmpty(getAttrHspace())) ret.append(" hspace=\"").append(getAttrHspace()).append("\"");
    if(UtilString.isNotEmpty(getAttrBordercolor())) ret.append(" bordercolor=\"").append(getAttrBordercolor()).append("\"");
    if(UtilString.isNotEmpty(getAttrVspace())) ret.append(" vspace=\"").append(getAttrVspace()).append("\"");
    if(UtilString.isNotEmpty(getAttrSrc())) ret.append(" src=\"").append(getAttrSrc()).append("\"");
    if(UtilString.isNotEmpty(getAttrEndtag())) ret.append(" endtag=\"").append(getAttrEndtag()).append("\"");
    if(UtilString.isNotEmpty(getAttrFrameborder())) ret.append(" frameborder=\"").append(getAttrFrameborder()).append("\"");
    if(UtilString.isNotEmpty(getAttrStringCharactere())) ret.append(" stringCharactere=\"").append(getAttrStringCharactere()).append("\"");
    if(UtilString.isNotEmpty(getAttrScrolling())) ret.append(" scrolling=\"").append(getAttrScrolling()).append("\"");
    if(UtilString.isNotEmpty(getAttrNoresize())) ret.append(" noresize=\"").append(getAttrNoresize()).append("\"");
    if(UtilString.isNotEmpty(getAttrWidth())) ret.append(" width=\"").append(getAttrWidth()).append("\"");
    if(UtilString.isNotEmpty(getAttrMarginheight())) ret.append(" marginheight=\"").append(getAttrMarginheight()).append("\"");
    ret.append("\\>");
    return ret.toString();
  }
  public String toJsp() {
    StringBuffer ret = new StringBuffer("<html:TagIframe");
    if(UtilString.isNotEmpty(getAttrHeight())) ret.append(" attrHeight=").append(getStringCharactere()).append(getAttrHeight()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrMarginwidth())) ret.append(" attrMarginwidth=").append(getStringCharactere()).append(getAttrMarginwidth()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrHspace())) ret.append(" attrHspace=").append(getStringCharactere()).append(getAttrHspace()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrBordercolor())) ret.append(" attrBordercolor=").append(getStringCharactere()).append(getAttrBordercolor()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrVspace())) ret.append(" attrVspace=").append(getStringCharactere()).append(getAttrVspace()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrSrc())) ret.append(" attrSrc=").append(getStringCharactere()).append(getAttrSrc()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrEndtag())) ret.append(" attrEndtag=").append(getStringCharactere()).append(getAttrEndtag()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrFrameborder())) ret.append(" attrFrameborder=").append(getStringCharactere()).append(getAttrFrameborder()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrStringCharactere())) ret.append(" attrStringCharactere=").append(getStringCharactere()).append(getAttrStringCharactere()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrScrolling())) ret.append(" attrScrolling=").append(getStringCharactere()).append(getAttrScrolling()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrNoresize())) ret.append(" attrNoresize=").append(getStringCharactere()).append(getAttrNoresize()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrWidth())) ret.append(" attrWidth=").append(getStringCharactere()).append(getAttrWidth()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrMarginheight())) ret.append(" attrMarginheight=").append(getStringCharactere()).append(getAttrMarginheight()).append(getStringCharactere());
    ret.append("/>\r\n");
    return ret.toString();
  }
}
