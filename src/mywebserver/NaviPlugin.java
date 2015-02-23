package mywebserver;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import BIF.SWE1.interfaces.Plugin;
import BIF.SWE1.interfaces.Request;
import BIF.SWE1.interfaces.Response;

public class NaviPlugin implements Plugin {

	public static boolean set = false;
	public static boolean readIn = false;
	public static boolean readIn2 = false;


	@Override
	public float canHandle(Request req) {
		String s = req.getUrl().getPath();


		if (s.toUpperCase().equals("/NAVI.HTML")
				|| s.toUpperCase().equals("/NAVI"))
			return 0.9f;

		return 0;
	}

	
	@Override
	public Response handle(Request req) {

		Response resp;
		String street = null;
		
		if(req.getMethod().equalsIgnoreCase("POST"))
			street = RequestImpl.readPostContent(req).trim();
		
		/*if(street!=null)
			System.out.println(street);*/

		if (readIn == true) {
			resp = naviError(req, 1);

			return resp;
		}
		
		if ("readinfile517517".equals(street)){
			
				boolean exit = false;
				Connection.lock.lock();
				if(NaviPlugin.readIn2)
					exit = true;
				else
					NaviPlugin.readIn=true;
				Connection.lock.unlock();
				
				if(!exit){
			    NaviPlugin.readIn2 = true;
			    Navi nv = new Navi();
			    nv.start();
				}
				
			    resp = naviError(req, 1);			
		
			return resp;
		}
		if (set == false) {

			resp = naviError(req, 2);

			return resp;

		}

		if (!req.getMethod().equals("POST")) {
			resp = naviError(req, 0);

			return resp;
		}


		
		resp = handleReq(req, street);

		return resp;
	}

	private Response handleReq(Request req, String street) {
		Response resp = new ResponseImpl();
		
		//solange nicht anderst definiert, kommt die Normale Seite bei Response raus
		resp = naviError(req,0);

		System.out.println("Street: " + street);
		System.out.println("Handle");
		
		Navi nv = new Navi();
		
		String[] places = nv.find(street);
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("<p> The street \"" + street + "\" is found in the following cities: <br /> <ul>");
		
		for(int i = 0; i < places.length; i++){
			
			sb.append("<li> " + places[i] + "</li>");
			
		}
		
		sb.append("</p>");
		sb.append("<form method=\"GET\" action=\"navi.html\">");
		sb.append("<input type=\"submit\" value=\" Look up another Street \"></form> <br /> ");
		
		resp.setContent(getPageAlterContent(sb.toString()));
		
		return resp;
	}

	private Response naviError(Request req, int errNo) {

		/*
		 * Errno: 
		 * 0= Normaler Aufruf der Seite 
		 * 1= Die Karte wird gerade eingelesen 
		 * 2= Die Karte wurde noch nicht eingelesen
		 */
		
		Response resp = new ResponseImpl();

		String content = null;

		switch (errNo) {

		case 0:
			content = "<form action=\"navi.html\" method=\"POST\"> \n"
					+ "<p>Streetname: \n"
					+ "<br /> "
					+ "<textarea  name=\"rawtext\" type=\"text\" size=\"30\" maxlength=\"30\"></textarea> "
					+ "</p> " + "<input type=\"submit\" value=\" Absenden \"> "
					+ "</form> " + "<br /> ";
			break;

		case 1:
			content = // "<img src=\"processing.gif\" alt=\"loading image\" /> "
						// +
			"<p> System reads in Map file. Please refresh the page manually </p>";
			break;

		case 2:
			content = "<br /><p> to use the Navi Plugin you must read in the Map first! </p>";
			break;

		default:
			content = "<p>Server Error! <br /> Please contact an Admin! </p>";
		}

		content = getPageAlterContent(content);


		resp.setContent(content);

		return resp;
	}

	private String getPageAlterContent(String newContent) {

		String newPage = null;
		StringBuilder content = new StringBuilder();
		CharSequence c;
		FileInputStream readFile = null;
		BufferedReader readBuf;

		String file = null;
		String dir = System.getProperty("user.dir"); // + "/..";

		String dirFolder[];

		dirFolder = dir.split("\\\\");

		// System.out.println(dir);
		// System.out.println(dirFolder[dirFolder.length-1]);

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
		// System.out.println(dir);
		file = dir + "\\test\\navi.html";

		try {
			readFile = new FileInputStream(file);
			readBuf = new BufferedReader(new InputStreamReader(readFile));
			while ((c = readBuf.readLine()) != null)
				content.append(c);

			readFile.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int i = content.indexOf("id=\"navicont\"");

		if (i == 0)
			return "Error";

		i = content.indexOf(">", i);

		newPage = content.substring(0, i + 1) + newContent
				+ content.substring(i + 1);

		return newPage;
	}
}
