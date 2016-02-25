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
public class TagLabel extends TagSupport {

  public static final String CONST_TYPE="LABEL";
  public static final boolean CONST_HAS_ENDTAG=false;

  private String stringCharactere=null;
  private String attrFor;
  private String attrStringCharactere;
  private String attrEndtag;

  public TagLabel() {
  }

  public int doStartTag() {
    JspWriter out = pageContext.getOut();
    ServletRequest request = pageContext.getRequest();
    HttpSession session = pageContext.getSession();
    try {
      out.print("<LABEL");
      if (UtilString.isNotEmpty(getAttrFor()))
        out.print(" FOR="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrFor(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrStringCharactere()))
        out.print(" STRINGCHARACTERE="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrStringCharactere(), request, session, "")+getStringCharactere());
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
 * @return  the attrFor
 * @uml.property  name="attrFor"
 */
public String getAttrFor() {
    return attrFor;
  }
  /**
 * @param attrFor  the attrFor to set
 * @uml.property  name="attrFor"
 */
public void setAttrFor(String attrFor) {
    this.attrFor = attrFor;
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
    StringBuffer ret = new StringBuffer("<LABEL");
    if(UtilString.isNotEmpty(getAttrFor())) ret.append(" for=\"").append(getAttrFor()).append("\"");
    if(UtilString.isNotEmpty(getAttrStringCharactere())) ret.append(" stringCharactere=\"").append(getAttrStringCharactere()).append("\"");
    if(UtilString.isNotEmpty(getAttrEndtag())) ret.append(" endtag=\"").append(getAttrEndtag()).append("\"");
    ret.append("\\>");
    return ret.toString();
  }
  public String toJsp() {
    StringBuffer ret = new StringBuffer("<html:TagLabel");
    if(UtilString.isNotEmpty(getAttrFor())) ret.append(" attrFor=").append(getStringCharactere()).append(getAttrFor()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrStringCharactere())) ret.append(" attrStringCharactere=").append(getStringCharactere()).append(getAttrStringCharactere()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrEndtag())) ret.append(" attrEndtag=").append(getStringCharactere()).append(getAttrEndtag()).append(getStringCharactere());
    ret.append("/>\r\n");
    return ret.toString();
  }
}
