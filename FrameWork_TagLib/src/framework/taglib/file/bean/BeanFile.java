package framework.taglib.file.bean;

import framework.ressource.util.UtilFile;
import framework.ressource.util.UtilString;
import framework.trace.Trace;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;


/**
 * @author  HP_Administrateur
 */
public class BeanFile extends java.io.File{

  private static final long serialVersionUID = -6293076711787008449L;

  public static final int TYPE_FILE = 1;

  private int index;
  private File rootFile;
  private int position;
  private int type;
  private int nbLine;
  private String buffer = null;
  private long initialModified; 

  public BeanFile(String filename) {
	  this(0, 0, (BeanFile)null, filename);
  }

  public BeanFile(BeanFile rootFile, String filename) {
	  this(0, 0, rootFile, filename);
  }

  public BeanFile(Integer index, Integer position, String filename) {
	  this(index.intValue(), position.intValue(), (BeanFile)null, filename);
  }

  public BeanFile(int index, int position, String filename) {
	  this(index, position, (BeanFile)null, filename);
  }

  public BeanFile(Integer index, Integer position, BeanFile rootFile, String filename) {
	  this(index.intValue(), position.intValue(), rootFile, filename);
  }

  public BeanFile(int index, int position, BeanFile rootFile, String filename) {
	  this(index, position, rootFile, new File(filename));
  }

  public BeanFile(int index, int position, BeanFile rootFile, File file) {
	  super((rootFile!=null) ? (rootFile.getRootFile()!=null) ? rootFile.getRootFile().getAbsoluteFile() : rootFile.getAbsoluteFile() : null,
            (rootFile!=null) ? (rootFile.getRootFile()!=null) ? UtilFile.getPathRelative(rootFile.getRootFile(), file) : UtilFile.getPathRelative(rootFile, file) : file.getAbsolutePath());
      setIndex(index);
      setPosition(position);
      setRootFile((rootFile!=null) && (rootFile.getRootFile()!=null) ? rootFile.getRootFile() : rootFile);
      setInitialModified(this.lastModified());

	  initType(file.getAbsolutePath());
  }

  protected void initType(String filename) {
	  type = TYPE_FILE;
  }

  /**
 * @return  the index
 * @uml.property  name="index"
 */
public int getIndex() {
      return index;
  }
  /**
 * @return  the position
 * @uml.property  name="position"
 */
public int getPosition() {
      return position;
  }
  /**
 * @return  the rootFile
 * @uml.property  name="rootFile"
 */
public File getRootFile() {
    return rootFile;
  }
  /**
 * @param index  the index to set
 * @uml.property  name="index"
 */
public void setIndex(int index) {
      this.index = index;
  }
  /**
 * @param position  the position to set
 * @uml.property  name="position"
 */
public void setPosition(int position) {
      this.position = position;
  }
  /**
 * @param rootFile  the rootFile to set
 * @uml.property  name="rootFile"
 */
public void setRootFile(File rootFile) {
    this.rootFile = rootFile;
  }
  public String getPathRelative() {
    return UtilFile.getPathRelative(getRootFile(), this);
  }
  public String getPathUriRelative() {
    String ret = getPathRelative();
    if (ret!=null)
      ret = ret.replace('\\', '/');
    return ret;
  }
  public String getContent(){
	  String ret = "";
      try {
    	  ret = read();
      }
      catch (FileNotFoundException ex) {
      }
      catch (IOException ex) {
      }
      return ret;
  }

  public String getExtension() {
    String ret = null;
    String szName = this.getName();
    int pos = szName.charAt('.');
    if (pos >= 0) {
      ret = szName.substring(pos+1, szName.length());
    }
    return ret;
  }
  /**
   * Renvoi True si le fichier est à jour par rapport au fichier passé en paramètre.
   * En testant les dates de modification et la taille des fichiers
   * @param file
   * @return
   */
  public boolean isUpToDate (BeanFile file) {
	// Verifi si se sont les même fichiers
	boolean ret = false;
	try {
		ret = this.getCanonicalPath().equals(file.getCanonicalPath());
		  if (ret)
			  // Verifi si le fichier n'a pas été modifié
			  ret = (file.lastModified()==this.getInitialModified()) && (file.length()==(buffer!=null ? buffer.length() : this.length()));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return ret;
  }

  public String read() throws IOException {
	StringBuffer ret = new StringBuffer();
	FileReader fr = new FileReader(this);
	int ch = -1;
	int iNbLine=0;
	while ((ch = fr.read()) != -1) {
		if (ch=='\r')
			iNbLine++;
		ret.append((char)ch);
	}
	setBuffer(ret.toString());
    setNbLine(iNbLine);
/*
	String line;
	BufferedReader in = new BufferedReader(fr);

    if (!in.ready())
        throw new IOException();

    try {
    	int iNbLine=0;
	    while ((line = in.readLine()) != null) {
	    	ret.append(line);
	    	iNbLine++;
	    }
	    setNbLine(iNbLine);
    } finally {
    	in.close();
    }
*/

	return buffer.toString();
  }

  public String read(int lineStart, int nbLine) throws IOException {
	return UtilString.readLine(read(), lineStart, nbLine);
  }

    /**
	 * @return  the type
	 * @uml.property  name="type"
	 */
    public int getType() {
		return type;
	}
	/**
	 * @param type  the type to set
	 * @uml.property  name="type"
	 */
	public void setType(int type) {
		this.type = type;
	}
	public boolean canRead() {
		// TODO Auto-generated method stub
		return super.canRead();
	}
	public boolean canWrite() {
		// TODO Auto-generated method stub
		return super.canWrite();
	}
	public int compareTo(File pathname) {
		// TODO Auto-generated method stub
		return super.compareTo(pathname);
	}
/* JDK 1.4
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return super.compareTo(o);
	}
*/
	public boolean createNewFile() throws IOException {
		// TODO Auto-generated method stub
		return super.createNewFile();
	}
	public boolean delete() {
		// TODO Auto-generated method stub
		return super.delete();
	}
	public void deleteOnExit() {
		// TODO Auto-generated method stub
		super.deleteOnExit();
	}
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	public boolean exists() {
		// TODO Auto-generated method stub
		return super.exists();
	}
	public File getAbsoluteFile() {
		// TODO Auto-generated method stub
		return super.getAbsoluteFile();
	}
	public String getAbsolutePath() {
		// TODO Auto-generated method stub
		return super.getAbsolutePath();
	}
	public File getCanonicalFile() throws IOException {
		// TODO Auto-generated method stub
		return super.getCanonicalFile();
	}
	public String getCanonicalPath() throws IOException {
/*
		String ret = "";
		if (rootFile!=null) {
			ret = rootFile.getCanonicalPath();
			if(!super.getPath().startsWith(File.separator) && !ret.endsWith(File.separator))
				ret += File.separator;
			ret += super.getPath();
		}
		else
			ret = super.getCanonicalPath();
		return ret;
*/
		return super.getCanonicalPath();
	}
	public String getName() {
		// TODO Auto-generated method stub
		return super.getName();
	}
	public String getParent() {
		// TODO Auto-generated method stub
		return super.getParent();
	}
	public File getParentFile() {
		// TODO Auto-generated method stub
		return super.getParentFile();
	}
	public String getPath() {
		// TODO Auto-generated method stub
		return super.getPath();
	}
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	public boolean isAbsolute() {
		// TODO Auto-generated method stub
		return super.isAbsolute();
	}
	public boolean isDirectory() {
		// TODO Auto-generated method stub
		return super.isDirectory();
	}
	public boolean isFile() {
		// TODO Auto-generated method stub
		return super.isFile();
	}
	public boolean isHidden() {
		// TODO Auto-generated method stub
		return super.isHidden();
	}
	public long lastModified() {
		// TODO Auto-generated method stub
		return super.lastModified();
	}
	public long length() {
		// TODO Auto-generated method stub
		return super.length();
	}
	public String[] list() {
		// TODO Auto-generated method stub
		return super.list();
	}
	public String[] list(FilenameFilter filter) {
		// TODO Auto-generated method stub
		return super.list(filter);
	}
	public File[] listFiles() {
  	  // TRACE
      //Trace.DEBUG("BeanFile listFiles START");
		File root = (getRootFile()==null) ? this : getRootFile();
	  	  // TRACE
	      //Trace.DEBUG("BeanFile listFiles root:"+root);
		File[] fileList = super.listFiles();
	  	  // TRACE
	      //Trace.DEBUG("BeanFile listFiles fileList:"+fileList);
		BeanFile[] beanFileList = new BeanFile[fileList.length];
		for(int i=0 ; i<fileList.length ; i++) {
		  	  // TRACE
		      //Trace.DEBUG("BeanFile listFiles fileList["+i+"]:"+fileList[i]);
			beanFileList[i] = new BeanFile(new Integer(getIndex()+1), new Integer(i), this, UtilFile.getPathRelative(root, fileList[i]));//fileList[i].getName());
		  	  // TRACE
		      //Trace.DEBUG("BeanFile listFiles fileList["+i+"]:"+fileList[i]+" beanFileList["+i+"]:"+beanFileList[i]);
		}
	  	  // TRACE
	      //Trace.DEBUG("BeanFile listFiles END");
		return beanFileList;
	}
	public File[] listFiles(FileFilter filter) {
		// TODO Auto-generated method stub
		File root = (getRootFile()==null) ? this : getRootFile();
		File[] fileList = super.listFiles(filter);
		BeanFile[] beanFileList = new BeanFile[fileList.length];
		for(int i=0 ; i<fileList.length ; i++) {
			beanFileList[i] = new BeanFile(new Integer(getIndex()+1), new Integer(i), this, UtilFile.getPathRelative(root, fileList[i]));//fileList[i].getName());
		}
		return beanFileList;
	}
	public File[] listFiles(FilenameFilter filter) {
		// TODO Auto-generated method stub
		File root = (getRootFile()==null) ? this : getRootFile();
		File[] fileList = super.listFiles(filter);
		BeanFile[] beanFileList = new BeanFile[fileList.length];
		for(int i=0 ; i<fileList.length ; i++) {
			beanFileList[i] = new BeanFile(new Integer(getIndex()+1), new Integer(i), this, UtilFile.getPathRelative(root, fileList[i]));//fileList[i].getName());
		}
		return beanFileList;
	}
	public boolean mkdir() {
		// TODO Auto-generated method stub
		return super.mkdir();
	}
	public boolean mkdirs() {
		// TODO Auto-generated method stub
		return super.mkdirs();
	}
	public boolean renameTo(File dest) {
		// TODO Auto-generated method stub
		return super.renameTo(dest);
	}
	public boolean setLastModified(long time) {
		// TODO Auto-generated method stub
		return super.setLastModified(time);
	}
	public boolean setReadOnly() {
		// TODO Auto-generated method stub
		return super.setReadOnly();
	}
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	public URI toURI() {
		// TODO Auto-generated method stub
		return super.toURI();
	}
	public URL toURL() throws MalformedURLException {
		// TODO Auto-generated method stub
		return super.toURL();
	}

	/**
	 * @return  the nbLine
	 * @uml.property  name="nbLine"
	 */
	public int getNbLine() {
		return nbLine;
	}

	/**
	 * @param nbLine  the nbLine to set
	 * @uml.property  name="nbLine"
	 */
	public void setNbLine(int nbLine) {
		this.nbLine = nbLine;
	}

	/**
	 * @return  the buffer
	 * @uml.property  name="buffer"
	 */
	public String getBuffer() {
		return buffer;
	}

	/**
	 * @param buffer  the buffer to set
	 * @uml.property  name="buffer"
	 */
	public void setBuffer(String buffer) {
		this.buffer = buffer;
	}

	/**
	 * @return  the initialModified
	 * @uml.property  name="initialModified"
	 */
	public long getInitialModified() {
		return initialModified;
	}

	/**
	 * @param initialModified  the initialModified to set
	 * @uml.property  name="initialModified"
	 */
	public void setInitialModified(long initialModified) {
		this.initialModified = initialModified;
	}
}
