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
public class TagDiv extends BodyTagSupport {

  public static final String CONST_TYPE="DIV";
  public static final boolean CONST_HAS_ENDTAG=true;

  private String content=null;
  private String stringCharactere=null;
  private String attrContent;
  private String attrName;
  private String attrStringCharactere;
  private String attrType;
  private String attrStyle;
  private String attrId;
  private String attrBgcolor;
  private String attrClass;

  public TagDiv() {
  }

  public int doStartTag() {
    JspWriter out = pageContext.getOut();
    ServletRequest request = pageContext.getRequest();
    HttpSession session = pageContext.getSession();
    try {
      out.print("<DIV");
      if (UtilString.isNotEmpty(getAttrContent()))
        out.print(" CONTENT="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrContent(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrName()))
        out.print(" NAME="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrName(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrStringCharactere()))
        out.print(" STRINGCHARACTERE="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrStringCharactere(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrType()))
        out.print(" TYPE="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrType(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrStyle()))
        out.print(" STYLE="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrStyle(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrId()))
        out.print(" ID="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrId(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrBgcolor()))
        out.print(" BGCOLOR="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrBgcolor(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrClass()))
        out.print(" CLASS="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrClass(), request, session, "")+getStringCharactere());
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
      out.print("</DIV>");
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
 * @return  the attrBgcolor
 * @uml.property  name="attrBgcolor"
 */
public String getAttrBgcolor() {
    return attrBgcolor;
  }
  /**
 * @param attrBgcolor  the attrBgcolor to set
 * @uml.property  name="attrBgcolor"
 */
public void setAttrBgcolor(String attrBgcolor) {
    this.attrBgcolor = attrBgcolor;
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

  public String toHtml() {
    StringBuffer ret = new StringBuffer("<DIV");
    if(UtilString.isNotEmpty(getAttrContent())) ret.append(" content=\"").append(getAttrContent()).append("\"");
    if(UtilString.isNotEmpty(getAttrName())) ret.append(" name=\"").append(getAttrName()).append("\"");
    if(UtilString.isNotEmpty(getAttrStringCharactere())) ret.append(" stringCharactere=\"").append(getAttrStringCharactere()).append("\"");
    if(UtilString.isNotEmpty(getAttrType())) ret.append(" type=\"").append(getAttrType()).append("\"");
    if(UtilString.isNotEmpty(getAttrStyle())) ret.append(" style=\"").append(getAttrStyle()).append("\"");
    if(UtilString.isNotEmpty(getAttrId())) ret.append(" id=\"").append(getAttrId()).append("\"");
    if(UtilString.isNotEmpty(getAttrBgcolor())) ret.append(" bgcolor=\"").append(getAttrBgcolor()).append("\"");
    if(UtilString.isNotEmpty(getAttrClass())) ret.append(" class=\"").append(getAttrClass()).append("\"");
    ret.append(">");
    if (getContent()!=null) ret.append(getContent());
    ret.append("</DIV>");
    return ret.toString();
  }
  public String toJsp() {
    StringBuffer ret = new StringBuffer("<html:TagDiv");
    if(UtilString.isNotEmpty(getAttrContent())) ret.append(" attrContent=").append(getStringCharactere()).append(getAttrContent()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrName())) ret.append(" attrName=").append(getStringCharactere()).append(getAttrName()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrStringCharactere())) ret.append(" attrStringCharactere=").append(getStringCharactere()).append(getAttrStringCharactere()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrType())) ret.append(" attrType=").append(getStringCharactere()).append(getAttrType()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrStyle())) ret.append(" attrStyle=").append(getStringCharactere()).append(getAttrStyle()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrId())) ret.append(" attrId=").append(getStringCharactere()).append(getAttrId()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrBgcolor())) ret.append(" attrBgcolor=").append(getStringCharactere()).append(getAttrBgcolor()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrClass())) ret.append(" attrClass=").append(getStringCharactere()).append(getAttrClass()).append(getStringCharactere());
    ret.append(">\r\n");
    if (getContent()!=null) ret.append(getContent());
    ret.append("</html:TagDiv>\r\n");
    return ret.toString();
  }
}
