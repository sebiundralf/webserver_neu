package mywebserver;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import BIF.SWE1.interfaces.Response;

public class ResponseImpl implements Response {

	private int statuscode = 200;							//default statuscode
	
	HashMap<String, String> map = new HashMap<String, String>();
	
	
	@Override
	public Map<String, String> getHeaders() {
		
		return map;											//return map, created in addHeader()
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
	public void setContentType(String contentType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getStatusCode() {								//getter
				
		
		return statuscode;	
	}

	@Override
	public void setStatusCode(int status) {						//setter
		
		statuscode = status;
				
	}

	@Override
	public String getStatus() {
		
		String status_string="";
		
		switch (statuscode){
		
		case 200: 
			status_string = "200 ok";
			break;
		case 404:
			status_string = "404 not found";
			break;
		case 500:
			status_string = "500 internal server error";
			break;
	
		}
		
		
		return status_string.toUpperCase();
	}

	@Override
	public void addHeader(String header, String value) {
		map.put(header, value);
		
	}

	@Override
	public void setContent(String content) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setContent(byte[] content) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setContent(InputStream stream) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void send(OutputStream network) {
		// TODO Auto-generated method stub
		
	}

}
