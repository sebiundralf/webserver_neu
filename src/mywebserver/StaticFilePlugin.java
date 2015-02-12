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
			return 0.7f;

		return 0;
	}

	@Override
	public Response handle(Request req) {

		ResponseImpl resp = new ResponseImpl();
	
		FileInputStream fs = null;
		byte[] data = null;
		 File file = new File(req.getUrl().getPath());


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
