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
public class TagScr extends TagSupport {

  public static final String CONST_TYPE="SCR";
  public static final boolean CONST_HAS_ENDTAG=false;

  private String stringCharactere=null;
  private String attrStringCharactere;
  private String attrEndtag;
  private String attrLanguage;
  private String attrIpt;

  public TagScr() {
  }

  public int doStartTag() {
    JspWriter out = pageContext.getOut();
    ServletRequest request = pageContext.getRequest();
    HttpSession session = pageContext.getSession();
    try {
      out.print("<SCR");
      if (UtilString.isNotEmpty(getAttrStringCharactere()))
        out.print(" STRINGCHARACTERE="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrStringCharactere(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrEndtag()))
        out.print(" ENDTAG="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrEndtag(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrLanguage()))
        out.print(" LANGUAGE="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrLanguage(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrIpt()))
        out.print(" IPT="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrIpt(), request, session, "")+getStringCharactere());
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

  public String toHtml() {
    StringBuffer ret = new StringBuffer("<SCR");
    if(UtilString.isNotEmpty(getAttrStringCharactere())) ret.append(" stringCharactere=\"").append(getAttrStringCharactere()).append("\"");
    if(UtilString.isNotEmpty(getAttrEndtag())) ret.append(" endtag=\"").append(getAttrEndtag()).append("\"");
    if(UtilString.isNotEmpty(getAttrLanguage())) ret.append(" language=\"").append(getAttrLanguage()).append("\"");
    if(UtilString.isNotEmpty(getAttrIpt())) ret.append(" ipt=\"").append(getAttrIpt()).append("\"");
    ret.append("\\>");
    return ret.toString();
  }
  public String toJsp() {
    StringBuffer ret = new StringBuffer("<html:TagScr");
    if(UtilString.isNotEmpty(getAttrStringCharactere())) ret.append(" attrStringCharactere=").append(getStringCharactere()).append(getAttrStringCharactere()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrEndtag())) ret.append(" attrEndtag=").append(getStringCharactere()).append(getAttrEndtag()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrLanguage())) ret.append(" attrLanguage=").append(getStringCharactere()).append(getAttrLanguage()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrIpt())) ret.append(" attrIpt=").append(getStringCharactere()).append(getAttrIpt()).append(getStringCharactere());
    ret.append("/>\r\n");
    return ret.toString();
  }
}
