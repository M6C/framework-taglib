package framework.taglib.file;

import framework.ressource.ftp.FTPClient;
import framework.ressource.ftp.FTPException;
import framework.ressource.util.UtilFile;
import framework.ressource.util.UtilRequest;
import framework.ressource.util.UtilSort;
import framework.ressource.util.UtilString;
import framework.ressource.util.UtilVector;
import framework.taglib.file.bean.BeanFTP;
import framework.taglib.file.bean.BeanFTPAddress;
import framework.taglib.file.bean.BeanFile;
import framework.trace.Trace;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Vector;
import java.util.regex.Pattern;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * @author  HP_Administrateur
 */
public class TagFileList extends BodyTagSupport {

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
  private String withDirectory = null;

  // Portee de la liste de fichier soit en 'request' soit en 'session'. En 'request' par defaut si vide.
  private String scope = null;
  // Nom de stockage de la liste de fichier en 'request' ou 'session' en fonction du scope.
  private String name = null;

  public TagFileList() {
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
/*
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
        		int indexPath = (port.indexOf('/')>0) ? port.indexOf('/') : port.indexOf('\\');
        		if (indexPath>0) {
    	    		path = port.substring(indexPath+1);
        			port = port.substring(0, indexPath);
        		}
    		}
    		else {
        		port = "21";
        		// Path
        		int indexPath = (host.indexOf('/')>0) ? host.indexOf('/') : host.indexOf('\\');
        		if (indexPath>0) {
    	    		path = host.substring(indexPath);
    	    		host = host.substring(0, indexPath);
        		}
    		}
*/
    		BeanFTPAddress beanFTPAddress = new BeanFTPAddress(szPath);
    		FTPClient ftpc = null;
    		try {
				ftpc = new FTPClient(beanFTPAddress.getHost(), Integer.parseInt(beanFTPAddress.getPort()));
				ftpc.login(beanFTPAddress.getLogin(), beanFTPAddress.getPassword());
				ftpc.chdir(beanFTPAddress.getPath());
				File filePath = new BeanFTP(beanFTPAddress, 0, 0, path);
				String[] listFilename = ftpc.dir(null, true);
				if (listFilename != null) {
//					pathList.copyInto(listFile);
			        int iBeanIndex = 0;
			        copyListIntoVectorAt(filePath, listFilename, pathList, 0, iBeanIndex++, BeanFTP.class);
			        if (UtilString.isNotEmpty(szPathToExpand)) {
				        File fileItem = null;
				        for (int i = 0; i < pathList.size(); i++) {
				          fileItem = ((File)pathList.elementAt(i));
				          if (fileItem.isDirectory()) {
				        	  String str1 = UtilString.replaceAll(szPathToExpand, "\\", "/");
				        	  String str2 = UtilString.replaceAll(fileItem.getAbsolutePath(), "\\", "/");
				            if (str1.startsWith(str2))
				              copyListIntoVectorAt(fileItem, ftpc.dir(fileItem.getAbsolutePath(), true), pathList, i+1, iBeanIndex++, BeanFTP.class);
				          }
				        }
			        }
				}
/*
    		} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FTPException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
*/
    		} catch(Exception ex) {
    			Trace.ERROR(this, ex);
			} finally {
				if (ftpc!=null)
					try {
						ftpc.quit();
		    		} catch(Exception ex) {
		    			Trace.ERROR(this, ex);
					}
			}
    	}
    	else {
    	  BeanFile filePath = new BeanFile(0, 0, szPath);
    	  // TRACE
    	  //Trace.DEBUG("TagFileList szPath:"+szPath);
    	  File filePathToExpand = null;
	      FileFilter fileFilter = null;
	      final String szFilter = getFilterReplaceParamByRequestValue();
    	  // TRACE
	      //Trace.DEBUG("TagFileList szFilter:"+szFilter);
	      if (UtilString.isNotEmpty(szFilter)) {
    	      final String szWithDirectory = getWithDirectoryReplaceParamByRequestValue();
    	      final boolean bWithDirectory = UtilString.isEndByIgnoreCase(szWithDirectory, "true");
	    	  fileFilter = new FileFilter() {
	            public boolean accept(File pathname) {
	                //return pathname.getName().matches(szFilter);
	                //return Pattern.compile(szFilter).matcher(pathname.getName()).matches();
	                //return Pattern.matches(szFilter, pathname.getName());
	                return (pathname.isDirectory()&&bWithDirectory) || Pattern.compile(szFilter, Pattern.CASE_INSENSITIVE).matcher(pathname.getName()).find();
	            }
	        };
	      }
    	  // TRACE
	      //Trace.DEBUG("TagFileList doStartTag szPathToExpand:"+szPathToExpand);
	      if (UtilString.isNotEmpty(szPathToExpand)) {
	        boolean relative = szPathToExpand.indexOf(":"+File.separator)<0;
	        filePathToExpand = relative ? new BeanFile(0, 0, filePath, szPathToExpand) : new BeanFile(0, 0, szPathToExpand);
	      }
	      filePathToExpand = ((filePathToExpand!=null)&&(filePathToExpand.exists())) ? filePathToExpand : null;
    	  // TRACE
	      //Trace.DEBUG("TagFileList doStartTag filePathToExpand:"+filePathToExpand);
	      if (filePath.exists() && filePath.isDirectory()) {
	    	  // TRACE
		      //Trace.DEBUG("TagFileList filePath.exists() && filePath.isDirectory()");
	        int iBeanIndex = 0;
	    	  // TRACE
		      //Trace.DEBUG("TagFileList BEFORE copyListIntoVectorAt 1");
		      //copyListIntoVectorAt(File rootFile, Object[] list, Vector vec, int at, int beanIndex, Class classBean)		      
		      copyListIntoVectorAt(filePath, filePath.listFiles(), pathList, 0, iBeanIndex++, BeanFile.class);
	    	  // TRACE
		      //Trace.DEBUG("TagFileList AFTER copyListIntoVectorAt 1");
	        File fileItem = null;
	    	  // TRACE
		      //Trace.DEBUG("TagFileList pathList.size");
	        for (int i = 0; i < pathList.size(); i++) {
	          fileItem = (BeanFile)pathList.elementAt(i);
	    	  // TRACE
		      //Trace.DEBUG("TagFileList fileItem "+i+":"+fileItem);
	          if ((filePathToExpand!=null)&&(fileItem.isDirectory())) {
		    	  // TRACE
			      //Trace.DEBUG("TagFileList fileItem "+i+" (filePathToExpand!=null)&&(fileItem.isDirectory()):"+((filePathToExpand!=null)&&(fileItem.isDirectory())));
	            if (filePathToExpand.getAbsolutePath().startsWith(fileItem.getAbsolutePath())) {
	  	    	  // TRACE
	  		      //Trace.DEBUG("TagFileList BEFORE copyListIntoVectorAt 2 "+i);
	              copyListIntoVectorAt(filePath, fileItem.listFiles(fileFilter), pathList, i+1, iBeanIndex++, BeanFile.class);
	  	    	  // TRACE
	  		      //Trace.DEBUG("TagFileList AFTER copyListIntoVectorAt 2 "+i);
	            }
	          }
	        }
	      }
	    }
		if (UtilString.isNotEmpty(szEnd)){
			iEnd = index + Integer.parseInt(szEnd);
			iEnd = (iEnd > pathList.size()) ? pathList.size() : iEnd;
		}
		else {
			iEnd = pathList.size();
		}
    }
    else {
      pathList = null;
      index = 0;
      iEnd = 0;
    }
	  // TRACE
    //Trace.DEBUG("TagFileList doStartTag pathList:"+pathList+" name:"+getName()+" pathList.size:"+(pathList==null ? null : pathList.size()));
    if (UtilString.isNotEmpty(getName())){ 
      if (UtilString.isEqualsIgnoreCase(getScope(), "session"))
        pageContext.getSession().setAttribute(getName(), pathList);
      else
        pageContext.getRequest().setAttribute(getName(), pathList);
    }

    return (((pathList!=null)&&(iEnd>index)) ? EVAL_BODY_BUFFERED : SKIP_BODY);
  }

  public int doAfterBody() {
	// TRACE
	//Trace.DEBUG("TagFileList doAfterBody");
    try {
	    BodyContent bc = getBodyContent();
	    String szIndexStep = getIndexStepReplaceParamByRequestValue();
	    index+=UtilString.isEmpty(szIndexStep) ? 1 : Integer.parseInt(szIndexStep);
		// TRACE
		//Trace.DEBUG("TagFileList index:"+index);
	    if (bc != null) {
	        JspWriter out = bc.getEnclosingWriter();
	        out.println(bc.getString());
	        bc.clearBody();
	    }
	/*
    }
    catch (IOException ioe) {
      Trace.ERROR("Error in BodingTag: ", ioe);
	*/
	} catch(Exception ex) {
	  Trace.ERROR(this, ex);
	}
    return (((pathList!=null)&&(iEnd>index)&&(pathList.size()>index)) ? EVAL_BODY_BUFFERED : SKIP_BODY);
  }

  public int doEndTag() {
    return EVAL_PAGE;
  }

  private void copyListIntoVectorAt(File rootFile, Object[] list, Vector vec, int at, int beanIndex, Class classBean) {
      try {
		// TRACE
	    //Trace.DEBUG("TagFileList copyListIntoVectorAt rootFile:"+rootFile+" list:"+list+" at:"+at+" beanIndex:"+beanIndex+" classBean:"+classBean);
	    String methodName = getSortMethod();
		// TRACE
	    //Trace.DEBUG("TagFileList copyListIntoVectorAt methodName:"+methodName);
	    if (UtilString.isNotEmpty(methodName)) {
	      list = UtilSort.sortIncrease(list, methodName);
	/*
	    	  try {
	          java.io.StringWriter strW =  new java.io.StringWriter();
	          java.io.FileWriter fileW =  new java.io.FileWriter(new java.io.File("a.txt"));
	          ex.printStackTrace(new java.io.PrintWriter(strW));
	          fileW.write(strW.toString());
	          fileW.flush();
	          fileW.close();
	        } catch(Exception ex1) {}
	*/
	      }
	
	    //for( int i=0 ; i<list.length ; vec.add(at++,list[i++]));
	
	    for( int i=0 ; i<list.length ; i++ ) {
			// TRACE
		    //Trace.DEBUG("TagFileList copyListIntoVectorAt list["+i+"]:"+list[i]);
	        //vec.add(at++, new BeanFile(beanIndex, i, rootFile, ((File)list[i]).getAbsolutePath()));
	    	if (list[i] instanceof BeanFile) {
	    		vec.add(at++,list[i]);
	    	}
	    	else {
		    	Class[] classLst = new Class[]{Integer.class, Integer.class, classBean, String.class};
				// TRACE
			    //Trace.DEBUG("TagFileList copyListIntoVectorAt classLst:"+classLst);
		    	//Object[] objectLst = new Object[]{new Integer(beanIndex), new Integer(i), rootFile, ((BeanFile)list[i]).getPathUriRelative()};
		    	Object[] objectLst = new Object[]{new Integer(beanIndex), new Integer(i), rootFile, list[i]};
				// TRACE
			    //Trace.DEBUG("TagFileList copyListIntoVectorAt objectLst:"+objectLst);
			    Object cnstr = classBean.getConstructor(classLst).newInstance(objectLst);
				// TRACE
			    //Trace.DEBUG("TagFileList copyListIntoVectorAt cnstr:"+cnstr);
		    	if (at>=vec.size())
		    		vec.add(cnstr);
		    	else
		    		vec.add(at++, cnstr);
	    	}
	    }
	} catch(Exception ex) {
		Trace.ERROR(this, ex);
	}
  }

  public BeanFile getCurrentBean() {
    return (BeanFile)UtilVector.safeGetElementAt(pathList, index);
  }

  public File getCurrentFile() {
    return (File)UtilVector.safeGetElementAt(pathList, index);
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

/**
* @return  the withDirectory
* @uml.property  name="withDirectory"
*/
public String getWithDirectory() {
      return withDirectory;
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

  public String getWithDirectoryReplaceParamByRequestValue() {
    return UtilRequest.replaceParamByRequestValue(withDirectory, pageContext.getRequest(), pageContext.getSession(), "");
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
* @param withDirectory  the withDirectory to set
* @uml.property  name="withDirectory"
*/
public void setWithDirectory(String withDirectory) {
      this.withDirectory = withDirectory;
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