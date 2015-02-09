package uebungen;

import java.io.InputStream;

import mywebserver.PluginManagerImpl;
import mywebserver.RequestImpl;
import mywebserver.ResponseImpl;
import BIF.SWE1.interfaces.PluginManager;
import BIF.SWE1.interfaces.Request;
import BIF.SWE1.interfaces.Response;
import BIF.SWE1.interfaces.UEB4;

public class UEB4Impl implements UEB4 {

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
	public Response getResponse() {
		// TODO Auto-generated method stub
		return new ResponseImpl();
	}
}
