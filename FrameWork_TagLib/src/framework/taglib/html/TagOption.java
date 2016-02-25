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
public class TagOption extends BodyTagSupport {

  public static final String CONST_TYPE="OPTION";
  public static final boolean CONST_HAS_ENDTAG=true;

  private String content=null;
  private String stringCharactere=null;
  private String initFromRequestName=null;
  private String attrInitFromRequest;
  private String attrContent;
  private String attrInitFromRequestName;
  private String attrSelected;
  private String attrStringCharactere;
  private String attrValue;

  public TagOption() {
  }

  public int doStartTag() {
    JspWriter out = pageContext.getOut();
    ServletRequest request = pageContext.getRequest();
    HttpSession session = pageContext.getSession();
    try {
      out.print("<OPTION");
      if (UtilString.isNotEmpty(getAttrInitFromRequest()))
        out.print(" INITFROMREQUEST="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrInitFromRequest(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrContent()))
        out.print(" CONTENT="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrContent(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrInitFromRequestName()))
        out.print(" INITFROMREQUESTNAME="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrInitFromRequestName(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getInitFromRequestName()) && UtilString.isNotEmpty(getInitFromRequestName())) {
        String szVal1 = (String)UtilRequest.getAttribute(getInitFromRequestName(), request, session);
        String szVal2 = UtilRequest.replaceParamByRequestValue(getAttrValue(), request, session, "-"+szVal1);
        if (UtilString.isEquals(szVal1, szVal2)) {
          out.print(" SELECTED="+getStringCharactere()+"SELECTED"+getStringCharactere());
        }
      }
      else if (UtilString.isNotEmpty(getAttrSelected()))
        out.print(" SELECTED="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrSelected(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrStringCharactere()))
        out.print(" STRINGCHARACTERE="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrStringCharactere(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrValue()))
        out.print(" VALUE="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrValue(), request, session, "")+getStringCharactere());
      out.print(">");
    } catch(IOException ioe) {
      Trace.ERROR("Error in HeadingTag: ", ioe);
    }
    return EVAL_BODY_INCLUDE;
  }

  public int doEndTag() {
    JspWriter out = pageContext.getOut();
    ServletRequest request = pageContext.getRequest();
    HttpSession session = pageContext.getSession();
    try {
      if (UtilString.isNotEmpty(getContent()))
        out.print(UtilRequest.replaceParamByRequestValue(getContent(), request, session, ""));
      out.print("</OPTION>");
    } catch(IOException ioe) {
      Trace.ERROR("Error in FootingTag: ", ioe);
    }
    return EVAL_PAGE;
  }

  public int doAfterBody() {
    BodyContent bc = getBodyContent();
    ServletRequest request = pageContext.getRequest();
    HttpSession session = pageContext.getSession();
    if (bc != null) {
      try{
        JspWriter out = bc.getEnclosingWriter();
        out.println(bc.getString());
        bc.clearBody();
        return EVAL_BODY_TAG;
      } catch(IOException ioe) {
        Trace.ERROR("Error in BodingTag: ", ioe);
      }
    }
    return SKIP_BODY;
  }

  /**
 * @return  the content
 * @uml.property  name="content"
 */
public String getContent() {return content;}
  /**
 * @param content  the content to set
 * @uml.property  name="content"
 */
public void setContent(String content) {this.content=content;}

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
 * @return  the attrContent
 * @uml.property  name="attrContent"
 */
public String getAttrContent() {
    return attrContent;
  }
  /**
 * @param attrContent  the attrContent to set
 * @uml.property  name="attrContent"
 */
public void setAttrContent(String attrContent) {
    this.attrContent = attrContent;
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
 * @return  the attrSelected
 * @uml.property  name="attrSelected"
 */
public String getAttrSelected() {
    return attrSelected;
  }
  /**
 * @param attrSelected  the attrSelected to set
 * @uml.property  name="attrSelected"
 */
public void setAttrSelected(String attrSelected) {
    this.attrSelected = attrSelected;
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

  public String toHtml() {
    StringBuffer ret = new StringBuffer("<OPTION");
    if(UtilString.isNotEmpty(getAttrInitFromRequest())) ret.append(" initFromRequest=\"").append(getAttrInitFromRequest()).append("\"");
    if(UtilString.isNotEmpty(getAttrContent())) ret.append(" content=\"").append(getAttrContent()).append("\"");
    if(UtilString.isNotEmpty(getAttrInitFromRequestName())) ret.append(" initFromRequestName=\"").append(getAttrInitFromRequestName()).append("\"");
    if(UtilString.isNotEmpty(getAttrSelected())) ret.append(" selected=\"").append(getAttrSelected()).append("\"");
    if(UtilString.isNotEmpty(getAttrStringCharactere())) ret.append(" stringCharactere=\"").append(getAttrStringCharactere()).append("\"");
    if(UtilString.isNotEmpty(getAttrValue())) ret.append(" value=\"").append(getAttrValue()).append("\"");
    ret.append(">");
    if (getContent()!=null) ret.append(getContent());
    ret.append("</OPTION>");
    return ret.toString();
  }
  public String toJsp() {
    StringBuffer ret = new StringBuffer("<html:TagOption");
    if(UtilString.isNotEmpty(getAttrInitFromRequest())) ret.append(" attrInitFromRequest=").append(getStringCharactere()).append(getAttrInitFromRequest()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrContent())) ret.append(" attrContent=").append(getStringCharactere()).append(getAttrContent()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrInitFromRequestName())) ret.append(" attrInitFromRequestName=").append(getStringCharactere()).append(getAttrInitFromRequestName()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrSelected())) ret.append(" attrSelected=").append(getStringCharactere()).append(getAttrSelected()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrStringCharactere())) ret.append(" attrStringCharactere=").append(getStringCharactere()).append(getAttrStringCharactere()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrValue())) ret.append(" attrValue=").append(getStringCharactere()).append(getAttrValue()).append(getStringCharactere());
    ret.append(">\r\n");
    if (getContent()!=null) ret.append(getContent());
    ret.append("</html:TagOption>\r\n");
    return ret.toString();
  }
}
