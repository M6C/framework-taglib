package framework.taglib.ancestor;

import framework.ressource.util.UtilEvalJava;
import framework.ressource.util.UtilString;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * <p>Title: </p> <p>Description: </p> <p>Copyright: Copyright (c) 2002</p> <p>Company: </p>
 * @author  not attributable
 * @version  1.0
 */

public class AncestorBodyTagSupport extends BodyTagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String show;

  public AncestorBodyTagSupport() {
  }

  /**
 * @param show  the show to set
 * @uml.property  name="show"
 */
public void setShow(String show) {
    this.show = show;
  }

  /**
 * @return  the show
 * @uml.property  name="show"
 */
public String getShow() {
    return show;
  }

  public boolean isShow() {
    boolean ret = true;
    if(UtilString.isNotEmpty(getShow())) {
      try {
        ret = UtilEvalJava.resolveBooleanExpression(getShow());
      }
      catch (Exception ex) {
        ret = true;
      }
    }
    return ret;
  }
}
