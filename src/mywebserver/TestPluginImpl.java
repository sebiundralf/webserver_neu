package mywebserver;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import BIF.SWE1.interfaces.Plugin;
import BIF.SWE1.interfaces.Request;
import BIF.SWE1.interfaces.Response;

public class TestPluginImpl implements Plugin{

	@Override
	public float canHandle(Request req) {
		
		if(req.getUrl().getRawUrl().equals("/test/foo.html"))
			return 0.9f;
		else if(req.getUrl().getRawUrl().equals("/"))
			return 0.8f;
		else 
			return 0.0f;
		
		
	
	}

	@Override
	public Response handle(Request req) {
		ResponseImpl resp = new ResponseImpl();
		StringBuilder content = new StringBuilder();
		CharSequence c;
		FileInputStream readFile = null;
		BufferedReader readBuf;
		
		String file = null;
		String path = req.getUrl().getPath();
		String dir = System.getProperty("user.dir") + "/..";
		
		if(path.equals("/test/foo.html")){
			
			file = dir + "\\test\\foo.html";
			System.out.println("file: " + file);
			
		}else if (path.equals("/")) {
			file = dir + "\\test\\index.html";
			System.out.println("file: " + file);
		}
			
		
		System.out.println("user.dir: " + dir);
		System.out.println("path: " + path);
		
		
		try {
			readFile = new FileInputStream(file);
			readBuf = new BufferedReader(new InputStreamReader(readFile));
			while((c = readBuf.readLine()) != null)
				content.append(c);
			
			readFile.close();
			resp.setContent(content.toString());
			resp.setStatusCode(200);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		return resp;
	}

}
