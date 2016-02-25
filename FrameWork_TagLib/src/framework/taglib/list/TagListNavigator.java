package framework.taglib.list;

import framework.beandata.BeanDatabase;
import framework.ressource.util.UtilReflect;
import framework.ressource.util.UtilRequest;
import framework.ressource.util.UtilString;
import framework.taglib.ancestor.AncestorTagSupport;
import framework.trace.Trace;
import java.io.IOException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;

/**
 * @author  HP_Administrateur
 */
public class TagListNavigator extends AncestorTagSupport {
  private final static String CST_STRING_TO_REPLACE = "[index]";
  private final static String CST_STRING_CHARACTERE = "\"";
  private final static String CST_BOOLEAN_TRUE = "true";
  private final static String CST_DEFAULT_INDEX_START = "0";
  private final static String CST_DEFAULT_INDEX_STEP = "1";

  private String indexStart = null;
  private String indexEnd = null;
  private String indexStep = null;
  private String indexQuantity = null;
  private String isShowPrevious = null;
  private String isShowNext = null;
  private String libellePrevious = null;
  private String libelleNext = null;
  private String separator = null;
  private String bean=null;
  private String beanScope=null;

  private String stringCharactere=null;
  private String stringToReplace=null;
  private String attrHref=null;
  private String attrClass=null;
  private String attrClassSelected=null;

  public TagListNavigator() {
  }

  public int doStartTag() {
    ServletRequest request = pageContext.getRequest();
    HttpSession session = pageContext.getSession();
    try {
      JspWriter out = pageContext.getOut();
      if (UtilString.isNotEmpty(getBean())) {
        Object bean = ("session".equalsIgnoreCase(getBeanScope())) ? session.getAttribute(getBean()) : request.getAttribute(getBean());
        if((bean!=null)&&(bean instanceof BeanDatabase)) {
          Integer beanSize = (Integer)UtilReflect.safeInvokeMethod(bean, "getSize", null, null);
          if (beanSize != null) {
            StringBuffer tmpOut = new StringBuffer();
            String szIndex = request.getParameter(getBean());
            int iSize = beanSize.intValue();
            int iStep = Integer.parseInt(getIndexStep());
            int iEnd = (UtilString.isNotEmpty(getIndexEnd())) ? Integer.parseInt(getIndexEnd()) : iSize;
            int iQuantity = (UtilString.isNotEmpty(getIndexQuantity())) ? Integer.parseInt(getIndexQuantity())*iStep : iEnd;
            int iStart = UtilString.isNotEmpty(szIndex) ? ((int)(Integer.parseInt(szIndex)/iQuantity))*iQuantity : Integer.parseInt(getIndexStart());
            iEnd = (iEnd<(iSize-iStep)) ? iEnd : iSize;
            if ( UtilString.isNotEmpty(getIsShowPrevious()) &&
                 getIsShowPrevious().equalsIgnoreCase(CST_BOOLEAN_TRUE) &&
                 (iStart>=iQuantity) ) {
              formatAndAppend(tmpOut, iStart-iStep, iQuantity, iStep, getLibellePrevious());
              tmpOut.append(getSeparator());
            }
            for ( int i=iQuantity ; (iStart<iEnd)&&(iEnd>iStep)&&(i>0) ; iStart+=iStep, i-=iStep ) {
              formatAndAppend(tmpOut, iStart, iQuantity, iStep);
              if((UtilString.isNotEmpty(getSeparator()))&&(iStart<(iEnd-iStep))&&(i>iStep))
                tmpOut.append(getSeparator());
            }
            if ( UtilString.isNotEmpty(getIsShowNext()) &&
                 getIsShowNext().equalsIgnoreCase(CST_BOOLEAN_TRUE) &&
                 (iStart<(iEnd-iQuantity)) ) {
              tmpOut.append(getSeparator());
              formatAndAppend(tmpOut, iStart, iQuantity, iStep, getLibelleNext());
            }
            out.print(tmpOut.toString());
          }
        }
      }
    }
    catch (IOException ioe) {
      Trace.ERROR("Error in BodingTag: ", ioe);
    }
    return SKIP_BODY;
  }

  protected void formatAndAppend(StringBuffer tmpOut, int iStart, int iEnd, int iStep) {
    formatAndAppend(tmpOut, iStart, iEnd, iStep, String.valueOf(iStart));
  }
  protected void formatAndAppend(StringBuffer tmpOut, int iStart, int iEnd, int iStep, String libelle) {
    HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
    HttpSession session = pageContext.getSession();
    String szIndex = request.getParameter(getBean());
    String szAttrClass = getAttrClass();
    if (UtilString.isNotEmpty(getAttrClassSelected())&&UtilString.isNotEmpty(szIndex)&&(Integer.parseInt(szIndex)==iStart))
      szAttrClass = getAttrClassSelected();
    tmpOut.append("<A");
    if (UtilString.isNotEmpty(szAttrClass))
      tmpOut.append(" CLASS = ").append(getStringCharactere()).append(UtilRequest.replaceParamByRequestValue(szAttrClass, request, session, "")).append(getStringCharactere());
    if (UtilString.isNotEmpty(getAttrHref())) {
      String szAttrHref = getAttrHref();
      if (szAttrHref.indexOf(getStringToReplace())>-1)
        szAttrHref = getAttrHref().replaceAll(getStringToReplace(), getBean()+"="+String.valueOf(iStart));
      tmpOut.append(" HREF = ").append(getStringCharactere()).append(UtilRequest.replaceParamByRequestValue(szAttrHref, request, session, "")).append(getStringCharactere());
    } else {
      tmpOut.append(" HREF = ").append(getStringCharactere()).append(request.getRequestURI()).append("?").append(getBean()).append("=").append(String.valueOf(iStart)).append(getStringCharactere());
    }
    tmpOut.append(">").append(UtilRequest.replaceParamByRequestValue(libelle.replaceAll(CST_STRING_TO_REPLACE, String.valueOf(iStart)), request, session, "")).append("</A>");
  }

  /**
 * @param bean  the bean to set
 * @uml.property  name="bean"
 */
public void setBean(String bean) {
    this.bean = bean;
  }

  /**
 * @param beanScope  the beanScope to set
 * @uml.property  name="beanScope"
 */
public void setBeanScope(String beanScope) {
    this.beanScope = beanScope;
  }

  /**
 * @param indexStart  the indexStart to set
 * @uml.property  name="indexStart"
 */
public void setIndexStart(String indexStart) {
    this.indexStart = indexStart;
  }

  /**
 * @param indexStep  the indexStep to set
 * @uml.property  name="indexStep"
 */
public void setIndexStep(String indexStep) {
    this.indexStep = indexStep;
  }

  /**
 * @param indexEnd  the indexEnd to set
 * @uml.property  name="indexEnd"
 */
public void setIndexEnd(String indexEnd) {
    this.indexEnd = indexEnd;
  }

  /**
 * @param separator  the separator to set
 * @uml.property  name="separator"
 */
public void setSeparator(String separator) {
    this.separator = separator;
  }

  /**
 * @param attrHref  the attrHref to set
 * @uml.property  name="attrHref"
 */
public void setAttrHref(String attrHref) {
    this.attrHref = attrHref;
  }

  /**
 * @param attrClass  the attrClass to set
 * @uml.property  name="attrClass"
 */
public void setAttrClass(String attrClass) {
    this.attrClass = attrClass;
  }

  /**
 * @param stringCharactere  the stringCharactere to set
 * @uml.property  name="stringCharactere"
 */
public void setStringCharactere(String stringCharactere) {
    this.stringCharactere = stringCharactere;
  }

  /**
 * @param stringToReplace  the stringToReplace to set
 * @uml.property  name="stringToReplace"
 */
public void setStringToReplace(String stringToReplace) {
    this.stringToReplace = stringToReplace;
  }

  /**
 * @param indexQuantity  the indexQuantity to set
 * @uml.property  name="indexQuantity"
 */
public void setIndexQuantity(String indexQuantity) {
    this.indexQuantity = indexQuantity;
  }

  /**
 * @param isShowPrevious  the isShowPrevious to set
 * @uml.property  name="isShowPrevious"
 */
public void setIsShowPrevious(String isShowPrevious) {
    this.isShowPrevious = isShowPrevious;
  }

  /**
 * @param isShowNext  the isShowNext to set
 * @uml.property  name="isShowNext"
 */
public void setIsShowNext(String isShowNext) {
    this.isShowNext = isShowNext;
  }

  /**
 * @param attrClassSelected  the attrClassSelected to set
 * @uml.property  name="attrClassSelected"
 */
public void setAttrClassSelected(String attrClassSelected) {
    this.attrClassSelected = attrClassSelected;
  }

  /**
 * @param libelleNext  the libelleNext to set
 * @uml.property  name="libelleNext"
 */
public void setLibelleNext(String libelleNext) {
    this.libelleNext = libelleNext;
  }

  /**
 * @param libellePrevious  the libellePrevious to set
 * @uml.property  name="libellePrevious"
 */
public void setLibellePrevious(String libellePrevious) {
    this.libellePrevious = libellePrevious;
  }

  /**
 * @return  the bean
 * @uml.property  name="bean"
 */
public String getBean() {
    return bean;
  }

  /**
 * @return  the beanScope
 * @uml.property  name="beanScope"
 */
public String getBeanScope() {
    return beanScope;
  }

  /**
 * @return  the indexStart
 * @uml.property  name="indexStart"
 */
public String getIndexStart() {
    return (indexStart==null) ? CST_DEFAULT_INDEX_START : indexStart;
  }

  /**
 * @return  the indexStep
 * @uml.property  name="indexStep"
 */
public String getIndexStep() {
    return (indexStep==null) ? CST_DEFAULT_INDEX_STEP : indexStep;
  }

  /**
 * @return  the indexEnd
 * @uml.property  name="indexEnd"
 */
public String getIndexEnd() {
    return indexEnd;
  }

  /**
 * @return  the separator
 * @uml.property  name="separator"
 */
public String getSeparator() {
    return separator;
  }

  /**
 * @return  the attrHref
 * @uml.property  name="attrHref"
 */
public String getAttrHref() {
    return attrHref;
  }

  /**
 * @return  the attrClass
 * @uml.property  name="attrClass"
 */
public String getAttrClass() {
    return attrClass;
  }

  /**
 * @return  the stringCharactere
 * @uml.property  name="stringCharactere"
 */
public String getStringCharactere() {
    return UtilString.isEmpty(stringCharactere) ? CST_STRING_CHARACTERE : stringCharactere;
  }

  /**
 * @return  the stringToReplace
 * @uml.property  name="stringToReplace"
 */
public String getStringToReplace() {
    return UtilString.isEmpty(stringToReplace) ? CST_STRING_TO_REPLACE : stringToReplace;
  }

  /**
 * @return  the indexQuantity
 * @uml.property  name="indexQuantity"
 */
public String getIndexQuantity() {
    return indexQuantity;
  }

  /**
 * @return  the isShowPrevious
 * @uml.property  name="isShowPrevious"
 */
public String getIsShowPrevious() {
    return isShowPrevious;
  }

  /**
 * @return  the isShowNext
 * @uml.property  name="isShowNext"
 */
public String getIsShowNext() {
    return isShowNext;
  }

  /**
 * @return  the attrClassSelected
 * @uml.property  name="attrClassSelected"
 */
public String getAttrClassSelected() {
    return attrClassSelected;
  }

  /**
 * @return  the libelleNext
 * @uml.property  name="libelleNext"
 */
public String getLibelleNext() {
    return UtilString.isEmpty(libelleNext) ? new String(CST_STRING_TO_REPLACE) : libelleNext;
  }

  /**
 * @return  the libellePrevious
 * @uml.property  name="libellePrevious"
 */
public String getLibellePrevious() {
    return UtilString.isEmpty(libellePrevious) ? new String(CST_STRING_TO_REPLACE) : libellePrevious;
  }
}
