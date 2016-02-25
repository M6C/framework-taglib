package framework.taglib.list;

import framework.ressource.util.UtilRequest;
import framework.ressource.util.UtilSafe;
import framework.ressource.util.UtilString;
import framework.ressource.util.UtilVector;
import framework.trace.Trace;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * @author  HP_Administrateur
 */
public class TagList extends BodyTagSupport {

  private Object list = null;
  private Object item = null;
  private int size = 0;
  private int index = 0;
  private int iEnd = 0;

  // Porte de la liste soit en 'request' soit en 'session'. En 'request' par defaut si vide.
  private String scope = null;
  // Nom de stockage de la liste de fichier en 'request' ou 'session' en fonction du scope.
  private String name = null;
  // Index de debut de parcoure
  private String indexStart = null;
  // Index de fin de parcoure
  private String indexEnd = null;
  // Valeur d'incrementation de l'index
  private String indexStep = null;

  public TagList() {
  }

  public int doStartTag() {
    try {
      ServletRequest request = pageContext.getRequest();
      HttpSession session = pageContext.getSession();
      String szIndexStart = getIndexStartReplaceParamByRequestValue();
      String szIndexEnd = getIndexEndReplaceParamByRequestValue();
      String szName = getNameReplaceParamByRequestValue();
      String szScope = getScopeReplaceParamByRequestValue();
      index = UtilString.isNotEmpty(szIndexStart) ? Integer.parseInt(szIndexStart) : 0;
      if (UtilString.isNotEmpty(szName)) {
        if (szName.indexOf('$')<0) {
          if ("session".equalsIgnoreCase(szScope))
            szName = "S$" + szName;
          else
            szName = "R$" + szName;
        }
        list = UtilRequest.findObject(request, session, szName);
        size = UtilSafe.safeListSize(list);
        if ((list!=null)&&(index<size))
          item = UtilSafe.safeListGetElementAt(list, index);
        if (UtilString.isNotEmpty(szIndexEnd)) {
          int tmpIEnd = Integer.parseInt(szIndexEnd);
          if (list!=null) {
            if (tmpIEnd>size)
              iEnd = size;
            else
              iEnd = tmpIEnd;
          }
        }
        else if (list!=null)
          iEnd = size;
      }
    }
    catch(Exception ex) {
    }
    return (((list!=null)&&(iEnd>index)) ? EVAL_BODY_BUFFERED : SKIP_BODY);
  }

  public int doAfterBody() {
    BodyContent bc = getBodyContent();
    String szIndexStep = getIndexStepReplaceParamByRequestValue();
    index+=UtilString.isEmpty(szIndexStep) ? 1 : Integer.parseInt(szIndexStep);
    if ((list!=null)&&(index<size))
      item = UtilSafe.safeListGetElementAt(list, index);
    if (bc != null) {
      try {
        JspWriter out = bc.getEnclosingWriter();
        out.println(bc.getString());
        bc.clearBody();
      }
      catch (IOException ioe) {
        Trace.ERROR("Error in BodingTag: ", ioe);
      }
    }
    return (((list!=null)&&(iEnd>index)&&(size>index)) ? EVAL_BODY_BUFFERED : SKIP_BODY);
  }

  public int doEndTag() {
    return EVAL_PAGE;
  }

  public Object getCurrentItem() {
    return item;
  }

  public int getCurrentIndex() {
    return index;
  }

  /**
 * @return  the indexStep
 * @uml.property  name="indexStep"
 */
public String getIndexStep() {
    return UtilRequest.replaceParamByRequestValue(indexStep, pageContext.getRequest(), pageContext.getSession(), "");
  }

  /**
 * @return  the indexStart
 * @uml.property  name="indexStart"
 */
public String getIndexStart() {
    return UtilRequest.replaceParamByRequestValue(indexStart, pageContext.getRequest(), pageContext.getSession(), "");
  }

  /**
 * @return  the indexEnd
 * @uml.property  name="indexEnd"
 */
public String getIndexEnd() {
    return UtilRequest.replaceParamByRequestValue(indexEnd, pageContext.getRequest(), pageContext.getSession(), "");
  }

  /**
 * @return  the name
 * @uml.property  name="name"
 */
public String getName() {
    return UtilRequest.replaceParamByRequestValue(name, pageContext.getRequest(), pageContext.getSession(), "");
  }

  /**
 * @param indexStep  the indexStep to set
 * @uml.property  name="indexStep"
 */
public void setIndexStep(String indexStep) {
    this.indexStep = indexStep;
  }

  /**
 * @param indexStart  the indexStart to set
 * @uml.property  name="indexStart"
 */
public void setIndexStart(String indexStart) {
    this.indexStart = indexStart;
  }

  /**
 * @param indexEnd  the indexEnd to set
 * @uml.property  name="indexEnd"
 */
public void setIndexEnd(String indexEnd) {
    this.indexEnd = indexEnd;
  }

  /**
 * @param name  the name to set
 * @uml.property  name="name"
 */
public void setName(String name) {
    this.name = name;
  }

  /**
 * @param scope  the scope to set
 * @uml.property  name="scope"
 */
public void setScope(String scope) {
    this.scope = scope;
  }

  /**
 * @return  the scope
 * @uml.property  name="scope"
 */
public String getScope() {
    return UtilRequest.replaceParamByRequestValue(scope, pageContext.getRequest(), pageContext.getSession(), "");
  }

  public String getNameReplaceParamByRequestValue() {
    return UtilRequest.replaceParamByRequestValue(name, pageContext.getRequest(), pageContext.getSession(), "");
  }

  public String getScopeReplaceParamByRequestValue() {
    return UtilRequest.replaceParamByRequestValue(scope, pageContext.getRequest(), pageContext.getSession(), "");
  }

  public String getIndexStartReplaceParamByRequestValue() {
    return UtilRequest.replaceParamByRequestValue(indexStart, pageContext.getRequest(), pageContext.getSession(), "");
  }

  public String getIndexEndReplaceParamByRequestValue() {
    return UtilRequest.replaceParamByRequestValue(indexEnd, pageContext.getRequest(), pageContext.getSession(), "");
  }

  public String getIndexStepReplaceParamByRequestValue() {
    return UtilRequest.replaceParamByRequestValue(indexStep, pageContext.getRequest(), pageContext.getSession(), "");
  }
}
