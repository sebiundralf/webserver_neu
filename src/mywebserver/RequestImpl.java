package mywebserver;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import BIF.SWE1.interfaces.Request;
import BIF.SWE1.interfaces.Url;

public class RequestImpl implements Request {
	
	InputStream stream;
	int length;
	String requestLines[];
	String requestStr;
	
	HashMap<String,String> header;  								//in der Map werden die ganzen Header gespeichert
	
	ArrayList<Character> AL = new ArrayList<Character>();
	boolean valid=true;
	
	public RequestImpl(InputStream arg0){
		stream = arg0;
		
		if(stream == null){
			valid = false;
		}else{
			
			boolean empty = true;
			int c;
			
			try {
				while((c=stream.read()) != -1){						//solange der stream nicht leer ist
																	
					empty = false;
					AL.add((char) c);								//werden die gelesenen chars der arraylist hinzugefügt
					
				}
					
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
			if(empty)
				valid = false;
			
			char ch[] = new char[AL.size()];
			
			for(int i = 0; i < AL.size(); i++){						//inhalte der arraylist werden in ein normales array gespeichert
				ch[i] = AL.get(i);
			}
			
			requestStr = new String(ch);							//wandelt ch-array in einen string um
			requestLines = requestStr.split("\n");					//jede zeile des requests wird in dem neuen array gespeichert
			
			
			header = new HashMap<String,String>();
			
			for(int i = 1; i < requestLines.length-1; i++){
				String[] content = requestLines[i].split(": ");
				if(content.length>=2){
					header.put(content[0].toLowerCase(),content[1]);
				/*	 
				   System.out.println("Line" + i + ": " + content[0] + " " + content[1]);   // Testausgabe Header
					 System.out.println(header.get("user-agent"));
					*/ 
					 
				}		
			}
			
			for(int b = 0; b < requestLines.length; b++) 			//testausgabe der requestlines
				 System.out.println(requestLines[b]); 				//
			
		}
	}
	

	
	
	public RequestImpl(){
		this(null);
	}
	
	@Override
	public boolean isValid() {
		if(requestLines.length == 0)
			return false;

	String str[];
	
	str = requestLines[0].split(" ");
	
	if(str.length!=3)												//ungültig wenn header nicht 3 strings enthält
		return false;
	
	if(!this.getMethod().equals("GET") && !this.getMethod().equals("POST"))
		return false;
	
	return true;
	}
	
	

	@Override
	public String getMethod() {
		if(!valid)											
			return "";
		
		if(requestLines.length != 0)									//erste requestline mit " " splitten
			return (requestLines[0].split(" ")[0]).toUpperCase();		//erster substring = method
		
		return "";
	}

	@Override
	public Url getUrl() {
		if(!valid)
			return new UrlImpl();
		
		return new UrlImpl(requestLines[0].split(" ")[1]);				//erste requestline mit " " splitten
	}																	//zweiter substring = url

	@Override
	public Map<String, String> getHeaders() {

		return header;
	}

	@Override
	public int getContentLength() {								//searches for line with content-len and reads len
		
		for(String str : requestLines){							//each line consists of pairs --> 
			if(str.startsWith("Content-Length"))
				return Integer.valueOf((str.split(" ")[1]));	//split string and take value (=second string)
		}
		
		return 0;
	}

	@Override
	public String getContentType() {							//siehe content getContentLength()

		for(String str : requestLines){
			if(str.startsWith("Content-Type"))
				return (str.split(" ")[1]);
		}
		
		return null;
		
	}

	@Override
	public InputStream getContentStream() {
	
		return stream;
	}

	
}
