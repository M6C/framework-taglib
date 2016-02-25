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
public class TagInput extends TagSupport {

  public static final String CONST_TYPE="INPUT";
  public static final boolean CONST_HAS_ENDTAG=false;

  private String stringCharactere=null;
  private String initFromRequestName=null;
  private String attrInitFromRequest;
  private String attrSrc;
  private String attrReadonly;
  private String attrStringCharactere;
  private String attrMaxlength;
  private String attrSize;
  private String attrClass;
  private String attrOnfocus;
  private String attrOnclick;
  private String attrId;
  private String attrWidth;
  private String attrValue;
  private String attrHeight;
  private String attrStyle;
  private String attrBorder;
  private String attrName;
  private String attrChecked;
  private String attrInitFromRequestName;
  private String attrType;

  public TagInput() {
  }

  public int doStartTag() {
    JspWriter out = pageContext.getOut();
    ServletRequest request = pageContext.getRequest();
    HttpSession session = pageContext.getSession();
    try {
      out.print("<INPUT");
      if (UtilString.isNotEmpty(getAttrInitFromRequest()))
        out.print(" INITFROMREQUEST="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrInitFromRequest(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrSrc()))
        out.print(" SRC="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrSrc(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrReadonly()))
        out.print(" READONLY="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrReadonly(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrStringCharactere()))
        out.print(" STRINGCHARACTERE="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrStringCharactere(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrMaxlength()))
        out.print(" MAXLENGTH="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrMaxlength(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrSize()))
        out.print(" SIZE="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrSize(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrClass()))
        out.print(" CLASS="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrClass(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrOnfocus()))
        out.print(" ONFOCUS="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrOnfocus(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrOnclick()))
        out.print(" ONCLICK="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrOnclick(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrId()))
        out.print(" ID="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrId(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrWidth()))
        out.print(" WIDTH="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrWidth(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getInitFromRequestName()) && (UtilString.isEqualsIgnoreCase(getAttrType(), "TEXT") || UtilString.isEmpty(getAttrType()))) {
        String szVal = (String)UtilRequest.getAttribute(getInitFromRequestName(), request, session);
        if (UtilString.isNotEmpty(szVal)) {
          out.print(" VALUE="+getStringCharactere()+szVal+getStringCharactere());
        }
      }
      else if (UtilString.isNotEmpty(getAttrValue()))
        out.print(" VALUE="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrValue(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrHeight()))
        out.print(" HEIGHT="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrHeight(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrStyle()))
        out.print(" STYLE="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrStyle(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrBorder()))
        out.print(" BORDER="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrBorder(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrName()))
        out.print(" NAME="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrName(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getInitFromRequestName()) && UtilString.isNotEmpty(getInitFromRequestName()) && UtilString.isEqualsIgnoreCase(getAttrType(), "CHECKBOX")) {
        String szVal = (String)UtilRequest.getAttribute(getInitFromRequestName(), request, session);
        if (UtilString.isNotEmpty(szVal)) {
          out.print(" CHECKED="+getStringCharactere()+szVal+getStringCharactere());
        }
      }
      else if (UtilString.isNotEmpty(getAttrChecked()))
        out.print(" CHECKED="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrChecked(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrInitFromRequestName()))
        out.print(" INITFROMREQUESTNAME="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrInitFromRequestName(), request, session, "")+getStringCharactere());
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
 * @return  the initFromRequestName
 * @uml.property  name="initFromRequestName"
 */
public String getInitFromRequestName() {return initFromRequestName;}
  /**
 * @param initFromRequestName  the initFromRequestName to set
 * @uml.property  name="initFromRequestName"
 */
public void setInitFromRequestName(String initFromRequestName) {this.initFromRequestName=initFromRequestName;}

  /**
 * @return  the attrInitFromRequest
 * @uml.property  name="attrInitFromRequest"
 */
public String getAttrInitFromRequest() {
    return attrInitFromRequest;
  }
  /**
 * @param attrInitFromRequest  the attrInitFromRequest to set
 * @uml.property  name="attrInitFromRequest"
 */
public void setAttrInitFromRequest(String attrInitFromRequest) {
    this.attrInitFromRequest = attrInitFromRequest;
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
 * @return  the attrReadonly
 * @uml.property  name="attrReadonly"
 */
public String getAttrReadonly() {
    return attrReadonly;
  }
  /**
 * @param attrReadonly  the attrReadonly to set
 * @uml.property  name="attrReadonly"
 */
public void setAttrReadonly(String attrReadonly) {
    this.attrReadonly = attrReadonly;
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
 * @return  the attrMaxlength
 * @uml.property  name="attrMaxlength"
 */
public String getAttrMaxlength() {
    return attrMaxlength;
  }
  /**
 * @param attrMaxlength  the attrMaxlength to set
 * @uml.property  name="attrMaxlength"
 */
public void setAttrMaxlength(String attrMaxlength) {
    this.attrMaxlength = attrMaxlength;
  }
  /**
 * @return  the attrSize
 * @uml.property  name="attrSize"
 */
public String getAttrSize() {
    return attrSize;
  }
  /**
 * @param attrSize  the attrSize to set
 * @uml.property  name="attrSize"
 */
public void setAttrSize(String attrSize) {
    this.attrSize = attrSize;
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
 * @return  the attrOnfocus
 * @uml.property  name="attrOnfocus"
 */
public String getAttrOnfocus() {
    return attrOnfocus;
  }
  /**
 * @param attrOnfocus  the attrOnfocus to set
 * @uml.property  name="attrOnfocus"
 */
public void setAttrOnfocus(String attrOnfocus) {
    this.attrOnfocus = attrOnfocus;
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
 * @return  the attrValue
 * @uml.property  name="attrValue"
 */
public String getAttrValue() {
    return attrValue;
  }
  /**
 * @param attrValue  the attrValue to set
 * @uml.property  name="attrValue"
 */
public void setAttrValue(String attrValue) {
    this.attrValue = attrValue;
  }
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
 * @return  the attrChecked
 * @uml.property  name="attrChecked"
 */
public String getAttrChecked() {
    return attrChecked;
  }
  /**
 * @param attrChecked  the attrChecked to set
 * @uml.property  name="attrChecked"
 */
public void setAttrChecked(String attrChecked) {
    this.attrChecked = attrChecked;
  }
  /**
 * @return  the attrInitFromRequestName
 * @uml.property  name="attrInitFromRequestName"
 */
public String getAttrInitFromRequestName() {
    return attrInitFromRequestName;
  }
  /**
 * @param attrInitFromRequestName  the attrInitFromRequestName to set
 * @uml.property  name="attrInitFromRequestName"
 */
public void setAttrInitFromRequestName(String attrInitFromRequestName) {
    this.attrInitFromRequestName = attrInitFromRequestName;
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
    StringBuffer ret = new StringBuffer("<INPUT");
    if(UtilString.isNotEmpty(getAttrInitFromRequest())) ret.append(" initFromRequest=\"").append(getAttrInitFromRequest()).append("\"");
    if(UtilString.isNotEmpty(getAttrSrc())) ret.append(" src=\"").append(getAttrSrc()).append("\"");
    if(UtilString.isNotEmpty(getAttrReadonly())) ret.append(" readonly=\"").append(getAttrReadonly()).append("\"");
    if(UtilString.isNotEmpty(getAttrStringCharactere())) ret.append(" stringCharactere=\"").append(getAttrStringCharactere()).append("\"");
    if(UtilString.isNotEmpty(getAttrMaxlength())) ret.append(" maxlength=\"").append(getAttrMaxlength()).append("\"");
    if(UtilString.isNotEmpty(getAttrSize())) ret.append(" size=\"").append(getAttrSize()).append("\"");
    if(UtilString.isNotEmpty(getAttrClass())) ret.append(" class=\"").append(getAttrClass()).append("\"");
    if(UtilString.isNotEmpty(getAttrOnfocus())) ret.append(" onfocus=\"").append(getAttrOnfocus()).append("\"");
    if(UtilString.isNotEmpty(getAttrOnclick())) ret.append(" onclick=\"").append(getAttrOnclick()).append("\"");
    if(UtilString.isNotEmpty(getAttrId())) ret.append(" id=\"").append(getAttrId()).append("\"");
    if(UtilString.isNotEmpty(getAttrWidth())) ret.append(" width=\"").append(getAttrWidth()).append("\"");
    if(UtilString.isNotEmpty(getAttrValue())) ret.append(" value=\"").append(getAttrValue()).append("\"");
    if(UtilString.isNotEmpty(getAttrHeight())) ret.append(" height=\"").append(getAttrHeight()).append("\"");
    if(UtilString.isNotEmpty(getAttrStyle())) ret.append(" style=\"").append(getAttrStyle()).append("\"");
    if(UtilString.isNotEmpty(getAttrBorder())) ret.append(" border=\"").append(getAttrBorder()).append("\"");
    if(UtilString.isNotEmpty(getAttrName())) ret.append(" name=\"").append(getAttrName()).append("\"");
    if(UtilString.isNotEmpty(getAttrChecked())) ret.append(" checked=\"").append(getAttrChecked()).append("\"");
    if(UtilString.isNotEmpty(getAttrInitFromRequestName())) ret.append(" initFromRequestName=\"").append(getAttrInitFromRequestName()).append("\"");
    if(UtilString.isNotEmpty(getAttrType())) ret.append(" type=\"").append(getAttrType()).append("\"");
    ret.append("\\>");
    return ret.toString();
  }
  public String toJsp() {
    StringBuffer ret = new StringBuffer("<html:TagInput");
    if(UtilString.isNotEmpty(getAttrInitFromRequest())) ret.append(" attrInitFromRequest=").append(getStringCharactere()).append(getAttrInitFromRequest()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrSrc())) ret.append(" attrSrc=").append(getStringCharactere()).append(getAttrSrc()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrReadonly())) ret.append(" attrReadonly=").append(getStringCharactere()).append(getAttrReadonly()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrStringCharactere())) ret.append(" attrStringCharactere=").append(getStringCharactere()).append(getAttrStringCharactere()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrMaxlength())) ret.append(" attrMaxlength=").append(getStringCharactere()).append(getAttrMaxlength()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrSize())) ret.append(" attrSize=").append(getStringCharactere()).append(getAttrSize()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrClass())) ret.append(" attrClass=").append(getStringCharactere()).append(getAttrClass()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrOnfocus())) ret.append(" attrOnfocus=").append(getStringCharactere()).append(getAttrOnfocus()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrOnclick())) ret.append(" attrOnclick=").append(getStringCharactere()).append(getAttrOnclick()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrId())) ret.append(" attrId=").append(getStringCharactere()).append(getAttrId()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrWidth())) ret.append(" attrWidth=").append(getStringCharactere()).append(getAttrWidth()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrValue())) ret.append(" attrValue=").append(getStringCharactere()).append(getAttrValue()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrHeight())) ret.append(" attrHeight=").append(getStringCharactere()).append(getAttrHeight()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrStyle())) ret.append(" attrStyle=").append(getStringCharactere()).append(getAttrStyle()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrBorder())) ret.append(" attrBorder=").append(getStringCharactere()).append(getAttrBorder()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrName())) ret.append(" attrName=").append(getStringCharactere()).append(getAttrName()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrChecked())) ret.append(" attrChecked=").append(getStringCharactere()).append(getAttrChecked()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrInitFromRequestName())) ret.append(" attrInitFromRequestName=").append(getStringCharactere()).append(getAttrInitFromRequestName()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrType())) ret.append(" attrType=").append(getStringCharactere()).append(getAttrType()).append(getStringCharactere());
    ret.append("/>\r\n");
    return ret.toString();
  }
}
