package uebungen;

import java.io.InputStream;

import mywebserver.UrlImpl;
import BIF.SWE1.interfaces.Request;
import BIF.SWE1.interfaces.Response;
import BIF.SWE1.interfaces.UEB2;
import BIF.SWE1.interfaces.Url;

public class UEB2Impl implements UEB2 {

	@Override
	public Url getUrl(String path) {
		return new UrlImpl(path);
	}

	@Override
	public void helloWorld() {
	}

	@Override
	public Request getRequest(InputStream arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getResponse() {
		// TODO Auto-generated method stub
		return null;
	}
}
