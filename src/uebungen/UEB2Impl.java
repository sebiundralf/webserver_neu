package uebungen;

import java.io.InputStream;

import mywebserver.UrlImpl;
import BIF.SWE1.interfaces.Request;
import BIF.SWE1.interfaces.Response;
import BIF.SWE1.interfaces.UEB2;
import BIF.SWE1.interfaces.Url;

public class UEB2Impl implements UEB2 {

	@Override
	public Url GetUrl(String path) {
		return new UrlImpl(path);
	}

	@Override
	public void HelloWorld() {
	}

	@Override
	public Request GetRequest(InputStream arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response GetResponse() {
		// TODO Auto-generated method stub
		return null;
	}
}
