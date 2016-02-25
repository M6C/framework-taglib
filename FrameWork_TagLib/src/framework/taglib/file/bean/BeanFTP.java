package framework.taglib.file.bean;

import framework.ressource.ftp.FTPClient;
import framework.ressource.ftp.FTPException;
import framework.ressource.util.UtilFile;
import framework.ressource.util.UtilString;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author  HP_Administrateur
 */
public class BeanFTP extends BeanFile {

    private static final long serialVersionUID = -6748670042342740411L;

    public static final int TYPE_FTP = 2;

    private BeanFTPAddress address;
    private Date lastWriteTime;
    private String fileName;
    private Long fileSize;
    private String permissions;
    private String permissionUser;
    private String permissionGroup;
    private String permissionOther;
    private String owner;
    private String group;
    private String pathRelative;
    private boolean directory;

    public BeanFTP(BeanFTPAddress address) {
        this((BeanFTPAddress)null, null);
        setAddress(address);
        setFileName(address.getPath());
        pathRelative = address.getPath();
    }

    public BeanFTP(BeanFTPAddress address, String filename) {
        this((BeanFTP)null, filename);
        setAddress(address);
    }

    public BeanFTP(BeanFTP rootFileFtp, String filename) {
        this(0, 0, rootFileFtp, filename);
    }

    public BeanFTP(BeanFTPAddress address, int index, int position, String filename) {
        this(index, position, null, filename);
        setAddress(address);
    }

    public BeanFTP(Integer index, Integer position, BeanFTP rootFileFtp, String filename) {
        this(index.intValue(), position.intValue(), rootFileFtp, filename);
    }

    public BeanFTP(int index, int position, BeanFTP rootFileFtp, String filename) {
        super(index, position, rootFileFtp, (filename == null ? "" : filename));
        setAddress((rootFileFtp!=null) ? rootFileFtp.getAddress() : null);
        setRootFile(rootFileFtp);
        try {
            if (filename!=null) {
                parseItem(filename);
                if (rootFileFtp!=null) {
                    StringBuffer sbPathRelative = new StringBuffer();
                    File root = rootFileFtp;
                    while ((root!=null) && (root instanceof BeanFile)) {
                        sbPathRelative.append(File.separator).append(root.getName());
                        root = ((BeanFile)root).getRootFile();
                    }
                    pathRelative = sbPathRelative.append(fileName).toString();
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    protected void initType(String filename) {
        setType(TYPE_FTP);
    }

    private void parseItem (String strLine) throws ParseException {

        System.out.println("parseItem:" + strLine);

        //Retrieve the start position of the file/directory date string
        int intDatePosition = GetDateStartPosition(strLine);
        //Get the file name start position: intDatePosition + Len("Mar 21  1998") + 1
        int intFileNamePosition = intDatePosition + 13;

        //Remove CR symbols from the line
        //strLine = Replace$(strLine, vbCr, "")
        
        //Create a new instance of the CFtpFile class
        if (intDatePosition > 10) {
            //Here is the "UNIX FTP listing format"

            //Get the last modified date
            lastWriteTime = GetDateX(strLine.substring(intDatePosition, intFileNamePosition));

            //Get the file name
            if (strLine.charAt(0)=='l') {
                int intLinkPosition = strLine.lastIndexOf(" -> ", intFileNamePosition);
                if (intLinkPosition > 0) {
                    fileName = strLine.substring(intFileNamePosition, intLinkPosition - intFileNamePosition);
                    fileName = strLine.substring(intLinkPosition + 4);
                } else {
                    fileName = strLine.substring(intFileNamePosition);
                }
            } else {
                fileName = strLine.substring(intFileNamePosition);
            }

            //Get the file size
            //int intFileSizePosition = strLine.length() - strLine.indexOf(" ", intDatePosition - 2) + 1;
            int intFileSizePosition = strLine.substring(0, intDatePosition).trim().lastIndexOf(" ");
            fileSize = new Long(strLine.substring(intFileSizePosition, intDatePosition).trim());

            //Retrieve other file properties

            //Cut the line string
            strLine = strLine.substring(0, intFileSizePosition - 1);

            //Replace multiple whitespaces with a single one
            for (int i = 15 ; i>2 ; i--) {
                StringBuffer str = new StringBuffer();
                for (int j=0 ; j++<i ; str.append(" "));
                strLine = strLine.replaceAll(str.toString(), " ");
            }

            //Put each part of the line into an item of the varLineParts array
            String[] varLineParts = strLine.split(" ");

            //Assign properties values
            permissions = varLineParts[0];
            

            if (permissions.length()>=1) {
                //The IsDirectory property
                directory = strLine.substring(0, 1).equals("d");
                if (permissions.length()>=3) {
                    permissionUser = permissions.substring(1, 4).toLowerCase();
                    if (permissions.length()>=6) {
                        permissionGroup = permissions.substring(4, 7).toLowerCase();
                        if (permissions.length()>=10) {
                            permissionOther = permissions.substring(7, 10).toLowerCase();
                        }
                        else {
                            permissionOther = "---";
                        }
                    }
                    else {
                        permissionGroup = "---";
                        permissionOther = "---";
                    }
                }
                else {
                    permissionUser = "---";
                    permissionGroup = "---";
                    permissionOther = "---";
                }
            }
            else {
                directory = false;
                permissionUser = "---";
                permissionGroup = "---";
                permissionOther = "---";
            }
            owner = varLineParts[2];
            if (varLineParts.length==4) {
                group = varLineParts[3];
            }
        }
        else {
            try {
                if (strLine.startsWith("/")) {
                    int index = strLine.lastIndexOf("/")+1;
                    pathRelative = strLine;
                    fileName = (index<strLine.length()) ? strLine.substring(index, strLine.length()) : "";
                }
                else if (strLine.length() > 0) {
                    //Here is the "DOS FTP listing format"
                    //-rw-r--r--   1 web site      192 Nov 27 15:55 ScreenShot.css
                    fileName = (strLine.length() > 46) ? strLine.substring(46) : "";
                    strLine = (strLine.length() > 46) ? strLine.substring(0, 46) : strLine;
                    lastWriteTime = (strLine.length() > 46) ? SimpleDateFormat.getInstance().parse(strLine.substring(33, 46).replaceAll("  ", " ").trim()) : null;
                    if (strLine.indexOf("<DIR>") > 0) {
                        directory = true;
                    } else if (strLine.length() >= 33) {
                        fileSize = new Long(strLine.substring(23,33).trim());
                    }
                }
            }
            catch(Exception ex) {
            }
        }
    }

    private Date GetDateX(String strDateString) throws ParseException {
        /*
        '
        'The strDateString could be in the following formats:
        '
        'Jul  9 11:25
        'OR
        'Mar 21  1998
        '
        */
        strDateString = strDateString.replaceAll("  ", " ");
        String strDay = strDateString.substring(4, 6).toLowerCase();
        String strMonth = strDateString.substring(0, 3).toLowerCase();
        String strYear = strDateString.substring(7).toLowerCase();
        String strTime;
        int intMonth = 0;
        if (strDay.length()==1)
            strDay = "0" + strDay;
        if (strYear.indexOf(":") > 0)  {
            strTime = strYear;
            strYear = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
        } else {
            strTime = "00:00";
        }
    
        if (strMonth.equals("jan")) intMonth = 1; else
        if (strMonth.equals("feb")) intMonth = 2; else
        if (strMonth.equals("mar")) intMonth = 3; else
        if (strMonth.equals("apr")) intMonth = 4; else
        if (strMonth.equals("may")) intMonth = 5; else
        if (strMonth.equals("jun")) intMonth = 6; else
        if (strMonth.equals("jul")) intMonth = 7; else
        if (strMonth.equals("aug")) intMonth = 8; else
        if (strMonth.equals("sep")) intMonth = 9; else
        if (strMonth.equals("oct")) intMonth = 10; else
        if (strMonth.equals("nov")) intMonth = 11; else
        if (strMonth.equals("dec")) intMonth = 12;
    
        strMonth = Integer.toString(intMonth);
    
        return new SimpleDateFormat("dd/mm/yyyy MM:ss").parse(strDay.trim()+"/"+strMonth.trim()+"/"+strYear.trim()+" "+strTime.trim());
    }

    private int GetDateStartPosition(String strListingLine) {
        /*
        '
        'drwxr-xr-x   4 ftpuser  ftpusers       512 Jul  2  2001 kylix
        '                                          <------------>
        'drwxr-xr-x   3 ftpuser  ftpusers       512 Jul 19 11:25 optimizeit
        '                                          <------------>
        '
        */
        int ret = 0;
        String[] varMonthsArray = new String[]{"jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec"};
        String strStringToSearch = strListingLine.toLowerCase();
        int intStartPosition = 0;

        for (int i = 0 ; i<11 ; i++) {

            intStartPosition = strStringToSearch.indexOf(" " + varMonthsArray[i] + " ");
    
            if (intStartPosition > 0) {
                try {
                    if ((Integer.parseInt(strListingLine.substring(intStartPosition - 1, intStartPosition))>= 0) &&
                        (Integer.parseInt(strListingLine.substring(intStartPosition + 9, intStartPosition + 9 + 1))>= 0)) {
                        ret = intStartPosition + 1;
                        break;
                    }
                } catch (NumberFormatException ex) {
                }
            }
        }
        return ret;
    }

    public String read() throws IOException {
        String ret = null;
        FTPClient ftpc = null;
        try {
            ftpc = new FTPClient(address.getHost(), Integer.parseInt(address.getPort()));
            ftpc.login(address.getLogin(), address.getPassword());
            ftpc.chdir(address.getPath());
            OutputStream os = new ByteArrayOutputStream();
            try {
            	ftpc.get(os, getPath());
            } finally {
            	os.close();
            }
            ret = os.toString();
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
        return ret.toString();
    }

    public void write(String text) {
        FTPClient ftpc = null;
        try {
            //InputStream is = new ByteArrayInputStream();
            ftpc = new FTPClient(address.getHost(), Integer.parseInt(address.getPort()));
            ftpc.login(address.getLogin(), address.getPassword());
            ftpc.chdir(address.getPath());
            ftpc.put(text.getBytes(), getPath());
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

    /**
	 * @return  the fileName
	 * @uml.property  name="fileName"
	 */
    public String getFileName() {
        return fileName;
    }
    
    /**
	 * @param fileName  the fileName to set
	 * @uml.property  name="fileName"
	 */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    /**
	 * @return  the fileSize
	 * @uml.property  name="fileSize"
	 */
    public Long getFileSize() {
        return fileSize;
    }
    
    /**
	 * @param fileSize  the fileSize to set
	 * @uml.property  name="fileSize"
	 */
    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }
    
    /**
	 * @return  the group
	 * @uml.property  name="group"
	 */
    public String getGroup() {
        return group;
    }
    
    /**
	 * @param group  the group to set
	 * @uml.property  name="group"
	 */
    public void setGroup(String group) {
        this.group = group;
    }
    
    /**
	 * @return  the lastWriteTime
	 * @uml.property  name="lastWriteTime"
	 */
    public Date getLastWriteTime() {
        return lastWriteTime;
    }
    
    /**
	 * @param lastWriteTime  the lastWriteTime to set
	 * @uml.property  name="lastWriteTime"
	 */
    public void setLastWriteTime(Date lastWriteTime) {
        this.lastWriteTime = lastWriteTime;
    }
    
    /**
	 * @return  the owner
	 * @uml.property  name="owner"
	 */
    public String getOwner() {
        return owner;
    }
    
    /**
	 * @param owner  the owner to set
	 * @uml.property  name="owner"
	 */
    public void setOwner(String owner) {
        this.owner = owner;
    }
    
    /**
	 * @return  the permissions
	 * @uml.property  name="permissions"
	 */
    public String getPermissions() {
        return permissions;
    }
    
    /**
	 * @param permissions  the permissions to set
	 * @uml.property  name="permissions"
	 */
    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    /**
	 * @return  the pathRelative
	 * @uml.property  name="pathRelative"
	 */
    public String getPathRelative() {
        return pathRelative;//UtilFile.getPathRelative(getRootFile(), this);
    }

    /**
	 * @return  the address
	 * @uml.property  name="address"
	 */
    public BeanFTPAddress getAddress() {
        return address;
    }

    /**
	 * @param address  the address to set
	 * @uml.property  name="address"
	 */
    public void setAddress(BeanFTPAddress address) {
        this.address = address;
    }

    public boolean canRead() {
        // TODO Auto-generated method stub
        return (permissionUser!=null) && (permissionUser.charAt(1)=='r');//super.canRead();
    }
    public boolean canWrite() {
        // TODO Auto-generated method stub
        return (permissionUser!=null) && (permissionUser.charAt(2)=='w');//super.canWrite();
    }
    public int compareTo(File pathname) {
        // TODO Auto-generated method stub
        return -1;//super.compareTo(pathname);
    }
/*
    public int compareTo(Object o) {
        // TODO Auto-generated method stub
        return -1;//super.compareTo(o);
    }
*/
    public boolean createNewFile() throws IOException {
        // TODO Auto-generated method stub
        boolean ret = false;
        FTPClient ftpc = null;
        try {
            //InputStream is = new ByteArrayInputStream();
            ftpc = new FTPClient(address.getHost(), Integer.parseInt(address.getPort()));
            ftpc.login(address.getLogin(), address.getPassword());
            ftpc.chdir(address.getPath());
            ftpc.put("", getPath());
            ret = true;
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
        return ret;//super.delete();
//        return false;//super.createNewFile();
    }
    public boolean delete() {
        boolean ret = false;
        FTPClient ftpc = null;
        try {
            //InputStream is = new ByteArrayInputStream();
            ftpc = new FTPClient(address.getHost(), Integer.parseInt(address.getPort()));
            ftpc.login(address.getLogin(), address.getPassword());
            ftpc.chdir(address.getPath());
            ftpc.delete(getPath());
            ret = true;
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
        return ret;//super.delete();
    }
    public void deleteOnExit() {
        // TODO Auto-generated method stub
        super.deleteOnExit();
    }
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        return false;//super.equals(obj);
    }
    public boolean exists() {
        // TODO Auto-generated method stub
        return true;//super.exists();
    }
    public File getAbsoluteFile() {
        // TODO Auto-generated method stub
        return this;//super.getAbsoluteFile();
    }
    public String getAbsolutePath() {
        // TODO Auto-generated method stub
        return pathRelative;//super.getAbsolutePath();
    }
    public File getCanonicalFile() throws IOException {
        // TODO Auto-generated method stub
        return this;//super.getCanonicalFile();
    }
    public String getCanonicalPath() throws IOException {
        // TODO Auto-generated method stub
        return pathRelative;//super.getCanonicalPath();
    }
    public String getName() {
        // TODO Auto-generated method stub
        return fileName;//super.getName();
    }
    public String getParent() {
        // TODO Auto-generated method stub
        return (getRootFile()!=null) ? getRootFile().getPath() : File.separator;//super.getParent();
    }
    public File getParentFile() {
        // TODO Auto-generated method stub
        return getRootFile();//super.getParentFile();
    }
    public String getPath() {
        // TODO Auto-generated method stub
        return pathRelative;// + File.separator + fileName;//super.getPath();
    }
    public int hashCode() {
        // TODO Auto-generated method stub
        return -1;//super.hashCode();
    }
    public boolean isAbsolute() {
        // TODO Auto-generated method stub
        return false;//super.isAbsolute();
    }
    /**
	 * @return  the directory
	 * @uml.property  name="directory"
	 */
    public boolean isDirectory() {
        // TODO Auto-generated method stub
        return directory;//super.isDirectory();
    }
    public boolean isFile() {
        // TODO Auto-generated method stub
        return !directory;//super.isFile();
    }
    public boolean isHidden() {
        // TODO Auto-generated method stub
        return false;//super.isHidden();
    }
    public long lastModified() {
        // TODO Auto-generated method stub
        return lastWriteTime.getTime();//super.lastModified();
    }
    public long length() {
        // TODO Auto-generated method stub
        return (fileSize==null) ? 0 : fileSize.longValue();//super.length();
    }
    public String[] list() {
        // TODO Auto-generated method stub
        return null;//super.list();
    }
    public String[] list(FilenameFilter filter) {
        // TODO Auto-generated method stub
        return null;//super.list(filter);
    }
    public File[] listFiles() {
        // TODO Auto-generated method stub
        File root = (getRootFile()==null) ? this : getRootFile();
        File[] fileList = super.listFiles();
        BeanFTP[] beanFTPList = new BeanFTP[fileList.length];
        for(int i=0 ; i<fileList.length ; i++) {
            beanFTPList[i] = new BeanFTP(new Integer(getIndex()+1), new Integer(i), this, UtilFile.getPathRelative(root, fileList[i]));//fileList[i].getName());
        }
        return beanFTPList;
    }
    public File[] listFiles(FileFilter filter) {
        // TODO Auto-generated method stub
        File root = (getRootFile()==null) ? this : getRootFile();
        File[] fileList = super.listFiles(filter);
        BeanFTP[] beanFTPList = new BeanFTP[fileList.length];
        for(int i=0 ; i<fileList.length ; i++) {
            beanFTPList[i] = new BeanFTP(new Integer(getIndex()+1), new Integer(i), this, UtilFile.getPathRelative(root, fileList[i]));//fileList[i].getName());
        }
        return beanFTPList;
    }
    public File[] listFiles(FilenameFilter filter) {
        // TODO Auto-generated method stub
        File root = (getRootFile()==null) ? this : getRootFile();
        File[] fileList = super.listFiles(filter);
        BeanFTP[] beanFTPList = new BeanFTP[fileList.length];
        for(int i=0 ; i<fileList.length ; i++) {
            beanFTPList[i] = new BeanFTP(new Integer(getIndex()+1), new Integer(i), this, UtilFile.getPathRelative(root, fileList[i]));//fileList[i].getName());
        }
        return beanFTPList;
    }
    public boolean mkdir() {
        // TODO Auto-generated method stub
        return false;//super.mkdir();
    }
    public boolean mkdirs() {
        // TODO Auto-generated method stub
        return false;//super.mkdirs();
    }
    public boolean renameTo(File dest) {
        // TODO Auto-generated method stub
        return false;//super.renameTo(dest);
    }
    public boolean setLastModified(long time) {
        // TODO Auto-generated method stub
        lastWriteTime = new Date(time);
        return true;//super.setLastModified(time);
    }
    public boolean setReadOnly() {
        // TODO Auto-generated method stub
        return (permissionUser.charAt(1)=='r') && (permissionUser.charAt(1)!='w');//super.setReadOnly();
    }
    public String toString() {
        // TODO Auto-generated method stub
        return fileName;//super.toString();
    }
    public URI toURI() {
        // TODO Auto-generated method stub
        return null;//super.toURI();
    }
    public URL toURL() throws MalformedURLException {
        // TODO Auto-generated method stub
        return null;//super.toURL();
    }
}