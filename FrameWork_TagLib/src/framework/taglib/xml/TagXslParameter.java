package framework.taglib.xml;

import framework.ressource.util.UtilRequest;
import framework.ressource.util.UtilString;
import java.io.PrintWriter;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;
import javax.xml.transform.Transformer;

/**
 * @author  HP_Administrateur
 */
public class TagXslParameter extends TagSupport {
  private String name;
  private String value;


  public TagXslParameter() {
  }

  public int doStartTag() {
    JspWriter out = pageContext.getOut();
    try {
      ServletRequest request = pageContext.getRequest();
      HttpSession session = pageContext.getSession();
      TagXsl tagParent = null;
      Tag parent = getParent();
      while (parent != null) {
        if (parent instanceof TagXsl) {
          tagParent = (TagXsl) parent;
          break;
        }
        parent = parent.getParent();
      }
      if (tagParent != null) {
        if (UtilString.isNotEmpty(getName()) && UtilString.isNotEmpty(getValue())) {
          Transformer transformer = tagParent.getTransformer();
          if (transformer != null) {
            String szName = UtilRequest.replaceParamByRequestValue(getName(), request, session, "");
            String szValue = UtilRequest.replaceParamByRequestValue(getValue(), request, session, "");
            transformer.setParameter(szName, szValue);
          }
        }
      }
    }
    catch (Exception ex) {
      ex.printStackTrace(new PrintWriter(out));
    }
    return EVAL_PAGE;
  }

  /**
 * @return  the name
 * @uml.property  name="name"
 */
public String getName(){
    return name;
  }

  /**
 * @return  the value
 * @uml.property  name="value"
 */
public String getValue() {
    return value;
  }

  /**
 * @param name  the name to set
 * @uml.property  name="name"
 */
public void setName(String name){
    this.name = name;
  }

  /**
 * @param value  the value to set
 * @uml.property  name="value"
 */
public void setValue(String value) {
    this.value = value;
  }
}
