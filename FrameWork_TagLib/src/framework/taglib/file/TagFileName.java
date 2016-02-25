package framework.taglib.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import framework.ressource.util.UtilRequest;
import framework.ressource.util.UtilString;

/**
 * @author  HP_Administrateur
 */
public class TagFileName extends BodyTagSupport {

  private static final long serialVersionUID = -2931677977917151297L;

  private String path;

  public TagFileName() {
  }

  public int doStartTag() {
	JspWriter out = pageContext.getOut();
	ServletRequest request = pageContext.getRequest();
	HttpSession session = pageContext.getSession();
	try {
		String aPath = UtilRequest.replaceParamByRequestValue(getPath(), request, session, "");
		if (UtilString.isNotEmpty(aPath)) {
			String name = new File(aPath).getName();
	    	out.println(name);
		}
	} catch (FileNotFoundException ex) {
		ex.printStackTrace();
	} catch (IOException ex) {
		ex.printStackTrace();
	} catch (Exception ex) {
		ex.printStackTrace();
	}
    return SKIP_BODY;
  }

	/**
	 * @return
	 * @uml.property  name="path"
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param  string
	 * @uml.property  name="path"
	 */
	public void setPath(String string) {
		path = string;
	}
}
