package uebungen;

import java.io.InputStream;

import mywebserver.RequestImpl;
import mywebserver.ResponseImpl;
import mywebserver.TestPluginImpl;
import BIF.SWE1.interfaces.Plugin;
import BIF.SWE1.interfaces.Request;
import BIF.SWE1.interfaces.Response;
import BIF.SWE1.interfaces.UEB3;

public class UEB3Impl implements UEB3 {

	@Override
	public void helloWorld() {
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

	@Override
	public Plugin getTestPlugin() {
		// TODO Auto-generated method stub
		return new TestPluginImpl();
	}
}
