package uebungen;

import java.io.InputStream;

import mywebserver.PluginManagerImpl;
import mywebserver.RequestImpl;
import mywebserver.StaticFilePlugin;
import BIF.SWE1.interfaces.Plugin;
import BIF.SWE1.interfaces.PluginManager;
import BIF.SWE1.interfaces.Request;
import BIF.SWE1.interfaces.UEB5;

public class UEB5Impl implements UEB5 {

	public static String fileurl = "";
	public static String folder = "";

	@Override
	public void helloWorld() {
	}

	@Override
	public PluginManager getPluginManager() {
		// TODO Auto-generated method stub
		return new PluginManagerImpl();
	}

	@Override
	public Request getRequest(InputStream arg0) {
		// TODO Auto-generated method stub
		return new RequestImpl(arg0);
	}

	@Override
	public Plugin getStaticFilePlugin() {
		// TODO Auto-generated method stub
		return new StaticFilePlugin();
	}

	@Override
	public String getStaticFileUrl(String arg0) {
		
		
		fileurl =  folder + "\\" + arg0;	
		return fileurl;
	}

	@Override
	public void setStatiFileFolder(String arg0) {
		
		
		folder = System.getProperty("user.dir") + "\\" + arg0;
		//System.out.println(folder);
		
		
	}
}
