package framework.taglib.file;

import framework.ressource.util.UtilEncoder;
import framework.ressource.util.UtilReflect;
import framework.ressource.util.UtilRequest;
import framework.ressource.util.UtilSafe;
import framework.ressource.util.UtilString;
import framework.taglib.ancestor.AncestorTagSupport;
import framework.trace.Trace;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;

/**
 * @author  HP_Administrateur
 */
public class TagFileListNavigator extends AncestorTagSupport {
  private final static String CST_STRING_TO_REPLACE = "[index]";
  private final static String CST_STRING_CHARACTERE = "\"";
  private final static String CST_BOOLEAN_TRUE = "true";
  private final static String CST_DEFAULT_INDEX_START = "0";
  private final static String CST_DEFAULT_INDEX_STEP = "1";

  // Taille de la liste de fichier
  private String size = null;
  // Nom de l'index de navigation en request
  private String indexName = null;
  // Index de depart
  private String indexStart = null;
  // Index de fin
  private String indexEnd = null;
  // Incrementation de l'index
  private String indexStep = null;
  // Nombre d'index affich�
  private String indexQuantity = null;
  private String isShowPrevious = null;
  private String isShowNext = null;
  private String libellePrevious = null;
  private String libelleNext = null;
  private String separator = null;

  private String stringCharactere=null;
  private String stringToReplace=null;
  private String attrHref=null;
  private String attrClass=null;
  private String attrClassSelected=null;

  // Portee de la liste de fichier soit en 'request' soit en 'session'. En 'request' par defaut si vide.
  private String scope = null;
  // Nom de stockage de la liste de fichier en 'request' ou 'session' en fonction du scope.
  private String name = null;

  public TagFileListNavigator() {
  }

  public int doStartTag() {
    ServletRequest request = pageContext.getRequest();
    HttpSession session = pageContext.getSession();
    try {
      JspWriter out = pageContext.getOut();
      if (UtilString.isNotEmpty(getSize())||
    	  UtilString.isNotEmpty(getName())) {
        StringBuffer tmpOut = new StringBuffer();
        String szIndex = request.getParameter(getIndexName());
        String szSeparator = getSeparator();
        int iSize = 0;
        if (UtilString.isNotEmpty(getSize())) {
            iSize = Integer.parseInt(getSize());
        }
        if (UtilString.isNotEmpty(getName())) {
        	Object bean = UtilString.isEqualsIgnoreCase("session", getScope()) ? session.getAttribute(getName()) : request.getAttribute(getName());
        	Integer beanSize = new Integer(UtilSafe.safeListSize(bean));
        	iSize = (UtilString.isNotEmpty(getSize()) && (beanSize.intValue() > iSize)) ? iSize : beanSize.intValue();
        }
        int iStep = Integer.parseInt(getIndexStep());
        int iEnd = (UtilString.isNotEmpty(indexEnd)) ? Integer.parseInt(getIndexEnd()) : iSize;
        int iQuantity = (UtilString.isNotEmpty(indexQuantity)) ? Integer.parseInt(getIndexQuantity()) * iStep : iEnd;
        int iStart = UtilString.isNotEmpty(szIndex) ? ((int)(Integer.parseInt(szIndex)/iQuantity))*iQuantity : Integer.parseInt(getIndexStart());
        iEnd = (iEnd < (iSize - iStep)) ? iEnd : iSize;
        if (iEnd > iStart) {
	        if (UtilString.isNotEmpty(isShowPrevious) && getIsShowPrevious().equalsIgnoreCase(CST_BOOLEAN_TRUE) && (iStart >= iQuantity)) {
	          formatAndAppend(tmpOut, iStart - iStep, iQuantity, iStep, getLibellePrevious());
	          tmpOut.append(szSeparator);
	        }
	        for (int i = iQuantity; (iStart < iEnd) && (iEnd > iStep) && (i > 0); iStart += iStep, i -= iStep) {
	          formatAndAppend(tmpOut, iStart, iQuantity, (iStep+1));
	          if ( (UtilString.isNotEmpty(szSeparator)) &&
	              (iStart < (iEnd - iStep)) && (i > iStep))
	            tmpOut.append(szSeparator);
	        }
	        if (UtilString.isNotEmpty(getIsShowNext()) && getIsShowNext().equalsIgnoreCase(CST_BOOLEAN_TRUE) && (iStart < (iEnd - iQuantity + 1))) {
	          tmpOut.append(szSeparator);
	          formatAndAppend(tmpOut, iStart, iQuantity, iStep, getLibelleNext());
	        }
	        out.print(tmpOut.toString());
        }
      }
    }
    catch (IOException ioe) {
      Trace.ERROR("Error in BodingTag: ", ioe);
    }
    return SKIP_BODY;
  }

  protected void formatAndAppend(StringBuffer tmpOut, int iStart, int iEnd, int iStep) throws UnsupportedEncodingException {
    formatAndAppend(tmpOut, iStart, iEnd, iStep, String.valueOf(iStart));
  }
  protected void formatAndAppend(StringBuffer tmpOut, int iStart, int iEnd, int iStep, String libelle) throws UnsupportedEncodingException {
    HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
    HttpSession session = pageContext.getSession();
    String szIndex = request.getParameter(getIndexName());
    String szAttrClass = getAttrClass();
    if (UtilString.isNotEmpty(getAttrClassSelected())&&UtilString.isNotEmpty(szIndex)&&(Integer.parseInt(szIndex)==iStart))
      szAttrClass = getAttrClassSelected();
    tmpOut.append("<A");
    if (UtilString.isNotEmpty(szAttrClass))
      tmpOut.append(" CLASS = ").append(getStringCharactere()).append(UtilRequest.replaceParamByRequestValue(szAttrClass, request, session, "")).append(getStringCharactere());
    if (UtilString.isNotEmpty(getAttrHref())) {
      String szAttrHref = getAttrHref();
      if (szAttrHref.indexOf(getStringToReplace())>-1)
        szAttrHref = UtilString.replaceAll(getAttrHref(), getStringToReplace(), getIndexName()+"="+String.valueOf(iStart+1));
      tmpOut.append(" HREF = ").append(getStringCharactere()).append(UtilRequest.replaceParamByRequestValue(szAttrHref, request, session, "")).append(getStringCharactere());
    } else {
      tmpOut.append(" HREF = ").append(getStringCharactere()).append(request.getRequestURI()).append("?").append(getIndexName()).append("=").append(String.valueOf(iStart+1)).append(getStringCharactere());
    }
    tmpOut.append(">").append(UtilRequest.replaceParamByRequestValue(UtilString.replaceAll(libelle, CST_STRING_TO_REPLACE, String.valueOf(iStart+1)), request, session, "")).append("</A>");
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
 * @param size  the size to set
 * @uml.property  name="size"
 */
public void setSize(String size) {
    this.size = size;
  }

  /**
 * @param indexName  the indexName to set
 * @uml.property  name="indexName"
 */
public void setIndexName(String indexName) {
    this.indexName = indexName;
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
    return UtilRequest.replaceParamByRequestValue(indexEnd, pageContext.getRequest(), pageContext.getSession(), "");
  }

  /**
 * @return  the separator
 * @uml.property  name="separator"
 */
public String getSeparator() {
    return UtilRequest.replaceParamByRequestValue(separator, pageContext.getRequest(), pageContext.getSession(), "");
  }

  /**
 * @return  the attrHref
 * @uml.property  name="attrHref"
 */
public String getAttrHref() {
    return UtilRequest.replaceParamByRequestValue(attrHref, pageContext.getRequest(), pageContext.getSession(), "");
  }

  /**
 * @return  the attrClass
 * @uml.property  name="attrClass"
 */
public String getAttrClass() {
    return UtilRequest.replaceParamByRequestValue(attrClass, pageContext.getRequest(), pageContext.getSession(), "");
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
    return UtilRequest.replaceParamByRequestValue(indexQuantity, pageContext.getRequest(), pageContext.getSession(), "");
  }

  /**
 * @return  the isShowPrevious
 * @uml.property  name="isShowPrevious"
 */
public String getIsShowPrevious() {
    return UtilRequest.replaceParamByRequestValue(isShowPrevious, pageContext.getRequest(), pageContext.getSession(), "");
  }

  /**
 * @return  the isShowNext
 * @uml.property  name="isShowNext"
 */
public String getIsShowNext() {
    return UtilRequest.replaceParamByRequestValue(isShowNext, pageContext.getRequest(), pageContext.getSession(), "");
  }

  /**
 * @return  the attrClassSelected
 * @uml.property  name="attrClassSelected"
 */
public String getAttrClassSelected() {
    return UtilRequest.replaceParamByRequestValue(attrClassSelected, pageContext.getRequest(), pageContext.getSession(), "");
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

  /**
 * @return  the size
 * @uml.property  name="size"
 */
public String getSize() {
    return UtilRequest.replaceParamByRequestValue(size, pageContext.getRequest(), pageContext.getSession(), "");
  }

  /**
 * @return  the indexName
 * @uml.property  name="indexName"
 */
public String getIndexName() {
    return UtilRequest.replaceParamByRequestValue(indexName, pageContext.getRequest(), pageContext.getSession(), "");
  }

/**
 * @return  the name
 * @uml.property  name="name"
 */
public String getName() {
	return name;
}

/**
 * @param name  the name to set
 * @uml.property  name="name"
 */
public void setName(String name) {
	this.name = name;
}

/**
 * @return  the scope
 * @uml.property  name="scope"
 */
public String getScope() {
	return scope;
}

/**
 * @param scope  the scope to set
 * @uml.property  name="scope"
 */
public void setScope(String scope) {
	this.scope = scope;
}
}
