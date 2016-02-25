package framework.taglib.file.bean;

import framework.ressource.util.UtilFile;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;


/**
 * @author  HP_Administrateur
 */
public class BeanFile2 {
  private int index;
  private File file;
  private File rootFile;
  private int position;
  public BeanFile2(int index, int position, String filename, String rootFilename) {
      setIndex(index);
      setPosition(position);
      setFile(file);
      setRootFile(rootFile);
  }
  public BeanFile2(int index, int position, File file, File rootFile) {
      setIndex(index);
      setPosition(position);
      setFile(file);
      setRootFile(rootFile);
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
 * @return  the file
 * @uml.property  name="file"
 */
public File getFile() {
    return file;
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
 * @param file  the file to set
 * @uml.property  name="file"
 */
public void setFile(File file) {
    this.file = file;
  }
  /**
 * @param rootFile  the rootFile to set
 * @uml.property  name="rootFile"
 */
public void setRootFile(File rootFile) {
    this.rootFile = rootFile;
  }
  public String getPathRelative() {
    return UtilFile.getPathRelative(rootFile, file);
  }
  public String getPathUriRelative() {
    String ret = getPathRelative();
    if (ret!=null)
      ret = ret.replace('\\', '/');
    return ret;
  }
  public String getContent(){
    StringBuffer ret = new StringBuffer();
    if((getFile()!=null)&&(getFile().exists())&&(getFile().isFile())){
      try {
        FileReader fr = new FileReader(getFile());
        int ch = -1;
        while ((ch=fr.read())!=-1){
          ret.append(ch);
        }
      }
      catch (FileNotFoundException ex) {
      }
      catch (IOException ex) {
      }
    }
    return ret.toString();
  }
  public String getExtension() {
    String ret = null;
    if (getFile()!=null) {
      String szName = getFile().getName();
      int pos = szName.charAt('.');
      if (pos >= 0) {
        ret = szName.substring(pos+1, szName.length());
      }
    }
    return ret;
  }
	public boolean canRead() {
		// TODO Auto-generated method stub
		return file.canRead();
	}
	public boolean canWrite() {
		// TODO Auto-generated method stub
		return file.canWrite();
	}
	public int compareTo(File pathname) {
		// TODO Auto-generated method stub
		return file.compareTo(pathname);
	}
/* JDK 1.4
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return file.compareTo(o);
	}
*/
	public boolean createNewFile() throws IOException {
		// TODO Auto-generated method stub
		return file.createNewFile();
	}
	public boolean delete() {
		// TODO Auto-generated method stub
		return file.delete();
	}
	public void deleteOnExit() {
		// TODO Auto-generated method stub
		file.deleteOnExit();
	}
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return file.equals(obj);
	}
	public boolean exists() {
		// TODO Auto-generated method stub
		return file.exists();
	}
	public File getAbsoluteFile() {
		// TODO Auto-generated method stub
		return file.getAbsoluteFile();
	}
	public String getAbsolutePath() {
		// TODO Auto-generated method stub
		return file.getAbsolutePath();
	}
	public File getCanonicalFile() throws IOException {
		// TODO Auto-generated method stub
		return file.getCanonicalFile();
	}
	public String getCanonicalPath() throws IOException {
		// TODO Auto-generated method stub
		return file.getCanonicalPath();
	}
	public String getName() {
		// TODO Auto-generated method stub
		return file.getName();
	}
	public String getParent() {
		// TODO Auto-generated method stub
		return file.getParent();
	}
	public File getParentFile() {
		// TODO Auto-generated method stub
		return file.getParentFile();
	}
	public String getPath() {
		// TODO Auto-generated method stub
		return file.getPath();
	}
	public int hashCode() {
		// TODO Auto-generated method stub
		return file.hashCode();
	}
	public boolean isAbsolute() {
		// TODO Auto-generated method stub
		return file.isAbsolute();
	}
	public boolean isDirectory() {
		// TODO Auto-generated method stub
		return file.isDirectory();
	}
	public boolean isFile() {
		// TODO Auto-generated method stub
		return file.isFile();
	}
	public boolean isHidden() {
		// TODO Auto-generated method stub
		return file.isHidden();
	}
	public long lastModified() {
		// TODO Auto-generated method stub
		return file.lastModified();
	}
	public long length() {
		// TODO Auto-generated method stub
		return file.length();
	}
	public String[] list() {
		// TODO Auto-generated method stub
		return file.list();
	}
	public String[] list(FilenameFilter filter) {
		// TODO Auto-generated method stub
		return file.list(filter);
	}
	public File[] listFiles() {
		// TODO Auto-generated method stub
		return file.listFiles();
	}
	public File[] listFiles(FileFilter filter) {
		// TODO Auto-generated method stub
		return file.listFiles(filter);
	}
	public File[] listFiles(FilenameFilter filter) {
		// TODO Auto-generated method stub
		return file.listFiles(filter);
	}
	public boolean mkdir() {
		// TODO Auto-generated method stub
		return file.mkdir();
	}
	public boolean mkdirs() {
		// TODO Auto-generated method stub
		return file.mkdirs();
	}
	public boolean renameTo(File dest) {
		// TODO Auto-generated method stub
		return file.renameTo(dest);
	}
	public boolean setLastModified(long time) {
		// TODO Auto-generated method stub
		return file.setLastModified(time);
	}
	public boolean setReadOnly() {
		// TODO Auto-generated method stub
		return file.setReadOnly();
	}
	public String toString() {
		// TODO Auto-generated method stub
		return file.toString();
	}
	public URI toURI() {
		// TODO Auto-generated method stub
		return file.toURI();
	}
	public URL toURL() throws MalformedURLException {
		// TODO Auto-generated method stub
		return file.toURL();
	}
}
