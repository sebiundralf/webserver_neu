package mywebserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
	public static boolean debug = false; 

	HashMap<String, String> header; // in der Map werden die ganzen Header
									// gespeichert

	ArrayList<Character> AL = new ArrayList<Character>();
	boolean valid = true;

	public RequestImpl(InputStream arg0) {

		stream = arg0;

		if (stream == null) {
			valid = false;
		} else {

			boolean empty = true;
			int c;

			int j = 0;
			try {
				while ((c = stream.read()) != -1) { // solange der stream nicht
													// leer ist (leider ist er
													// nie leer

					if (c == '\n' || c == '\r') {
						j++;
					} else {

						j = 0;
					}

					empty = false;
					if (j < 3)
						AL.add((char) c); // werden die gelesenen chars der
											// arraylist hinzugefügt
					if(debug)
					 System.out.print((char) (c));  // (Gibt das einglesene Zeichen aus zum testen)

					if (j == 3)
						break; // sonst geht er nie aus der schleife, bzw.
								// kontent soll NICHT ausgelesen werden

				}

			} catch (IOException e) {

				e.printStackTrace();
			}

			if (empty)
				valid = false;

			char ch[] = new char[AL.size()];

			for (int i = 0; i < AL.size(); i++) { // inhalte der arraylist
													// werden in ein normales
													// array gespeichert
				ch[i] = AL.get(i);
			}

			requestStr = new String(ch); // wandelt ch-array in einen string um
			requestLines = requestStr.split("\n"); // jede zeile des requests
													// wird in dem neuen array
													// gespeichert
			header = new HashMap<String, String>();
			
			/*if(debug)
			 System.out.println(requestLines[0]);*/
			
			String head;
			String value;
			
			for (int i = 1; i < requestLines.length; i++) {
				String[] content = requestLines[i].split(": ");
				if (content.length >= 2) {
					
					head = content[0].toLowerCase().replaceAll("[\n\r]", "");
					value = content[1].replaceAll("[\n\r]", "");
					
					
					header.put(head.trim(), value.trim());

					/*if(debug)
					 System.out.println("Line" + i + ": " + head.trim() + ": " +
						value.trim()); // Testausgabe Header //
					 //System.out.println(header.get("user-agent"));*/
				 
				}
			}
	
		}
	}

	public RequestImpl() {
		this(null);
	}

	@Override
	public boolean isValid() {
		if (requestLines.length == 0)
			return false;

		String str[];

		str = requestLines[0].split(" ");

		if (str.length != 3) // ungültig wenn erste requestline nicht 3 strings
								// enthält
			return false;

		if (!this.getMethod().equals("GET") && !this.getMethod().equals("POST"))
			return false;

		return true;
	}

	@Override
	public String getMethod() {
		if (!valid)
			return "";

		if (requestLines.length != 0) // erste requestline mit " " splitten
			return (requestLines[0].split(" ")[0]).toUpperCase(); // erster
																	// substring
																	// = method

		return "";
	}

	@Override
	public Url getUrl() {
		if (!valid)
			return new UrlImpl();

		return new UrlImpl(requestLines[0].split(" ")[1]); // erste requestline
															// mit " " splitten
	} // zweiter substring = url

	@Override
	public Map<String, String> getHeaders() {

		return header;
	}

	@Override
	public int getContentLength() { // searches for line with content-len and
									// reads len
/*
		for (String str : requestLines) { // each line consists of pairs -->
			if (str.startsWith("Content-Length"))
				return Integer.valueOf((str.split(" ")[1])); // split string and
																// take value
																// (=second
																// string)
		}*/
		String s = null;
		if((!(s = header.get("content-length")).equals(""))){
			return Integer.valueOf(s);
		
		
		}
		//return 0;

		return 0;
	}

	@Override
	public String getContentType() { // siehe getContentLength()

		for (String str : requestLines) {
			if (str.startsWith("Content-Type"))
				return (str.split(" ")[1]);
		}

		return null;

	}

	@Override
	public InputStream getContentStream() {

		return stream;
	}
	
	
	/* Liest den Input-Stream des Übergebenen Request aus. Header schon ausgelesen, der Rest ist content;
	 * Wandelt den Content um und Liefert den fertigen String zurück
	 */
	
	public static String readPostContent(Request req){
		
		if(!req.getMethod().equals("POST"))
			return null;
		
		StringBuilder sb = new StringBuilder();
		int c;
		
		if(debug)
			System.out.println("Post content: ");
		
		try {
			for (int i = 0; i <= req.getContentLength(); i++) {

					c = req.getContentStream().read();
					sb.append((char) c);
					 
					if(debug)
					System.out.print((char) (c)); // (Gibt das einglesene
					// Zeichen aus
					// zum testen)

				}
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(debug)
			System.out.println("");
		
		String newContent = sb.toString();
		String [] ss;
		ss = newContent.split("=");
		if(ss.length>=2)
			newContent = ss[1];
		else
			newContent = "";
		

		try {
			newContent = URLDecoder.decode(newContent, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return newContent;
	}
	

}
