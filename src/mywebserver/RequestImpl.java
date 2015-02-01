package mywebserver;

import java.io.InputStream;
import java.util.Map;

import BIF.SWE1.interfaces.Request;
import BIF.SWE1.interfaces.Url;

public class RequestImpl implements Request {
	
	InputStream stream;
	
	public RequestImpl(InputStream arg0){
		stream = arg0;
	}
	
	
	@Override
	public boolean isValid() {
		

		
		return true;
	}

	@Override
	public String getMethod() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Url getUrl() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> getHeaders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getContentLength() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getContentType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InputStream getContentStream() {
		// TODO Auto-generated method stub
		return null;
	}

}
