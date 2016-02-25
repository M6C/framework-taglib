package framework.taglib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.tagext.BodyTagSupport;

import framework.beandata.BeanGenerique;
import framework.ressource.FrmWrkServlet;
import framework.ressource.bean.BeanData;
import framework.ressource.util.UtilAction;
import framework.ressource.util.UtilString;

/**
 * @author  HP_Administrateur
 */
public class TagLoadBean extends BodyTagSupport {
  private String name;
  private String scope;

  public TagLoadBean() {
  }

  public int doStartTag() {
	if (UtilString.isNotEmpty(getName())) {
		BeanData beanData = FrmWrkServlet.getBean(getName());
		if (beanData!=null) {
			try {
				BeanGenerique bean = UtilAction.newBean(beanData);
				if (bean!=null) {
		            bean.setBeanData(beanData);
					UtilAction.executeService(pageContext.getRequest(), pageContext.getResponse(), beanData, bean);
					if ("session".equalsIgnoreCase(getScope()))
				    	pageContext.getSession().setAttribute(getName(), bean);
				    else
				    	pageContext.getRequest().setAttribute(getName(), bean);
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
    return SKIP_BODY;
  }

  public int doEndTag() {
    return EVAL_PAGE;
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
