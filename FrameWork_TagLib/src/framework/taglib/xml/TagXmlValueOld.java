package framework.taglib.xml;

import framework.ressource.util.UtilRequest;
import framework.ressource.util.UtilSafe;
import framework.ressource.util.UtilString;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author  HP_Administrateur
 */
public class TagXmlValueOld extends TagSupport {
  protected static final char PATH_SEPARATOR = '/';
  protected static final char ATTRIBUTE_CAR = '@';
  protected static final char CURRENTNODE_CAR = '.';
  protected static final char CONDITION_START_CAR = '[';
  protected static final char CONDITION_END_CAR = ']';

  private String path;

  /**
   * Objets interne au TagLib
   */
  TagXml tagParent = null;

  public TagXmlValueOld() {
  }

  public int doStartTag() {
    JspWriter out = pageContext.getOut();
    ServletRequest request = pageContext.getRequest();
    HttpSession session = pageContext.getSession();
    Tag parent = getParent();
    while (parent != null) {
      if (parent instanceof TagXml) {
        tagParent = (TagXml)parent;
        break;
      }
      parent = parent.getParent();
    }
    if (tagParent != null) {
      try {
		out.print(elementValueFromXPath(tagParent.getCurrentNode()));
      }
      catch (DOMException ex) {ex.printStackTrace(new PrintWriter(out));}
      catch (IOException ex) {ex.printStackTrace(new PrintWriter(out));}
    }
    return EVAL_PAGE;
  }

  protected String elementValueFromXPath(Node currentNode) {
    String ret = "";
    JspWriter out = pageContext.getOut();
    ServletRequest request = pageContext.getRequest();
    HttpSession session = pageContext.getSession();
    try {
      if (UtilString.isNotEmpty(getPath()) && (currentNode!=null)) {
        String szPath = UtilRequest.replaceParamByRequestValue(getPath(), request, session, "");
        if (UtilString.isNotEmpty(szPath)) {
          if (szPath.startsWith(""+PATH_SEPARATOR))
            currentNode = tagParent.getDocument().getDocumentElement();
          String[] listPath = UtilString.split(szPath, PATH_SEPARATOR);
          int sizePath = UtilSafe.safeListSize(listPath)-1;
          if (sizePath>=0) {
            NodeList nodeList = null;
            String szName = null, szCondition = null;
            int i = 0;
            for (; i < sizePath; i++) {
              szPath = (String) UtilSafe.safeListGetElementAt(listPath, i);
              szName = xPathGetName(szPath);
              if ( (currentNode.getNodeType() == Node.ELEMENT_NODE) &&
                   (!currentNode.getNodeName().equals(szName)) &&
                   (!szName.equals(""+CURRENTNODE_CAR))) {
                nodeList = ((Element)currentNode).getElementsByTagName(szName);
                if ( (nodeList != null) && (nodeList.getLength() > 0)) {
                  currentNode = (Element)nodeList.item(0);
                }
              }
            }
            szPath = (String) UtilSafe.safeListGetElementAt(listPath, i);
            szName = xPathGetName(szPath);
            if (szName.startsWith(""+ATTRIBUTE_CAR)) {
              szName = szName.substring(1, szName.length());
              ret = ((Element)currentNode).getAttribute(szName);
            }
            else if (szName.equals(""+CURRENTNODE_CAR)) {
              szName = szPath.substring(1, szName.length());
              ret = getElementText((Element)currentNode);
            }
            else if ( (currentNode.getNodeType() == Node.ELEMENT_NODE) &&
                      (!currentNode.getNodeName().equals(szName))) {
              nodeList = ((Element)currentNode).getElementsByTagName(szName);
              if ( (nodeList != null) && (nodeList.getLength() > 0)) {
                ret = getElementText((Element)nodeList.item(0));
              }
            }
          }
        }
      }
    }
    catch (Exception ex) {ex.printStackTrace(new PrintWriter(out));}
    return ret;
  }

  /**
   * Retourne le nom contenu dans une element du xPath (ex: elm2 dans elm2[param == '1']')
   * @xPathElement Un element d'un chemin XPath (ex: elm2[param == '1'] dans '/elm1/elm2[param == '1']/elm3')
   * @return Le nom contenu dans une element du xPath
   */
  protected String xPathGetName(String xPathElement) {
    String ret = xPathElement;
    if (xPathElement!=null) {
      int iStart = xPathElement.indexOf(CONDITION_START_CAR);
      int iEnd = xPathElement.indexOf(CONDITION_END_CAR);
      if ( (iStart>-1)&&(iEnd>-1)&&(iStart>iEnd) )
        ret = xPathElement.substring(0, iStart);
    }
    return ret;
  }

  /**
   * Retourne le nom contenu dans une element du xPath (ex: param == '1' dans elm2[param == '1']')
   * @xPathElement Un element d'un chemin XPath (ex: elm2[param == '1'] dans '/elm1/elm2[param == '1']/elm3')
   * @return La condition contenu dans une element du xPath
   */
  protected String xPathGetCondition(String xPathElement) {
    String ret = xPathElement;
    if (xPathElement!=null) {
      int iStart = xPathElement.indexOf(CONDITION_START_CAR);
      int iEnd = xPathElement.indexOf(CONDITION_END_CAR);
      if ( (iStart>-1)&&(iEnd>-1)&&(iStart>iEnd) )
        ret = xPathElement.substring(iStart+1, iEnd-1);
    }
    return ret;
  }

  protected String getElementText(Element element) {
    StringBuffer ret = new StringBuffer();
    NodeList nodeList = element.getChildNodes();
    if (nodeList!=null){
      Node node = null;
      for(int i=0 ; i<nodeList.getLength() ; i++) {
        node = nodeList.item(i);
        if (node.getNodeType() == Node.TEXT_NODE) {
          ret.append(node.getNodeValue().trim());
        }
      }
    }
    return ret.toString();
  }

  /**
 * @return  the path
 * @uml.property  name="path"
 */
public String getPath() {
    return path;
  }

  /**
 * @param path  the path to set
 * @uml.property  name="path"
 */
public void setPath(String path) {
    this.path = path;
  }
}
