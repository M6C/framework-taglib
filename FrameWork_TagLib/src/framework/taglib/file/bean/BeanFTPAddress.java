package framework.taglib.file.bean;

import framework.ressource.ftp.FTPClient;
import framework.ressource.ftp.FTPException;
import framework.ressource.util.UtilString;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author  HP_Administrateur
 */
public class BeanFTPAddress {

	private String login = null;
	private String password = null;
	private String host = null;
	private String port = null;
	private String path = null;

	public BeanFTPAddress(String uri) {
		super();
		parseUri(uri);
	}

	private void parseUri(String strLine) {
		// Recupere les paramètres d'une chaine au format 'ftp://monlogin:monpassword@monserveurftp'
		int indexFrom = 6;
		int indexTo = strLine.indexOf('@', indexFrom);
		if (indexTo>0) {
    		// Login
    		login = strLine.substring(indexFrom, indexTo);
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
		host = strLine.substring(indexFrom);
		// Port
		int indexPort = host.indexOf(':');
		if (indexPort>0) {
			port = strLine.substring(indexPort+1);
			host = host.substring(0, indexPort);
    		// Path
    		int indexPath = (port.indexOf('/')>0) ? port.indexOf('/') : port.indexOf('\\');
    		if (indexPath>0) {
	    		path = port.substring(indexPath+1);
    			port = port.substring(0, indexPath);
    		}
		}
		else {
    		// Path
    		int indexPath = (host.indexOf('/')>0) ? host.indexOf('/') : host.indexOf('\\');
    		if (indexPath>0) {
	    		path = host.substring(indexPath);
	    		host = host.substring(0, indexPath);
    		}
		}
		if (UtilString.isEmpty(path))
			path = "/";
		if (UtilString.isEmpty(port))
    		port = "21";
	}


	public String read() throws IOException {
		String ret = null;
		FTPClient ftpc = null;
		OutputStream os = null;
		try {
			os = new ByteArrayOutputStream();
			ftpc = new FTPClient(host, Integer.parseInt(port));
			ftpc.login(login, password);
			ftpc.chdir("/");
			ftpc.get(os, path);
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
			if (os!=null)
				os.close();				
			os.close();
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
	
	/**
	 * @return  the host
	 * @uml.property  name="host"
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @param host  the host to set
	 * @uml.property  name="host"
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * @return  the login
	 * @uml.property  name="login"
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login  the login to set
	 * @uml.property  name="login"
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return  the password
	 * @uml.property  name="password"
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password  the password to set
	 * @uml.property  name="password"
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return  the path
	 * @uml.property  name="path"
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path  the path to set
	 * @uml.property  name="path"
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @return  the port
	 * @uml.property  name="port"
	 */
	public String getPort() {
		return port;
	}

	/**
	 * @param port  the port to set
	 * @uml.property  name="port"
	 */
	public void setPort(String port) {
		this.port = port;
	}

}
