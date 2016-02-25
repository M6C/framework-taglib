package framework.taglib.xml;

import framework.ressource.util.UtilRequest;
import framework.ressource.util.UtilString;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * @author  HP_Administrateur
 */
public class TagXslResultDom extends TagXslResult {

  private static final long serialVersionUID = 7464785122492092668L;

  private String name;
  private String scope;

  public TagXslResultDom() {
  }

  public void processResult(String result) throws Exception {
	if (UtilString.isNotEmpty(getName())) {
	    ServletRequest request = pageContext.getRequest();
	    HttpSession session = pageContext.getSession();
	    StringReader strReader = new StringReader(result);
	    // Check if the result can be read
	    if (strReader.ready()) {
	      if (UtilString.isNotEmpty(getName())) {
	        // Creation des outils de Parse du fichier XML
	        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
	        // Build the dom document from the result
	        Document resultDom = docBuilder.parse(new InputSource(strReader));
	        String szName = UtilRequest.replaceParamByRequestValue(getName(), request, session, "");
	        String szScope = "request";
	        if (UtilString.isNotEmpty(getScope()))
	          szScope = UtilRequest.replaceParamByRequestValue(getScope(), request, session, "");
	        if (UtilString.isEqualsIgnoreCase("session", szScope))
	          session.setAttribute(szName, resultDom);
	        else
	          request.setAttribute(szName, resultDom);
	      }
	    }
    }
  }

  /**
 * @return  the name
 * @uml.property  name="name"
 */
public String getName(){
    return name;
  }
  /**
 * @param name  the name to set
 * @uml.property  name="name"
 */
public void setName(String name){
    this.name = name;
  }
  /**
 * @return  the scope
 * @uml.property  name="scope"
 */
public String getScope(){
    return scope;
  }
  /**
 * @param scope  the scope to set
 * @uml.property  name="scope"
 */
public void setScope(String scope){
    this.scope = scope;
  }
}
