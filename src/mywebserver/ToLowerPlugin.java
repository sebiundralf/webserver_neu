package mywebserver;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;



import BIF.SWE1.interfaces.Plugin;
import BIF.SWE1.interfaces.Request;
import BIF.SWE1.interfaces.Response;

public class ToLowerPlugin implements Plugin {

	@Override
	public float canHandle(Request req) {
		String s = req.getUrl().getPath();

		if ((s.toUpperCase().equals("/TOLOWER.HTML") || s.toUpperCase().equals(
				"/TOLOWER"))
				&& req.getMethod().equals("POST"))
			return 0.9f;

		return 0;
	}

	public Response handle(Request req) {

		/* Content Auslesen */
		
		String newContent = RequestImpl.readPostContent(req);
		

		ResponseImpl resp = new ResponseImpl();
		String file = null;

		String dir = System.getProperty("user.dir");
		FileInputStream readFile = null;
		BufferedReader readBuf;
		

			StringBuilder content2 = new StringBuilder();
			CharSequence cs;

			String dirFolder[];

			dirFolder = dir.split("\\\\");

			/*
			 * Bei den Unitests sucht er immer im deploy ordner statt im root
			 * ordner, deshalb wird dieser entfernt
			 */
			if (dirFolder[dirFolder.length - 1].equals("deploy")) {

				StringBuilder newPath = new StringBuilder();
				newPath.append(dirFolder[0]);

				for (int i = 1; i < dirFolder.length - 1; i++)
					newPath.append("\\" + dirFolder[i]);

				dir = newPath.toString();

			}

		

		file = dir + "\\test\\toLower.html";

		try {
			readFile = new FileInputStream(file);
			readBuf = new BufferedReader(new InputStreamReader(readFile));
			while ((cs = readBuf.readLine()) != null)
				content2.append(cs);

			readFile.close();


		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



		// System.out.println("\nNew Content: " + newContent);


		// resp.setContent(newContent.toLowerCase());

	
		String[] splitString;
		
		splitString = content2.toString().split("<pre>");
		
		if(splitString.length >= 2)
		resp.setContent(splitString[0] + "<pre>" + newContent.toLowerCase() + splitString[1]);
		else
		{
			resp.setStatusCode(500);
			  resp.setContent("<html><head><title>Could not handle request</title></head>"
			  		+ "<body><br /><h1>Error</h><p> Server can not compute the folowing imput: \"" 
					  + newContent + "\"! <br /> </p> </body> </html>");
			
			
		}

		return resp;
	}

}
