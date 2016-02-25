package framework.taglib.xml;

import framework.ressource.util.UtilRequest;
import framework.ressource.util.UtilString;
import framework.ressource.util.UtilXML;
import framework.ressource.xpath.XPathResult;
import framework.trace.Trace;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.Tag;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author  HP_Administrateur
 */
public class TagXml extends BodyTagSupport {
  /**
   * Param�tres du TagLib
   */
  private String fileName;
  private String path;
  private String step;
  private String name;
  private String scope;

  /**
   * Objets interne au TagLib
   */
  private Document document = null;
  private Node root;
  private NodeList childList;
  private XPathResult xpathResult;
  private Node currentNode;
  private int index;
  private int iStep;

  public TagXml() {
  }

  public int doStartTag() {
    JspWriter out = pageContext.getOut();
    ServletRequest request = pageContext.getRequest();
    HttpSession session = pageContext.getSession();
    index = 0;
    iStep = UtilString.isEmpty(getStep()) ? 1 : Integer.parseInt(getStepReplaceParamByRequestValue());
    try {
      /**
       * Construction du document Xml � partir d'un fichier
       */
      if (UtilString.isNotEmpty(getFileName())) {
        File fileInit = new File(getFileName());
        if ( (fileInit != null) && (fileInit.isFile()) && (fileInit.exists())) {
          // Cramp;eacute;ation des outils de Parse du fichier XML
          DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
          DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
          // Lecture et Parse du Fichier XML
          document = docBuilder.parse(fileInit);
          if (document!=null) {
            root = document.getDocumentElement();
            root.normalize();
          }
        }
      }
      /**
       * Recuperation du document Xml � partir de la 'request' ou 'session' de l'utilisateur
       */
      else if (UtilString.isNotEmpty(getName())) {
        String szName = UtilRequest.replaceParamByRequestValue(getName(), request, session, "");
        String szScope = "request";
        if (UtilString.isNotEmpty(getScope()))
          szScope = UtilRequest.replaceParamByRequestValue(getScope(), request, session, "");
        if (UtilString.isEqualsIgnoreCase("session", szScope))
          document = (Document)session.getAttribute(szName);
        else
          document = (Document)request.getAttribute(szName);
        if (document!=null) {
          root = document.getDocumentElement();
          root.normalize();
        }
      }
      /**
       * Recuperation du document Xml � partir d'un taglib 'TagXml' parent
       */
      else if (UtilString.isNotEmpty(getPath())) {
        TagXml tagParent = null;
        Tag parent = getParent();
        while (parent != null) {
          if (parent instanceof TagXml) {
            tagParent = (TagXml) parent;
          }
          parent = parent.getParent();
        }
        if (tagParent!=null) {
          document = tagParent.getDocument();
          root = tagParent.getCurrentNode();
        }
      }

      if(root!=null) {
		// Recupere la liste des noeud enfants
		xpathResult = getXPathResult(root);
		if (xpathResult != null) {
			currentNode = xpathResult.iterateNext();
		}
      }
    }
    catch (Exception ex) {
        ex.printStackTrace(new PrintWriter(System.err));
        ex.printStackTrace(new PrintWriter(out));
    }
	return ((currentNode!= null) ? EVAL_BODY_INCLUDE : SKIP_BODY);
  }

  public int doAfterBody() {
    BodyContent bc = getBodyContent();
	if (xpathResult != null) {
	  try {
	  	if (bc != null) {
			JspWriter out = bc.getEnclosingWriter();
			out.println(bc.getString());
			bc.clearBody();
	  	}
		currentNode = xpathResult.iterateNext();
	  }
	  catch (IOException ioe) {
		Trace.ERROR("Error in doAfterBody: ", ioe);
	  }
	}
	return ((currentNode!= null) ? EVAL_BODY_AGAIN : SKIP_BODY);
  }

  public int doEndTag() {
	return EVAL_PAGE;
  }

  /**
   * Retourne un noeud enfants � partir du chemin initialis� dans 'rootPath'
   * Si 'rootPath' commence par '/' alors la recherche s'effectu � partir de l'element root
   * @param currentNode Node Le noeud � partir du quel la recherche commance
   * @return Node Un noeud
   */
  protected Node pathToNode(Node currentNode) {
	Node ret = null;
	ServletRequest request = pageContext.getRequest();
	HttpSession session = pageContext.getSession();
	if (UtilString.isNotEmpty(getPath()) && (currentNode!=null)) {
	  String szPath = UtilRequest.replaceParamByRequestValue(getPath(), request, session, "");
	  if (UtilString.isNotEmpty(szPath)) {
		ret = UtilXML.getXPathNode(document, currentNode, szPath);
	  }
	}
	return ret;
  }

  /**
   * Retourne un noeud enfants � partir du chemin initialis� dans 'rootPath'
   * Si 'rootPath' commence par '/' alors la recherche s'effectu � partir de l'element root
   * @param currentNode Node Le noeud � partir du quel la recherche commance
   * @return Node Un noeud
   */
  protected XPathResult getXPathResult(Node currentNode) {
	XPathResult ret = null;
	ServletRequest request = pageContext.getRequest();
	HttpSession session = pageContext.getSession();
	if (UtilString.isNotEmpty(getPath()) && (currentNode!=null)) {
	  String szPath = UtilRequest.replaceParamByRequestValue(getPath(), request, session, "");
	  if (UtilString.isNotEmpty(szPath)) {
		ret = UtilXML.getXPathResultNodeUnordered(document, currentNode, szPath);
	  }
	}
	return ret;
  }

  /**
 * @return  the currentNode
 * @uml.property  name="currentNode"
 */
public Node getCurrentNode() {
  	return currentNode;
  }

  /**
 * @return
 * @uml.property  name="fileName"
 */
  public String getFileName() {
    return fileName;
  }/**
 * @return
 * @uml.property  name="step"
 */
  public String getStep() {
    return step;
  }

  /**
 * @return  the name
 * @uml.property  name="name"
 */
public String getName() {
    return name;
  }

  /**
 * @return  the scope
 * @uml.property  name="scope"
 */
public String getScope() {
    return scope;
  }

  /**
 * @return  the root
 * @uml.property  name="root"
 */
public Node getRoot() {
    return root;
  }

  /**
 * @return  the document
 * @uml.property  name="document"
 */
public Document getDocument() {
    return document;
  }

  /**
 * @return  the path
 * @uml.property  name="path"
 */
public String getPath() {
    return path;
  }

  /**
 * @param  string
 * @uml.property  name="fileName"
 */
  public void setFileName(String string) {
    fileName = string;
  }/**
 * @param  string
 * @uml.property  name="step"
 */
  public void setStep(String string) {
    step = string;
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

  public void setRoot(Element root) {
    this.root = root;
  }

  /**
 * @param document  the document to set
 * @uml.property  name="document"
 */
public void setDocument(Document document) {
    this.document = document;
  }

  /**
 * @param path  the path to set
 * @uml.property  name="path"
 */
public void setPath(String path) {
    this.path = path;
  }

  public String getFileNameReplaceParamByRequestValue() {
    return UtilRequest.replaceParamByRequestValue(fileName, pageContext.getRequest(), pageContext.getSession(), "");
  }

  public String getPathReplaceParamByRequestValue() {
    return UtilRequest.replaceParamByRequestValue(path, pageContext.getRequest(), pageContext.getSession(), "");
  }

  public String getStepReplaceParamByRequestValue() {
    return UtilRequest.replaceParamByRequestValue(step, pageContext.getRequest(), pageContext.getSession(), "");
  }
}
