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
public class TagBase extends TagSupport {

  public static final String CONST_TYPE="BASE";
  public static final boolean CONST_HAS_ENDTAG=false;

  private String stringCharactere=null;
  private String attrHref;
  private String attrStringCharactere;

  public TagBase() {
  }

  public int doStartTag() {
    JspWriter out = pageContext.getOut();
    ServletRequest request = pageContext.getRequest();
    HttpSession session = pageContext.getSession();
    try {
      out.print("<BASE");
      if (UtilString.isNotEmpty(getAttrHref()))
        out.print(" HREF="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrHref(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrStringCharactere()))
        out.print(" STRINGCHARACTERE="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrStringCharactere(), request, session, "")+getStringCharactere());
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

  public String toHtml() {
    StringBuffer ret = new StringBuffer("<BASE");
    if(UtilString.isNotEmpty(getAttrHref())) ret.append(" href=\"").append(getAttrHref()).append("\"");
    if(UtilString.isNotEmpty(getAttrStringCharactere())) ret.append(" stringCharactere=\"").append(getAttrStringCharactere()).append("\"");
    ret.append("\\>");
    return ret.toString();
  }
  public String toJsp() {
    StringBuffer ret = new StringBuffer("<html:TagBase");
    if(UtilString.isNotEmpty(getAttrHref())) ret.append(" attrHref=").append(getStringCharactere()).append(getAttrHref()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrStringCharactere())) ret.append(" attrStringCharactere=").append(getStringCharactere()).append(getAttrStringCharactere()).append(getStringCharactere());
    ret.append("/>\r\n");
    return ret.toString();
  }
}
