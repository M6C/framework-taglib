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
public class TagForm extends BodyTagSupport {

  public static final String CONST_TYPE="FORM";
  public static final boolean CONST_HAS_ENDTAG=true;

  private String content=null;
  private String stringCharactere=null;
  private String attrMethod;
  private String attrContent;
  private String attrName;
  private String attrStringCharactere;
  private String attrAction;
  private String attrClass;
  private String attrTarget;

  public TagForm() {
  }

  public int doStartTag() {
    JspWriter out = pageContext.getOut();
    ServletRequest request = pageContext.getRequest();
    HttpSession session = pageContext.getSession();
    try {
      out.print("<FORM");
      if (UtilString.isNotEmpty(getAttrMethod()))
        out.print(" METHOD="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrMethod(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrContent()))
        out.print(" CONTENT="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrContent(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrName()))
        out.print(" NAME="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrName(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrStringCharactere()))
        out.print(" STRINGCHARACTERE="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrStringCharactere(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrAction()))
        out.print(" ACTION="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrAction(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrClass()))
        out.print(" CLASS="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrClass(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrTarget()))
        out.print(" TARGET="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrTarget(), request, session, "")+getStringCharactere());
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
      out.print("</FORM>");
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
 * @return  the attrMethod
 * @uml.property  name="attrMethod"
 */
public String getAttrMethod() {
    return attrMethod;
  }
  /**
 * @param attrMethod  the attrMethod to set
 * @uml.property  name="attrMethod"
 */
public void setAttrMethod(String attrMethod) {
    this.attrMethod = attrMethod;
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
 * @return  the attrAction
 * @uml.property  name="attrAction"
 */
public String getAttrAction() {
    return attrAction;
  }
  /**
 * @param attrAction  the attrAction to set
 * @uml.property  name="attrAction"
 */
public void setAttrAction(String attrAction) {
    this.attrAction = attrAction;
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
    StringBuffer ret = new StringBuffer("<FORM");
    if(UtilString.isNotEmpty(getAttrMethod())) ret.append(" method=\"").append(getAttrMethod()).append("\"");
    if(UtilString.isNotEmpty(getAttrContent())) ret.append(" content=\"").append(getAttrContent()).append("\"");
    if(UtilString.isNotEmpty(getAttrName())) ret.append(" name=\"").append(getAttrName()).append("\"");
    if(UtilString.isNotEmpty(getAttrStringCharactere())) ret.append(" stringCharactere=\"").append(getAttrStringCharactere()).append("\"");
    if(UtilString.isNotEmpty(getAttrAction())) ret.append(" action=\"").append(getAttrAction()).append("\"");
    if(UtilString.isNotEmpty(getAttrClass())) ret.append(" class=\"").append(getAttrClass()).append("\"");
    if(UtilString.isNotEmpty(getAttrTarget())) ret.append(" target=\"").append(getAttrTarget()).append("\"");
    ret.append(">");
    if (getContent()!=null) ret.append(getContent());
    ret.append("</FORM>");
    return ret.toString();
  }
  public String toJsp() {
    StringBuffer ret = new StringBuffer("<html:TagForm");
    if(UtilString.isNotEmpty(getAttrMethod())) ret.append(" attrMethod=").append(getStringCharactere()).append(getAttrMethod()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrContent())) ret.append(" attrContent=").append(getStringCharactere()).append(getAttrContent()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrName())) ret.append(" attrName=").append(getStringCharactere()).append(getAttrName()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrStringCharactere())) ret.append(" attrStringCharactere=").append(getStringCharactere()).append(getAttrStringCharactere()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrAction())) ret.append(" attrAction=").append(getStringCharactere()).append(getAttrAction()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrClass())) ret.append(" attrClass=").append(getStringCharactere()).append(getAttrClass()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrTarget())) ret.append(" attrTarget=").append(getStringCharactere()).append(getAttrTarget()).append(getStringCharactere());
    ret.append(">\r\n");
    if (getContent()!=null) ret.append(getContent());
    ret.append("</html:TagForm>\r\n");
    return ret.toString();
  }
}
