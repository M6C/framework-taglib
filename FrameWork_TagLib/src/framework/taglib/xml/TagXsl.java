package framework.taglib.xml;

import framework.ressource.util.UtilReflect;
import framework.ressource.util.UtilRequest;
import framework.ressource.util.UtilString;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.URIResolver;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

/**
 * @author  HP_Administrateur
 */
public class TagXsl extends TagSupport {

  /**
   * 
   */
  private static final long serialVersionUID = -4238582692327103376L;
  
  private String xml;
  private String xsl;
  
  private String xmlName;
  private String xmlScope;

  private Transformer transformer = null;
  // Parametres initialise par un sous-taglib pour le stockage
  // du document Xml dans la requette de l'utilsateur
  private TagXslResult tagXslResult;

  public TagXsl() {
  }

  public int doStartTag() {
    JspWriter out = pageContext.getOut();
    ServletRequest request = pageContext.getRequest();
    HttpSession session = pageContext.getSession();
    ServletContext context = session.getServletContext();
    if (UtilString.isNotEmpty(getXsl())) {
      try {
		//TransformerFactory tFactory = TransformerFactory.newInstance();
		javax.xml.transform.TransformerFactory tFactory = TransformerFactory.newInstance(
			"org.apache.xalan.processor.TransformerFactoryImpl",
			Thread.currentThread().getContextClassLoader()); 

        final ServletContext ctx = context;
        URIResolver uriResolver = new URIResolver() {
          public Source resolve(String href, String base) {
            Source src = new StreamSource(ctx.getResourceAsStream(href));
            return src;
          }
        };
        tFactory.setURIResolver(uriResolver);
        String szXsl = UtilRequest.replaceParamByRequestValue(getXsl(), request, session, "");
        if (UtilString.isNotEmpty(szXsl)) {
          Source xslSource = null;
          try {
            xslSource = new StreamSource(new java.net.URL("file", "", szXsl).openStream());
          }
          catch (Exception ex) {
            xslSource = new StreamSource(context.getResourceAsStream(szXsl));
          }
          
          // Generate the transformer.
          transformer = tFactory.newTransformer(xslSource);
        }
        else
          // Generate the transformer.
          transformer = tFactory.newTransformer();
      }
      // If an Exception occurs, write the error to the page.
      catch (Exception ex) {
        ex.printStackTrace(new PrintWriter(out));
      }
    }
    return EVAL_PAGE;
  }

  public int doEndTag() {
    JspWriter out = pageContext.getOut();
    ServletRequest request = pageContext.getRequest();
    HttpSession session = pageContext.getSession();
    ServletContext context = session.getServletContext();
    try {
	    String szXml = UtilRequest.replaceParamByRequestValue(getXml(), request, session, "");
	    String szXmlName = UtilRequest.replaceParamByRequestValue(getXmlName(), request, session, "");
	    String szXmlScope = UtilRequest.replaceParamByRequestValue(getXmlScope(), request, session, "");
      if (getTransformer()!=null) {
    	  Source xmlSource = null;
    	  if (UtilString.isNotEmpty(szXml)) {
	        // Get the XML input document and the stylesheet, both in the servlet
	        // engine document directory.
              try {
      	        xmlSource = new StreamSource(new java.net.URL("file", "", szXml).openStream());
              }
              catch (Exception ex) {
            	xmlSource = new StreamSource(context.getResourceAsStream(szXml));
              }
    	  }
    	  else if (UtilString.isNotEmpty(szXmlName)) {
          Document doc = null;
          if (UtilString.isEqualsIgnoreCase("session", szXmlScope))
            doc = (Document)session.getAttribute(szXmlName);
          else
            doc = (Document)request.getAttribute(szXmlName);
          if (doc!=null)
            xmlSource = new DOMSource(doc);
        }
        if (xmlSource!=null) {
	        StringWriter strWriter = new StringWriter();
	        // Perform the transformation, sending the output to the response.
	        transformer.transform(xmlSource, new StreamResult(strWriter));
	        // Get the Xml result
	        String strResult = strWriter.toString();
	        // Check if the result is not empty
	        if (UtilString.isNotEmpty(strResult)) {
            if (getTagXslResult()!=null) {
              getTagXslResult().processResult(strResult);
            }
	        }
	      }
      }
    } catch(Exception ex) {
      ex.printStackTrace(new PrintWriter(out));
    }
    return EVAL_PAGE;
  }

  /**
 * @return  the xml
 * @uml.property  name="xml"
 */
public String getXml(){
    return xml;
  }
  /**
 * @param xml  the xml to set
 * @uml.property  name="xml"
 */
public void setXml(String xml){
    this.xml = xml;
  }
  /**
 * @return  the xsl
 * @uml.property  name="xsl"
 */
public String getXsl(){
    return xsl;
  }
  /**
 * @param xsl  the xsl to set
 * @uml.property  name="xsl"
 */
public void setXsl(String xsl){
    this.xsl = xsl;
  }
  /**
 * @return  the transformer
 * @uml.property  name="transformer"
 */
public Transformer getTransformer() {
    return transformer;
  }
  /**
 * @param transformer  the transformer to set
 * @uml.property  name="transformer"
 */
public void setTransformer(Transformer transformer){
    this.transformer = transformer;
  }
  /**
 * @return  the xmlName
 * @uml.property  name="xmlName"
 */
public String getXmlName() {
  	return xmlName;
  }
  
  /**
 * @param xmlName  the xmlName to set
 * @uml.property  name="xmlName"
 */
public void setXmlName(String xmlName) {
  	this.xmlName = xmlName;
  }
  
  /**
 * @return  the xmlScope
 * @uml.property  name="xmlScope"
 */
public String getXmlScope() {
  	return xmlScope;
  }
  
  /**
 * @param xmlScope  the xmlScope to set
 * @uml.property  name="xmlScope"
 */
public void setXmlScope(String xmlScope) {
  	this.xmlScope = xmlScope;
  }
  
  /**
 * @return  the tagXslResult
 * @uml.property  name="tagXslResult"
 */
public TagXslResult getTagXslResult() {
    return tagXslResult;
  }
  
  /**
 * @param tagXslResult  the tagXslResult to set
 * @uml.property  name="tagXslResult"
 */
public void setTagXslResult(
      TagXslResult tagXslResult) {
    this.tagXslResult = tagXslResult;
  }
}
