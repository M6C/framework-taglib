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
public class TagTable extends BodyTagSupport {

  public static final String CONST_TYPE="TABLE";
  public static final boolean CONST_HAS_ENDTAG=true;

  private String content=null;
  private String stringCharactere=null;
  private String attrDocumentcookiesubstring;
  private String attrHeight;
  private String attrBackground;
  private String attrContent;
  private String attrAlign;
  private String attrVar;
  private String attrCellspacing;
  private String attrId;
  private String attrBorder;
  private String attrCellpadding;
  private String attrJ;
  private String attrBgcolor;
  private String attrI;
  private String attrStringCharactere;
  private String attrWidth;
  private String attrClass;

  public TagTable() {
  }

  public int doStartTag() {
    JspWriter out = pageContext.getOut();
    ServletRequest request = pageContext.getRequest();
    HttpSession session = pageContext.getSession();
    try {
      out.print("<TABLE");
      if (UtilString.isNotEmpty(getAttrDocumentcookiesubstring()))
        out.print(" DOCUMENTCOOKIESUBSTRING="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrDocumentcookiesubstring(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrHeight()))
        out.print(" HEIGHT="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrHeight(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrBackground()))
        out.print(" BACKGROUND="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrBackground(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrContent()))
        out.print(" CONTENT="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrContent(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrAlign()))
        out.print(" ALIGN="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrAlign(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrVar()))
        out.print(" VAR="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrVar(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrCellspacing()))
        out.print(" CELLSPACING="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrCellspacing(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrId()))
        out.print(" ID="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrId(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrBorder()))
        out.print(" BORDER="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrBorder(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrCellpadding()))
        out.print(" CELLPADDING="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrCellpadding(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrJ()))
        out.print(" J="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrJ(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrBgcolor()))
        out.print(" BGCOLOR="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrBgcolor(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrI()))
        out.print(" I="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrI(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrStringCharactere()))
        out.print(" STRINGCHARACTERE="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrStringCharactere(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrWidth()))
        out.print(" WIDTH="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrWidth(), request, session, "")+getStringCharactere());
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
      out.print("</TABLE>");
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
 * @return  the attrDocumentcookiesubstring
 * @uml.property  name="attrDocumentcookiesubstring"
 */
public String getAttrDocumentcookiesubstring() {
    return attrDocumentcookiesubstring;
  }
  /**
 * @param attrDocumentcookiesubstring  the attrDocumentcookiesubstring to set
 * @uml.property  name="attrDocumentcookiesubstring"
 */
public void setAttrDocumentcookiesubstring(String attrDocumentcookiesubstring) {
    this.attrDocumentcookiesubstring = attrDocumentcookiesubstring;
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
 * @return  the attrBackground
 * @uml.property  name="attrBackground"
 */
public String getAttrBackground() {
    return attrBackground;
  }
  /**
 * @param attrBackground  the attrBackground to set
 * @uml.property  name="attrBackground"
 */
public void setAttrBackground(String attrBackground) {
    this.attrBackground = attrBackground;
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
 * @return  the attrAlign
 * @uml.property  name="attrAlign"
 */
public String getAttrAlign() {
    return attrAlign;
  }
  /**
 * @param attrAlign  the attrAlign to set
 * @uml.property  name="attrAlign"
 */
public void setAttrAlign(String attrAlign) {
    this.attrAlign = attrAlign;
  }
  /**
 * @return  the attrVar
 * @uml.property  name="attrVar"
 */
public String getAttrVar() {
    return attrVar;
  }
  /**
 * @param attrVar  the attrVar to set
 * @uml.property  name="attrVar"
 */
public void setAttrVar(String attrVar) {
    this.attrVar = attrVar;
  }
  /**
 * @return  the attrCellspacing
 * @uml.property  name="attrCellspacing"
 */
public String getAttrCellspacing() {
    return attrCellspacing;
  }
  /**
 * @param attrCellspacing  the attrCellspacing to set
 * @uml.property  name="attrCellspacing"
 */
public void setAttrCellspacing(String attrCellspacing) {
    this.attrCellspacing = attrCellspacing;
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
 * @return  the attrCellpadding
 * @uml.property  name="attrCellpadding"
 */
public String getAttrCellpadding() {
    return attrCellpadding;
  }
  /**
 * @param attrCellpadding  the attrCellpadding to set
 * @uml.property  name="attrCellpadding"
 */
public void setAttrCellpadding(String attrCellpadding) {
    this.attrCellpadding = attrCellpadding;
  }
  /**
 * @return  the attrJ
 * @uml.property  name="attrJ"
 */
public String getAttrJ() {
    return attrJ;
  }
  /**
 * @param attrJ  the attrJ to set
 * @uml.property  name="attrJ"
 */
public void setAttrJ(String attrJ) {
    this.attrJ = attrJ;
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
 * @return  the attrI
 * @uml.property  name="attrI"
 */
public String getAttrI() {
    return attrI;
  }
  /**
 * @param attrI  the attrI to set
 * @uml.property  name="attrI"
 */
public void setAttrI(String attrI) {
    this.attrI = attrI;
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
    StringBuffer ret = new StringBuffer("<TABLE");
    if(UtilString.isNotEmpty(getAttrDocumentcookiesubstring())) ret.append(" documentcookiesubstring=\"").append(getAttrDocumentcookiesubstring()).append("\"");
    if(UtilString.isNotEmpty(getAttrHeight())) ret.append(" height=\"").append(getAttrHeight()).append("\"");
    if(UtilString.isNotEmpty(getAttrBackground())) ret.append(" background=\"").append(getAttrBackground()).append("\"");
    if(UtilString.isNotEmpty(getAttrContent())) ret.append(" content=\"").append(getAttrContent()).append("\"");
    if(UtilString.isNotEmpty(getAttrAlign())) ret.append(" align=\"").append(getAttrAlign()).append("\"");
    if(UtilString.isNotEmpty(getAttrVar())) ret.append(" var=\"").append(getAttrVar()).append("\"");
    if(UtilString.isNotEmpty(getAttrCellspacing())) ret.append(" cellspacing=\"").append(getAttrCellspacing()).append("\"");
    if(UtilString.isNotEmpty(getAttrId())) ret.append(" id=\"").append(getAttrId()).append("\"");
    if(UtilString.isNotEmpty(getAttrBorder())) ret.append(" border=\"").append(getAttrBorder()).append("\"");
    if(UtilString.isNotEmpty(getAttrCellpadding())) ret.append(" cellpadding=\"").append(getAttrCellpadding()).append("\"");
    if(UtilString.isNotEmpty(getAttrJ())) ret.append(" j=\"").append(getAttrJ()).append("\"");
    if(UtilString.isNotEmpty(getAttrBgcolor())) ret.append(" bgcolor=\"").append(getAttrBgcolor()).append("\"");
    if(UtilString.isNotEmpty(getAttrI())) ret.append(" i=\"").append(getAttrI()).append("\"");
    if(UtilString.isNotEmpty(getAttrStringCharactere())) ret.append(" stringCharactere=\"").append(getAttrStringCharactere()).append("\"");
    if(UtilString.isNotEmpty(getAttrWidth())) ret.append(" width=\"").append(getAttrWidth()).append("\"");
    if(UtilString.isNotEmpty(getAttrClass())) ret.append(" class=\"").append(getAttrClass()).append("\"");
    ret.append(">");
    if (getContent()!=null) ret.append(getContent());
    ret.append("</TABLE>");
    return ret.toString();
  }
  public String toJsp() {
    StringBuffer ret = new StringBuffer("<html:TagTable");
    if(UtilString.isNotEmpty(getAttrDocumentcookiesubstring())) ret.append(" attrDocumentcookiesubstring=").append(getStringCharactere()).append(getAttrDocumentcookiesubstring()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrHeight())) ret.append(" attrHeight=").append(getStringCharactere()).append(getAttrHeight()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrBackground())) ret.append(" attrBackground=").append(getStringCharactere()).append(getAttrBackground()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrContent())) ret.append(" attrContent=").append(getStringCharactere()).append(getAttrContent()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrAlign())) ret.append(" attrAlign=").append(getStringCharactere()).append(getAttrAlign()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrVar())) ret.append(" attrVar=").append(getStringCharactere()).append(getAttrVar()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrCellspacing())) ret.append(" attrCellspacing=").append(getStringCharactere()).append(getAttrCellspacing()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrId())) ret.append(" attrId=").append(getStringCharactere()).append(getAttrId()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrBorder())) ret.append(" attrBorder=").append(getStringCharactere()).append(getAttrBorder()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrCellpadding())) ret.append(" attrCellpadding=").append(getStringCharactere()).append(getAttrCellpadding()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrJ())) ret.append(" attrJ=").append(getStringCharactere()).append(getAttrJ()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrBgcolor())) ret.append(" attrBgcolor=").append(getStringCharactere()).append(getAttrBgcolor()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrI())) ret.append(" attrI=").append(getStringCharactere()).append(getAttrI()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrStringCharactere())) ret.append(" attrStringCharactere=").append(getStringCharactere()).append(getAttrStringCharactere()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrWidth())) ret.append(" attrWidth=").append(getStringCharactere()).append(getAttrWidth()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrClass())) ret.append(" attrClass=").append(getStringCharactere()).append(getAttrClass()).append(getStringCharactere());
    ret.append(">\r\n");
    if (getContent()!=null) ret.append(getContent());
    ret.append("</html:TagTable>\r\n");
    return ret.toString();
  }
}
