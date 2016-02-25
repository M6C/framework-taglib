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
public class TagLink extends TagSupport {

  public static final String CONST_TYPE="LINK";
  public static final boolean CONST_HAS_ENDTAG=false;

  private String stringCharactere=null;
  private String attrHref;
  private String attrRel;
  private String attrStringCharactere;
  private String attrType;

  public TagLink() {
  }

  public int doStartTag() {
    JspWriter out = pageContext.getOut();
    ServletRequest request = pageContext.getRequest();
    HttpSession session = pageContext.getSession();
    try {
      out.print("<LINK");
      if (UtilString.isNotEmpty(getAttrHref()))
        out.print(" HREF="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrHref(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrRel()))
        out.print(" REL="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrRel(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrStringCharactere()))
        out.print(" STRINGCHARACTERE="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrStringCharactere(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrType()))
        out.print(" TYPE="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrType(), request, session, "")+getStringCharactere());
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
 * @return  the attrRel
 * @uml.property  name="attrRel"
 */
public String getAttrRel() {
    return attrRel;
  }
  /**
 * @param attrRel  the attrRel to set
 * @uml.property  name="attrRel"
 */
public void setAttrRel(String attrRel) {
    this.attrRel = attrRel;
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
 * @return  the attrType
 * @uml.property  name="attrType"
 */
public String getAttrType() {
    return attrType;
  }
  /**
 * @param attrType  the attrType to set
 * @uml.property  name="attrType"
 */
public void setAttrType(String attrType) {
    this.attrType = attrType;
  }

  public String toHtml() {
    StringBuffer ret = new StringBuffer("<LINK");
    if(UtilString.isNotEmpty(getAttrHref())) ret.append(" href=\"").append(getAttrHref()).append("\"");
    if(UtilString.isNotEmpty(getAttrRel())) ret.append(" rel=\"").append(getAttrRel()).append("\"");
    if(UtilString.isNotEmpty(getAttrStringCharactere())) ret.append(" stringCharactere=\"").append(getAttrStringCharactere()).append("\"");
    if(UtilString.isNotEmpty(getAttrType())) ret.append(" type=\"").append(getAttrType()).append("\"");
    ret.append("\\>");
    return ret.toString();
  }
  public String toJsp() {
    StringBuffer ret = new StringBuffer("<html:TagLink");
    if(UtilString.isNotEmpty(getAttrHref())) ret.append(" attrHref=").append(getStringCharactere()).append(getAttrHref()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrRel())) ret.append(" attrRel=").append(getStringCharactere()).append(getAttrRel()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrStringCharactere())) ret.append(" attrStringCharactere=").append(getStringCharactere()).append(getAttrStringCharactere()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrType())) ret.append(" attrType=").append(getStringCharactere()).append(getAttrType()).append(getStringCharactere());
    ret.append("/>\r\n");
    return ret.toString();
  }
}
