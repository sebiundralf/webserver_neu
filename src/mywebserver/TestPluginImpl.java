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
		
		String s = req.getUrl().getPath().toUpperCase();
		
		if(s.equals("/TEST/FOO.HTML"))
			return 0.2f;
		else if(s.equals("/") || 
				s.equals("/INDEX.HTML") )
			return 0.2f;
		else if(s.toUpperCase().equals("/TOLOWER.HTML") || 
				s.toUpperCase().equals("/TOLOWER") )
			return 0.2f;
		
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
		String dir = System.getProperty("user.dir"); // + "/..";
		
		String dirFolder[];
		
		dirFolder = dir.split("\\\\");
		
	//	System.out.println(dir);
	//	System.out.println(dirFolder[dirFolder.length-1]);
		
		/* Bei den Unitests sucht er immer im deploy ordner statt im root ordner, deshalb wird dieser entfernt */
		if(dirFolder[dirFolder.length-1].equals("deploy")){
			
			StringBuilder newPath = new StringBuilder();
			newPath.append(dirFolder[0]);
			
			for(int i = 1; i < dirFolder.length-1; i++)
				newPath.append("\\" + dirFolder[i] );
			
			dir = newPath.toString();
			
		}
		//System.out.println(dir);
		

		
		if(path.equals("/test/foo.html")){
			
			file = dir + "\\test\\foo.html";
			//System.out.println("file: " + file);
			
		}else if(path.equals("/") ) {
			file = dir + "\\test\\index.html";
		}
		
		else{
			file = dir + "\\test" + path.replace("/", "\\");
			//System.out.println("file: " + file);
		}
				
	//	System.out.println("user.dir: " + dir);
	//	System.out.println("path: " + path);
		
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
