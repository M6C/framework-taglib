package framework.taglib.xml;

import framework.ressource.util.UtilEncoder;
import framework.ressource.util.UtilReflect;
import framework.ressource.util.UtilRequest;
import framework.ressource.util.UtilString;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URLEncoder;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.w3c.dom.Document;

/**
 * @author  HP_Administrateur
 */
public class TagXmlPrinter extends TagSupport {
  private String writerClass = null;
  private String name = null;
  private String scope = null;
  private String encoding = null;

  public TagXmlPrinter() {
  }

  public int doStartTag() {
	JspWriter out = pageContext.getOut();
	try {
		ServletRequest request = pageContext.getRequest();
		HttpSession session = pageContext.getSession();
		if (UtilString.isEmpty(getWriterClass()))
		  writerClass = "javax.servlet.jsp.JspWriter";
		Writer writer = null;
		String szWriterClass = UtilRequest.replaceParamByRequestValue(getWriterClass(), request, session, "");
		if ("javax.servlet.jsp.JspWriter".equals(szWriterClass))
		  writer = out;
		else
		  writer = (Writer)UtilReflect.newInstance(szWriterClass);
		if (writer!=null) {
			Document doc = null;
			if (UtilString.isNotEmpty(getName())) {
				String szName = UtilRequest.replaceParamByRequestValue(getName(), request, session, "");
				String szScope = UtilRequest.replaceParamByRequestValue(getScope(), request, session, "");
				String szEncoding = UtilRequest.replaceParamByRequestValue(getEncoding(), request, session, "");
				if (UtilString.isEqualsIgnoreCase("session", szScope))
					doc = (Document)session.getAttribute(szName);
				else
					doc = (Document)request.getAttribute(szName);
				if (doc!=null) {
					//TransformerFactory tFactory = TransformerFactory.newInstance();
					javax.xml.transform.TransformerFactory tFactory = TransformerFactory.newInstance(
						"org.apache.xalan.processor.TransformerFactoryImpl",
						Thread.currentThread().getContextClassLoader()); 
					// Generate the transformer.
					Transformer transformer = tFactory.newTransformer();				
					StringWriter strWriter = new StringWriter();
					Source xmlSource = new DOMSource(doc);
					// Perform the transformation, sending the output to the response.
					transformer.transform(xmlSource, new StreamResult(strWriter));
					String str = strWriter.toString();
					if(UtilString.isNotEmpty(szEncoding)) {
						if ("HTML".equalsIgnoreCase(szEncoding))
							str = UtilEncoder.encodeHTML(str);
						else if ("HTMLSpecialChars".equalsIgnoreCase(szEncoding))
							str = UtilEncoder.encodeHTMLSpecialChars(str);
						else if ("HTMLEntities".equalsIgnoreCase(szEncoding))
							str = UtilEncoder.encodeHTMLEntities(str);
						else
							str = URLEncoder.encode(str, szEncoding);
					}
					// Write the Xml result
					writer.write(str);
				}
			}
		}
    }
    catch(Exception ex) {
      ex.printStackTrace(new PrintWriter(out));
    }
    return EVAL_PAGE;
  }

  /**
 * @param writerClass  the writerClass to set
 * @uml.property  name="writerClass"
 */
public void setWriterClass(String writerClass) {
    this.writerClass = writerClass;
  }

  /**
 * @return  the writerClass
 * @uml.property  name="writerClass"
 */
public String getWriterClass() {
    return writerClass;
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

/**
 * @return  the encoding
 * @uml.property  name="encoding"
 */
public String getEncoding() {
	return encoding;
}

/**
 * @param encoding  the encoding to set
 * @uml.property  name="encoding"
 */
public void setEncoding(String encoding) {
	this.encoding = encoding;
}
}
