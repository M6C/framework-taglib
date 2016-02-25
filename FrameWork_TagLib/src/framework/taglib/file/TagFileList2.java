package framework.taglib.file;

import framework.ressource.ftp.FTPClient;
import framework.ressource.ftp.FTPException;
import framework.ressource.util.UtilFile;
import framework.ressource.util.UtilRequest;
import framework.ressource.util.UtilSort;
import framework.ressource.util.UtilString;
import framework.ressource.util.UtilVector;
import framework.taglib.file.bean.BeanFile;
import framework.trace.Trace;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.LinkedList;
import java.util.Vector;
import java.util.regex.Pattern;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * @author  HP_Administrateur
 */
public class TagFileList2 extends BodyTagSupport {

  private Vector pathList = null;
  private int index = 0;
  private int iEnd = 0;

  private String path = null;
  private String pathToExpand = null;
  private String indexStart = null;
  private String indexEnd = null;
  private String indexStep = null;
  private String sortMethod = null;
  private String filter = null;

  // Portee de la liste de fichier soit en 'request' soit en 'session'. En 'request' par defaut si vide.
  private String scope = null;
  // Nom de stockage de la liste de fichier en 'request' ou 'session' en fonction du scope.
  private String name = null;

  public TagFileList2() {
  }

  public int doStartTag() {
    String szIndexStart = getIndexStartReplaceParamByRequestValue();
    String szEnd = getIndexEndReplaceParamByRequestValue();
    String szPath = getPathReplaceParamByRequestValue();
    String szPathToExpand = getPathToExpandReplaceParamByRequestValue();
    index = UtilString.isNotEmpty(szIndexStart) ? Integer.parseInt(szIndexStart) : 0;
    if (UtilString.isNotEmpty(szPath)) {
	    pathList = new Vector();
    	if (szPath.toUpperCase().startsWith("FTP://")) {
    		String login = null;
    		String password = null;
    		String host = null;
    		String port = null;
    		String path = null;
    		// Recupere les paramètres d'une chaine au format 'ftp://monlogin:monpassword@monserveurftp'
    		int indexFrom = 6;
    		int indexTo = szPath.indexOf('@', indexFrom);
    		if (indexTo>0) {
	    		// Login
	    		login = szPath.substring(indexFrom, indexTo);
	    		int indexPwd = login.indexOf(':', indexFrom);
	    		if (indexPwd>0) {
	    			// Password
		    		password = login.substring(indexPwd+1);
		    		// Login
	    			login = login.substring(0, indexPwd);
	    		}
    		}
    		indexFrom = indexTo+1;
    		// Host
    		host = szPath.substring(indexFrom);
    		// Port
    		int indexPort = host.indexOf(':');
    		if (indexPort>0) {
    			port = szPath.substring(indexPort+1);
    			host = host.substring(0, indexPort);
        		// Path
        		int indexPath = port.indexOf(':')+1;
        		if (indexPath>0) {
    	    		path = port.substring(indexPath+1);
        			port = port.substring(0, indexPath);
        		}
    		}
    		else {
        		port = "21";
        		// Path
        		int indexPath = host.indexOf(':')+1;
        		if (indexPath>0) {
    	    		path = host.substring(indexPath+1);
        			port = host.substring(0, indexPath);
        		}
    		}
    		FTPClient ftpc = null;
    		try {
				ftpc = new FTPClient(host, Integer.parseInt(port));
				ftpc.login(login, password);
				if (UtilString.isEmpty(path))
					path = "/";
				ftpc.chdir(path);
				File filePath = new File(path);
				String[] listFilename = ftpc.dir(null, true);
				if (listFilename != null) {
					int size = listFilename.length;
					File[] listFile = new File[size];
					for(int i=0 ; i<size ; i++) {
						listFile[i] = new File(listFilename[i]);
					}
//					pathList.copyInto(listFile);
			        int iBeanIndex = 0;
			        copyListIntoVectorAt(filePath, listFile, pathList, 0, iBeanIndex++);
			        File fileItem = null;
			        for (int i = 0; i < pathList.size(); i++) {
/*
			          fileItem = ((BeanFile)pathList.elementAt(i)).getFile();
*/
			          File filePathToExpand = new File(szPathToExpand);
			          if (fileItem.isDirectory()) {
			            if (filePathToExpand.getAbsolutePath().startsWith(fileItem.getAbsolutePath()))
			            	iBeanIndex++;
			              //copyListIntoVectorAt(filePath, fileItem.listFiles(fileFilter), pathList, i+1, iBeanIndex++);
			          }
			        }
				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FTPException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (ftpc!=null)
					try {
						ftpc.quit();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (FTPException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
    	}
    	else {
	      File filePath = new File(szPath);
	      File filePathToExpand = null;
	      FileFilter fileFilter = null;
	      final String szFilter = getFilterReplaceParamByRequestValue();
	      if (UtilString.isNotEmpty(szFilter)) {
	        fileFilter = new FileFilter() {
	            public boolean accept(File pathname) {
	                //return pathname.getName().matches(szFilter);
	                //return Pattern.compile(szFilter).matcher(pathname.getName()).matches();
	                //return Pattern.matches(szFilter, pathname.getName());
	                return Pattern.compile(szFilter, Pattern.CASE_INSENSITIVE).matcher(pathname.getName()).find();
	            }
	        };
	      }
	      if (UtilString.isNotEmpty(szPathToExpand)) {
	        boolean relative = szPathToExpand.indexOf(":"+File.separator)<0;
	        filePathToExpand = relative ? new File(filePath, szPathToExpand) : new File(szPathToExpand);
	      }
	      filePathToExpand = ((filePathToExpand!=null)&&(filePathToExpand.exists())) ? filePathToExpand : null;
	      if (filePath.exists() && filePath.isDirectory()) {
	        int iBeanIndex = 0;
	        copyListIntoVectorAt(filePath, filePath.listFiles(fileFilter), pathList, 0, iBeanIndex++);
	        File fileItem = null;
	        for (int i = 0; i < pathList.size(); i++) {
/*
	          fileItem = ((BeanFile)pathList.elementAt(i)).getFile();
*/
	          if ((filePathToExpand!=null)&&(fileItem.isDirectory())) {
	            if (filePathToExpand.getAbsolutePath().startsWith(fileItem.getAbsolutePath()))
	              copyListIntoVectorAt(filePath, fileItem.listFiles(fileFilter), pathList, i+1, iBeanIndex++);
	          }
	        }
	        if (UtilString.isNotEmpty(szEnd)){
	          iEnd = index + Integer.parseInt(szEnd);
	          iEnd = (iEnd > pathList.size()) ? pathList.size() : iEnd;
	        }
	       else
	         iEnd = pathList.size();
	      }
    	}
    }
    else {
      pathList = null;
      index = 0;
      iEnd = 0;
    }
    if (UtilString.isNotEmpty(getName())){
      if (UtilString.isEqualsIgnoreCase(getScope(), "session"))
        pageContext.getSession().setAttribute(getName(), pathList);
      else
        pageContext.getRequest().setAttribute(getName(), pathList);
    }

    return (((pathList!=null)&&(iEnd>index)) ? EVAL_BODY_BUFFERED : SKIP_BODY);
  }

  public int doAfterBody() {
    BodyContent bc = getBodyContent();
    String szIndexStep = getIndexStepReplaceParamByRequestValue();
    index+=UtilString.isEmpty(szIndexStep) ? 1 : Integer.parseInt(szIndexStep);
    if (bc != null) {
      try {
        JspWriter out = bc.getEnclosingWriter();
        out.println(bc.getString());
        bc.clearBody();
      }
      catch (IOException ioe) {
        Trace.ERROR("Error in BodingTag: ", ioe);
      }
    }
    return (((pathList!=null)&&(iEnd>index)&&(pathList.size()>index)) ? EVAL_BODY_BUFFERED : SKIP_BODY);
  }

  public int doEndTag() {
    return EVAL_PAGE;
  }

  private void copyListIntoVectorAt(File rootFile, Object[] list, Vector vec, int at, int beanIndex) {
    String methodName = getSortMethod();
    if (UtilString.isNotEmpty(methodName)) {
      try {
      list = UtilSort.sortIncrease(list, methodName);
      } catch(Exception ex) {
        try {
          java.io.StringWriter strW =  new java.io.StringWriter();
          java.io.FileWriter fileW =  new java.io.FileWriter(new java.io.File("a.txt"));
          ex.printStackTrace(new java.io.PrintWriter(strW));
          fileW.write(strW.toString());
          fileW.flush();
          fileW.close();
        } catch(Exception ex1) {}
      }
    }
/*
    for( int i=0 ; i<list.length ; i++ ) {
        vec.add(at++, new BeanFile(beanIndex, i, (File)list[i], rootFile));
    }
*/
  }

  public BeanFile getCurrentBean() {
    return (BeanFile)UtilVector.safeGetElementAt(pathList, index);
  }

  public File getCurrentFile() {
    File ret = null;
    BeanFile beanFile = (BeanFile)UtilVector.safeGetElementAt(pathList, index);
/*
    if (beanFile!=null)
      ret = beanFile.getFile();
*/
    return ret;
  }

public int getCurrentFileIndex() {
  int ret = -1;
  BeanFile beanFile = (BeanFile)UtilVector.safeGetElementAt(pathList, index);
  if (beanFile!=null)
    ret = beanFile.getIndex();
  return ret;
}
  /**
 * @return  the pathToExpand
 * @uml.property  name="pathToExpand"
 */
public String getPathToExpand() {
    return UtilRequest.replaceParamByRequestValue(pathToExpand, pageContext.getRequest(), pageContext.getSession(), "");
  }

  /**
 * @return  the path
 * @uml.property  name="path"
 */
public String getPath() {
    return UtilRequest.replaceParamByRequestValue(path, pageContext.getRequest(), pageContext.getSession(), "");
  }

  /**
 * @return  the pathList
 * @uml.property  name="pathList"
 */
public Vector getPathList() {
    return pathList;
  }

  /**
 * @param path  the path to set
 * @uml.property  name="path"
 */
public void setPath(String path) {
    this.path = path;
  }

  /**
 * @return  the indexStep
 * @uml.property  name="indexStep"
 */
public String getIndexStep() {
    return UtilRequest.replaceParamByRequestValue(indexStep, pageContext.getRequest(), pageContext.getSession(), "");
  }

  /**
 * @return  the indexStart
 * @uml.property  name="indexStart"
 */
public String getIndexStart() {
    return UtilRequest.replaceParamByRequestValue(indexStart, pageContext.getRequest(), pageContext.getSession(), "");
  }

  /**
 * @return  the indexEnd
 * @uml.property  name="indexEnd"
 */
public String getIndexEnd() {
    return UtilRequest.replaceParamByRequestValue(indexEnd, pageContext.getRequest(), pageContext.getSession(), "");
  }

  /**
 * @return  the name
 * @uml.property  name="name"
 */
public String getName() {
    return UtilRequest.replaceParamByRequestValue(name, pageContext.getRequest(), pageContext.getSession(), "");
  }

  /**
 * @return  the scope
 * @uml.property  name="scope"
 */
public String getScope() {
    return UtilRequest.replaceParamByRequestValue(scope, pageContext.getRequest(), pageContext.getSession(), "");
  }

  /**
 * @return  the sortMethod
 * @uml.property  name="sortMethod"
 */
public String getSortMethod(){
    return UtilRequest.replaceParamByRequestValue(sortMethod, pageContext.getRequest(), pageContext.getSession(), "");
  }

  /**
 * @return  the filter
 * @uml.property  name="filter"
 */
public String getFilter() {
        return filter;
  }


  public String getPathReplaceParamByRequestValue() {
    return UtilRequest.replaceParamByRequestValue(path, pageContext.getRequest(), pageContext.getSession(), "");
  }

  public String getIndexStartReplaceParamByRequestValue() {
    return UtilRequest.replaceParamByRequestValue(indexStart, pageContext.getRequest(), pageContext.getSession(), "");
  }

  public String getIndexEndReplaceParamByRequestValue() {
    return UtilRequest.replaceParamByRequestValue(indexEnd, pageContext.getRequest(), pageContext.getSession(), "");
  }

  public String getIndexStepReplaceParamByRequestValue() {
    return UtilRequest.replaceParamByRequestValue(indexStep, pageContext.getRequest(), pageContext.getSession(), "");
  }

  /**
 * @param pathToExpand  the pathToExpand to set
 * @uml.property  name="pathToExpand"
 */
public void setPathToExpand(String pathToExpand) {
    this.pathToExpand = pathToExpand;
  }

  public String getPathToExpandReplaceParamByRequestValue() {
    return UtilRequest.replaceParamByRequestValue(pathToExpand, pageContext.getRequest(), pageContext.getSession(), "");
  }

  public String getFilterReplaceParamByRequestValue() {
    return UtilRequest.replaceParamByRequestValue(filter, pageContext.getRequest(), pageContext.getSession(), "");
  }

  /**
 * @param indexStep  the indexStep to set
 * @uml.property  name="indexStep"
 */
public void setIndexStep(String indexStep) {
        this.indexStep = indexStep;
      }

  /**
 * @param filter  the filter to set
 * @uml.property  name="filter"
 */
public void setFilter(String filter) {
        this.filter = filter;
      }

  /**
 * @param indexStart  the indexStart to set
 * @uml.property  name="indexStart"
 */
public void setIndexStart(String indexStart) {
    this.indexStart = indexStart;
  }

  /**
 * @param indexEnd  the indexEnd to set
 * @uml.property  name="indexEnd"
 */
public void setIndexEnd(String indexEnd) {
    this.indexEnd = indexEnd;
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
 * @param sortMethod  the sortMethod to set
 * @uml.property  name="sortMethod"
 */
public void setSortMethod(String sortMethod) {
    this.sortMethod = sortMethod;
  }
}