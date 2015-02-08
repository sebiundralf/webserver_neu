package mywebserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import java.io.BufferedWriter;

import BIF.SWE1.interfaces.Response;

public class ResponseImpl implements Response {

	private int statuscode = 200; //default statuscode
	String content;
	String contentType;
	
	
	HashMap<String, String> map = new HashMap<String, String>();
	
	
	@Override
	public Map<String, String> getHeaders() {
		
		return map;											//return map, created in addHeader()
	}

	@Override
	public int getContentLength() {
		// TODO Auto-generated method stub
		
		int i = 0;
		
		try {
			i = this.content.getBytes("UTF-8").length;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public String getContentType() {
		// TODO Auto-generated method stub
		return contentType;
	}

	@Override
	public void setContentType(String contentType) {
		this.contentType = contentType;
		return;
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
			status_string = "200 OK";
			break;
		case 404:
			status_string = "404 Not Found";
			break;
		case 500:
			status_string = "500 INTERNAL SERVER ERROR";
			break;
	
		}
		
		
		return status_string;
	}

	@Override
	public void addHeader(String header, String value) {
		map.put(header, value);
		
	}

	@Override
	public void setContent(String content) {
		this.content = content;
		return;
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
		
		StringBuilder responsetext = new StringBuilder();
		
		responsetext.append("HTTP/1.1 ");				//Erste Zeile vom Header
		responsetext.append(getStatus());
		responsetext.append("\n");
		
		if(!(map == null)){
			
	
			for (Iterator<Entry<String, String>> iterator = map.entrySet()
					.iterator(); iterator.hasNext();)    //Kein Plan was das ganze Zeug in der Forschleife zu bedeuten hat
														//Hat Eclipse selbst abgeändert
														//Jedenfalls soll damit jeder Eintrag aus der Hashmap ausgelesen werden
			
			{
				Entry<String, String> entry = iterator.next();    //<-- same
				
				
				Object key = entry.getKey();
				Object value = entry.getValue();
				
			 /* Hier werden Key und Value als neue Zeile hinzugefügt */
				responsetext.append(key);
				responsetext.append(": ");
				responsetext.append(value);
				responsetext.append("\n");
			}
			
		}
		
		responsetext.append("\n");
		responsetext.append(this.content);  //Hier noch der Content, brauchts späger denk ich
		
		
		String response = responsetext.toString();
		
		//System.out.println(response);
		

		
		
		try{
			Writer out = new BufferedWriter(new OutputStreamWriter(network,"UTF-8"));
		
		out.append(response);
		out.close();
	} catch (IOException e) {
		e.printStackTrace();
	}
		return;
	
	}

}
