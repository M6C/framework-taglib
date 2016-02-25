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
public class TagArea extends TagSupport {

  public static final String CONST_TYPE="AREA";
  public static final boolean CONST_HAS_ENDTAG=false;

  private String stringCharactere=null;
  private String attrShape;
  private String attrHref;
  private String attrStringCharactere;
  private String attrCoords;
  private String attrTarget;

  public TagArea() {
  }

  public int doStartTag() {
    JspWriter out = pageContext.getOut();
    ServletRequest request = pageContext.getRequest();
    HttpSession session = pageContext.getSession();
    try {
      out.print("<AREA");
      if (UtilString.isNotEmpty(getAttrShape()))
        out.print(" SHAPE="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrShape(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrHref()))
        out.print(" HREF="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrHref(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrStringCharactere()))
        out.print(" STRINGCHARACTERE="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrStringCharactere(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrCoords()))
        out.print(" COORDS="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrCoords(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrTarget()))
        out.print(" TARGET="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrTarget(), request, session, "")+getStringCharactere());
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
 * @return  the attrShape
 * @uml.property  name="attrShape"
 */
public String getAttrShape() {
    return attrShape;
  }
  /**
 * @param attrShape  the attrShape to set
 * @uml.property  name="attrShape"
 */
public void setAttrShape(String attrShape) {
    this.attrShape = attrShape;
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
 * @return  the attrCoords
 * @uml.property  name="attrCoords"
 */
public String getAttrCoords() {
    return attrCoords;
  }
  /**
 * @param attrCoords  the attrCoords to set
 * @uml.property  name="attrCoords"
 */
public void setAttrCoords(String attrCoords) {
    this.attrCoords = attrCoords;
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

  public String toHtml() {
    StringBuffer ret = new StringBuffer("<AREA");
    if(UtilString.isNotEmpty(getAttrShape())) ret.append(" shape=\"").append(getAttrShape()).append("\"");
    if(UtilString.isNotEmpty(getAttrHref())) ret.append(" href=\"").append(getAttrHref()).append("\"");
    if(UtilString.isNotEmpty(getAttrStringCharactere())) ret.append(" stringCharactere=\"").append(getAttrStringCharactere()).append("\"");
    if(UtilString.isNotEmpty(getAttrCoords())) ret.append(" coords=\"").append(getAttrCoords()).append("\"");
    if(UtilString.isNotEmpty(getAttrTarget())) ret.append(" target=\"").append(getAttrTarget()).append("\"");
    ret.append("\\>");
    return ret.toString();
  }
  public String toJsp() {
    StringBuffer ret = new StringBuffer("<html:TagArea");
    if(UtilString.isNotEmpty(getAttrShape())) ret.append(" attrShape=").append(getStringCharactere()).append(getAttrShape()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrHref())) ret.append(" attrHref=").append(getStringCharactere()).append(getAttrHref()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrStringCharactere())) ret.append(" attrStringCharactere=").append(getStringCharactere()).append(getAttrStringCharactere()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrCoords())) ret.append(" attrCoords=").append(getStringCharactere()).append(getAttrCoords()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrTarget())) ret.append(" attrTarget=").append(getStringCharactere()).append(getAttrTarget()).append(getStringCharactere());
    ret.append("/>\r\n");
    return ret.toString();
  }
}
