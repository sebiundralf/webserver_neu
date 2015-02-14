package mywebserver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


import BIF.SWE1.interfaces.Plugin;
import BIF.SWE1.interfaces.Request;
import BIF.SWE1.interfaces.Response;
import BIF.SWE1.interfaces.Url;

public class StaticFilePlugin implements Plugin {

	@Override
	public float canHandle(Request req) {

		Url vUrl = req.getUrl();

		// System.out.println("PFAD: " +
		// System.getProperty("user.dir")+"\\"+vUrl.getPath());
		// System.out.println("vURL: "+ vUrl.getPath());

		if (new File(vUrl.getPath()).exists() == true) // siehe UEB5Impl
			return 0.1f;
		
		if(vUrl.getPath().equals("/load_static"))
			return 0.1f;

		return 0;
	}

	@Override
	public Response handle(Request req) {

		ResponseImpl resp = new ResponseImpl();
	
		FileInputStream fs = null;
		byte[] data = null;
		String filename;
		File file;
		if(req.getUrl().getPath().equals("/load_static"))
			 filename = RequestImpl.readPostContent(req);
		else
		 filename = req.getUrl().getPath();
		
		if(RequestImpl.debug)
		System.out.println("Static file Filename: " + filename);

			
		 file = new File(filename);
		
		if(!file.exists()){
			 resp.setContent("<html><head><title>File not foud</title></head>"
				  		+ "<body><br /><h1>Error 404</h1><p> Server can not find the requested file \"" 
						  + filename + "\"! <br /> </p>"
						  		+ " <a href=\"/static.html\"> go back to static file input page </a> <br /> "
						  		+ " <a href=\"/index.html\"> go back to main page </a> </body> </html>");
			
			return resp;
		}

		try {
			try {
				fs = new FileInputStream(file);
				data = new byte[(int)file.length()];
				fs.read(data); // Bytearray mit kodierten Daten
			} finally {
				if (fs != null)
					fs.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		resp.setContent(data);
	//	System.out.println("CONTENT: " + resp.content);
		
		
		
			
		
		
		return resp;
	}

}
