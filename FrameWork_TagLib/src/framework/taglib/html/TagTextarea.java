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
public class TagTextarea extends BodyTagSupport {

  public static final String CONST_TYPE="TEXTAREA";
  public static final boolean CONST_HAS_ENDTAG=true;

  private String content=null;
  private String stringCharactere=null;
  private String attrContent;
  private String attrName;
  private String attrStringCharactere;
  private String attrRows;
  private String attrCols;

  public TagTextarea() {
  }

  public int doStartTag() {
    JspWriter out = pageContext.getOut();
    ServletRequest request = pageContext.getRequest();
    HttpSession session = pageContext.getSession();
    try {
      out.print("<TEXTAREA");
      if (UtilString.isNotEmpty(getAttrContent()))
        out.print(" CONTENT="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrContent(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrName()))
        out.print(" NAME="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrName(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrStringCharactere()))
        out.print(" STRINGCHARACTERE="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrStringCharactere(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrRows()))
        out.print(" ROWS="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrRows(), request, session, "")+getStringCharactere());
      if (UtilString.isNotEmpty(getAttrCols()))
        out.print(" COLS="+getStringCharactere()+UtilRequest.replaceParamByRequestValue(getAttrCols(), request, session, "")+getStringCharactere());
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
      out.print("</TEXTAREA>");
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
 * @return  the attrRows
 * @uml.property  name="attrRows"
 */
public String getAttrRows() {
    return attrRows;
  }
  /**
 * @param attrRows  the attrRows to set
 * @uml.property  name="attrRows"
 */
public void setAttrRows(String attrRows) {
    this.attrRows = attrRows;
  }
  /**
 * @return  the attrCols
 * @uml.property  name="attrCols"
 */
public String getAttrCols() {
    return attrCols;
  }
  /**
 * @param attrCols  the attrCols to set
 * @uml.property  name="attrCols"
 */
public void setAttrCols(String attrCols) {
    this.attrCols = attrCols;
  }

  public String toHtml() {
    StringBuffer ret = new StringBuffer("<TEXTAREA");
    if(UtilString.isNotEmpty(getAttrContent())) ret.append(" content=\"").append(getAttrContent()).append("\"");
    if(UtilString.isNotEmpty(getAttrName())) ret.append(" name=\"").append(getAttrName()).append("\"");
    if(UtilString.isNotEmpty(getAttrStringCharactere())) ret.append(" stringCharactere=\"").append(getAttrStringCharactere()).append("\"");
    if(UtilString.isNotEmpty(getAttrRows())) ret.append(" rows=\"").append(getAttrRows()).append("\"");
    if(UtilString.isNotEmpty(getAttrCols())) ret.append(" cols=\"").append(getAttrCols()).append("\"");
    ret.append(">");
    if (getContent()!=null) ret.append(getContent());
    ret.append("</TEXTAREA>");
    return ret.toString();
  }
  public String toJsp() {
    StringBuffer ret = new StringBuffer("<html:TagTextarea");
    if(UtilString.isNotEmpty(getAttrContent())) ret.append(" attrContent=").append(getStringCharactere()).append(getAttrContent()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrName())) ret.append(" attrName=").append(getStringCharactere()).append(getAttrName()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrStringCharactere())) ret.append(" attrStringCharactere=").append(getStringCharactere()).append(getAttrStringCharactere()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrRows())) ret.append(" attrRows=").append(getStringCharactere()).append(getAttrRows()).append(getStringCharactere());
    if(UtilString.isNotEmpty(getAttrCols())) ret.append(" attrCols=").append(getStringCharactere()).append(getAttrCols()).append(getStringCharactere());
    ret.append(">\r\n");
    if (getContent()!=null) ret.append(getContent());
    ret.append("</html:TagTextarea>\r\n");
    return ret.toString();
  }
}
