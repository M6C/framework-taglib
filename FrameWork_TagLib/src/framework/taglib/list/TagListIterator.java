package framework.taglib.list;

import framework.beandata.BeanFindList;
import framework.ressource.util.UtilReflect;
import framework.ressource.util.UtilString;
import framework.trace.Trace;
import java.io.IOException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * @author  HP_Administrateur
 */
public class TagListIterator extends BodyTagSupport {

  private String indexStart = null;
  private String indexEnd = null;
  private String indexStep = null;
  private String bean=null;
  private String beanScope=null;

  public TagListIterator() {
  }

  public int doStartTag() {
    ServletRequest request = pageContext.getRequest();
    HttpSession session = pageContext.getSession();
    Boolean hasMoreElement = Boolean.FALSE;
    if (UtilString.isNotEmpty(getBean())) {
      Object bean = ("session".equalsIgnoreCase(getBeanScope())) ? session.getAttribute(getBean()) : request.getAttribute(getBean());
      if ( (bean!=null)&&(bean instanceof BeanFindList) ) {
        String szIndex = request.getParameter(getBean());
        if (UtilString.isNotEmpty(szIndex)) {
          //Initialisation de l'index du bean
          UtilReflect.safeInvokeMethod(bean, "setIndex", new Class[] {Integer.class}, new Object[] {new Integer(szIndex)});
          // Modifi l'index de fin du tag
          setIndexEnd(String.valueOf(Integer.parseInt(getIndexEnd()) + Integer.parseInt(szIndex)));
        }
        else
          //Initialisation de l'index du bean
          UtilReflect.safeInvokeMethod(bean, "setIndex", new Class[] {Integer.class}, new Object[] {new Integer(getIndexStart())});
        //Test si le bean contient d'autres elements
        hasMoreElement = (Boolean)UtilReflect.safeInvokeMethod(bean, "hasMoreElements", null, null);
        hasMoreElement = (hasMoreElement==null) ? Boolean.FALSE : Boolean.valueOf((hasMoreElement.booleanValue())&&((getIndexEnd()==null)||(Integer.parseInt(getIndexEnd())>Integer.parseInt(getIndexStart()))));
      }
    }
    return ((hasMoreElement.booleanValue()) ? EVAL_BODY_BUFFERED : SKIP_BODY);
  }


  public int doAfterBody() {
    BodyContent bc = getBodyContent();
    ServletRequest request = pageContext.getRequest();
    HttpSession session = pageContext.getSession();
    if (bc != null) {
      try {
        JspWriter out = bc.getEnclosingWriter();
        out.println(bc.getString());
        bc.clearBody();
        Boolean hasMoreElement = Boolean.FALSE;
        if (UtilString.isNotEmpty(getBean())) {
          Object bean = ("session".equalsIgnoreCase(getBeanScope())) ? session.getAttribute(getBean()) : request.getAttribute(getBean());
          if(bean!=null) {
            Integer index = (Integer) UtilReflect.safeInvokeMethod(bean, "getIndex", null, null);
            if ((index != null)&&(bean instanceof BeanFindList)) {
              // Recupere la valeur de l'incrementation
              int i = (getIndexStep()==null) ? 1 : Integer.parseInt(getIndexStep());
              // Incrementation de l'index
              index = new Integer(index.intValue()+i);
              // Modification de l'index du bean
              UtilReflect.safeInvokeMethod(bean, "setIndex", new Class[]{Integer.class}, new Object[]{index});
              //Test si le bean contient d'autres elements
              hasMoreElement = (Boolean) UtilReflect.safeInvokeMethod(bean, "hasMoreElements", null, null);
              hasMoreElement = (hasMoreElement==null) ? Boolean.FALSE : Boolean.valueOf((hasMoreElement.booleanValue())&&((getIndexEnd()==null)||(Integer.parseInt(getIndexEnd())>index.intValue())));
            }
          }
          if (hasMoreElement.booleanValue())
            return EVAL_BODY_BUFFERED;
          else
            return SKIP_BODY;
        }
      }
      catch (IOException ioe) {
        Trace.ERROR("Error in BodingTag: ", ioe);
      }
    }
    return SKIP_BODY;
  }

  /**
 * @param bean  the bean to set
 * @uml.property  name="bean"
 */
public void setBean(String bean) {
    this.bean = bean;
  }

  /**
 * @param beanScope  the beanScope to set
 * @uml.property  name="beanScope"
 */
public void setBeanScope(String beanScope) {
    this.beanScope = beanScope;
  }

  /**
 * @param indexStart  the indexStart to set
 * @uml.property  name="indexStart"
 */
public void setIndexStart(String indexStart) {
    this.indexStart = indexStart;
  }

  /**
 * @param indexStep  the indexStep to set
 * @uml.property  name="indexStep"
 */
public void setIndexStep(String indexStep) {
    this.indexStep = indexStep;
  }

  /**
 * @param indexEnd  the indexEnd to set
 * @uml.property  name="indexEnd"
 */
public void setIndexEnd(String indexEnd) {
    this.indexEnd = indexEnd;
  }

  /**
 * @return  the bean
 * @uml.property  name="bean"
 */
public String getBean() {
    return bean;
  }

  /**
 * @return  the beanScope
 * @uml.property  name="beanScope"
 */
public String getBeanScope() {
    return beanScope;
  }

  /**
 * @return  the indexStart
 * @uml.property  name="indexStart"
 */
public String getIndexStart() {
    return (indexStart==null) ? "0" : indexStart;
  }

  /**
 * @return  the indexStep
 * @uml.property  name="indexStep"
 */
public String getIndexStep() {
    return indexStep;
  }

  /**
 * @return  the indexEnd
 * @uml.property  name="indexEnd"
 */
public String getIndexEnd() {
    return indexEnd;
  }
}
