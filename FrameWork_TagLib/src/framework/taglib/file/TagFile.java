package framework.taglib.file;

import framework.ressource.util.UtilRequest;
import framework.ressource.util.UtilString;
import framework.taglib.file.bean.BeanFTP;
import framework.taglib.file.bean.BeanFTPAddress;
import framework.taglib.file.bean.BeanFile;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * @author  HP_Administrateur
 */
public class TagFile extends TagSupport {

  private String path;
  private String scope;
  private String name;

  public TagFile() {
  }

  public int doStartTag() {
    JspWriter out = pageContext.getOut();
	ServletRequest request = pageContext.getRequest();
	HttpSession session = pageContext.getSession();
	String aPath = UtilRequest.replaceParamByRequestValue(getPath(), request, session, "");
	if (UtilString.isNotEmpty(aPath) && UtilString.isNotEmpty(getName())) {
		BeanFile file = null;
    	if (aPath.toUpperCase().startsWith("FTP://")) {
    		BeanFTPAddress address = new BeanFTPAddress(aPath);
    		file = new BeanFTP(address, address.getPath());
    		address.setPath("/");
    	}
    	else {
			file = new BeanFile(aPath);
    	}
	    if ((file != null) && (file.exists()) && (file.isFile())) {
	        if ("session".equalsIgnoreCase(getScope()))
	          pageContext.getSession().setAttribute(getName(), file);
	        else
	          pageContext.getRequest().setAttribute(getName(), file);
	    }
	}
    return EVAL_PAGE;
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
