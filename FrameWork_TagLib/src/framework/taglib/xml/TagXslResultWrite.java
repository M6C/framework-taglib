package framework.taglib.xml;

import framework.ressource.util.UtilReflect;
import framework.ressource.util.UtilRequest;
import framework.ressource.util.UtilString;
import java.io.PrintWriter;
import java.io.Writer;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * @author  HP_Administrateur
 */
public class TagXslResultWrite extends TagXslResult {

  private static final long serialVersionUID = -5456321610863694761L;

  private String writerClass = null;

  public TagXslResultWrite() {
  }

  public void processResult(String result) throws Exception {
    JspWriter out = pageContext.getOut();
    ServletRequest request = pageContext.getRequest();
    HttpSession session = pageContext.getSession();
    Writer writer = null;
    String szWriterClass = UtilRequest.replaceParamByRequestValue(getWriterClass(), request, session, "");
    if (UtilString.isEmpty(szWriterClass))
      writer = out;
    else
      writer = (Writer)UtilReflect.newInstance(szWriterClass);
    if (writer!=null)
      writer.write(result);
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
}
