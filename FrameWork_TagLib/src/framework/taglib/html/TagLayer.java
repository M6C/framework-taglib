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
public class TagLayer extends TagSupport {

  public static final String CONST_TYPE="LAYER";
  public static final boolean CONST_HAS_ENDTAG=false;

  private String stringCharactere=null;
  private String attrSrc;
  private String attrVisibility;
  private String attrStringCharactere;
  private String attrOnload;
  private String attrEndtag;

  public TagLayer() {
  }

  public int doStartTag() {
    JspWriter out = pageContext.getOut();
    ServletRequest request = pageContext.getRequest();
    HttpSession session = pageContext.getSession();
    try {
      out.print("<LAYER");
      if (UtilString.isNotEmpty(getAttrSrc()))
        out.print(" SRC="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrSrc(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrVisibility()))
        out.print(" VISIBILITY="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrVisibility(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrStringCharactere()))
        out.print(" STRINGCHARACTERE="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrStringCharactere(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrOnload()))
        out.print(" ONLOAD="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrOnload(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrEndtag()))
        out.print(" ENDTAG="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrEndtag(), request, session, "")+getStringCharactere());
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
 * @return  the attrVisibility
 * @uml.property  name="attrVisibility"
 */
public String getAttrVisibility() {
    return attrVisibility;
  }
  /**
 * @param attrVisibility  the attrVisibility to set
 * @uml.property  name="attrVisibility"
 */
public void setAttrVisibility(String attrVisibility) {
    this.attrVisibility = attrVisibility;
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
 * @return  the attrOnload
 * @uml.property  name="attrOnload"
 */
public String getAttrOnload() {
    return attrOnload;
  }
  /**
 * @param attrOnload  the attrOnload to set
 * @uml.property  name="attrOnload"
 */
public void setAttrOnload(String attrOnload) {
    this.attrOnload = attrOnload;
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

  public String toHtml() {
    StringBuffer ret = new StringBuffer("<LAYER");
    if(UtilString.isNotEmpty(getAttrSrc())) ret.append(" src=\"").append(getAttrSrc()).append("\"");
    if(UtilString.isNotEmpty(getAttrVisibility())) ret.append(" visibility=\"").append(getAttrVisibility()).append("\"");
    if(UtilString.isNotEmpty(getAttrStringCharactere())) ret.append(" stringCharactere=\"").append(getAttrStringCharactere()).append("\"");
    if(UtilString.isNotEmpty(getAttrOnload())) ret.append(" onload=\"").append(getAttrOnload()).append("\"");
    if(UtilString.isNotEmpty(getAttrEndtag())) ret.append(" endtag=\"").append(getAttrEndtag()).append("\"");
    ret.append("\\>");
    return ret.toString();
  }
  public String toJsp() {
    StringBuffer ret = new StringBuffer("<html:TagLayer");
    if(UtilString.isNotEmpty(getAttrSrc())) ret.append(" attrSrc=").append(getStringCharactere()).append(getAttrSrc()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrVisibility())) ret.append(" attrVisibility=").append(getStringCharactere()).append(getAttrVisibility()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrStringCharactere())) ret.append(" attrStringCharactere=").append(getStringCharactere()).append(getAttrStringCharactere()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrOnload())) ret.append(" attrOnload=").append(getStringCharactere()).append(getAttrOnload()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrEndtag())) ret.append(" attrEndtag=").append(getStringCharactere()).append(getAttrEndtag()).append(getStringCharactere());
    ret.append("/>\r\n");
    return ret.toString();
  }
}
