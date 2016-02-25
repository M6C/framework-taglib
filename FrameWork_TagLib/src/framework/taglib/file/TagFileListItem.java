package framework.taglib.file;

import framework.ressource.util.UtilReflect;
import framework.ressource.util.UtilString;
import framework.trace.Trace;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * @author  HP_Administrateur
 */
public class TagFileListItem extends TagSupport {
  private String methode;
  private String methodeArgument;
  private String scope;
  private String name;

  public TagFileListItem() {
  }

  public int doStartTag() {
    try {
		// TRACE
		//Trace.DEBUG("TagFileListItem getMethode:"+getMethode());
		// TRACE
		//Trace.DEBUG("TagFileListItem getMethodeArgument:"+getMethodeArgument());
	    JspWriter out = pageContext.getOut();
	    TagFileList tagParent = null;
	    Tag parent = getParent();
	    while (parent!=null) {
	      if (parent instanceof TagFileList) {
	        tagParent = (TagFileList)parent;
	      }
	      parent = parent.getParent();
	    }
	    if (tagParent != null) {
	      if (UtilString.isNotEmpty(getMethode())) {
	          File file = tagParent.getCurrentFile();
	    	  // TRACE
	    	  //Trace.DEBUG("TagFileListItem tagParent.getCurrentFile:"+tagParent.getCurrentFile());
	          Class[] type = null;
	          Object[] value = null;
	          if (UtilString.isNotEmpty(getMethodeArgument())) {
	            String[] arg = UtilString.split(getMethodeArgument(), ';');
	      	  // TRACE
	      	  //Trace.DEBUG("TagFileListItem arg:"+arg);
	            int iArg = arg.length;
	            type = new Class[iArg];
	            value = new Object[iArg];
	            for (int i = 0; i < iArg; i++) {
	              type[i] = arg[i].getClass();
	              value[i] = arg[i];
	            }
	          }
	          Object invokeMethod = UtilReflect.invokeMethod(file, getMethode(), type, value);
	      	  // TRACE
	      	  //Trace.DEBUG("TagFileListItem invokeMethod:"+invokeMethod);
	          out.print(invokeMethod);
	      }
	      if (UtilString.isNotEmpty(getName())) {
	    	  // TRACE
	    	  //Trace.DEBUG("TagFileListItem tagParent.getCurrentBean:"+tagParent.getCurrentBean());
	        if ("session".equalsIgnoreCase(getScope()))
	          pageContext.getSession().setAttribute(getName(), tagParent.getCurrentBean());
	        else
	          pageContext.getRequest().setAttribute(getName(), tagParent.getCurrentBean());
	      }
	    }
    }
	catch(Exception ex) {
		Trace.ERROR(this, ex);
    }
    return EVAL_PAGE;
  }

  /**
 * @param methode  the methode to set
 * @uml.property  name="methode"
 */
public void setMethode(String methode) {
    this.methode = methode;
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
 * @param methodeArgument  the methodeArgument to set
 * @uml.property  name="methodeArgument"
 */
public void setMethodeArgument(String methodeArgument) {
    this.methodeArgument = methodeArgument;
  }

  /**
 * @return  the methode
 * @uml.property  name="methode"
 */
public String getMethode() {
    return methode;
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
 * @return  the methodeArgument
 * @uml.property  name="methodeArgument"
 */
public String getMethodeArgument() {
    return methodeArgument;
  }
}
