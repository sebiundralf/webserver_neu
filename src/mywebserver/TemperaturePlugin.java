package mywebserver;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import BIF.SWE1.interfaces.Plugin;
import BIF.SWE1.interfaces.Request;
import BIF.SWE1.interfaces.Response;

public class TemperaturePlugin implements Plugin{

	
	private static final String DB_URL = "jdbc:sqlserver://localhost;DataBaseName=swe;user=swe;password=hallo;";
	
	boolean dateFlag = false;
	String date, temp1, temp2, temp3, selectStmt, url, year, month, day = null;
	
	
	@Override
	public float canHandle(Request req) {
			
		String s = req.getUrl().getPath();


		if (s.toUpperCase().equals("/TEMPERATURE.HTML"))
			return 0.9f;
		 
		
		 
		if(req.getMethod().equals("GET")){
			
			String url = req.getUrl().getRawUrl();
			
			if(url.equals("/GetTemperature")){
				return 0.8f;
				
			} else if(url.contains("/GetTemperature")){
				
				dateFlag = true;
				String[] parts = url.split("/");
				year = parts[2];
				month = parts[3];
				day = parts[4];
				
				/*
				for(int i = 0; i<parts.length; i++){
					System.out.println("parts["+i+"]: " + parts[i] );
				}
				*/
				
			/*	System.out.println("year: " + year);
				System.out.println("month: " + month);
				System.out.println("day: " + day);
			*/
				return 0.8f;
			} else 
				return 0.0f; 
			
		
		}  
		
		
		return 0.0f;
	}

	@Override
	public Response handle(Request req) {

		ResponseImpl resp = new ResponseImpl();
		String file = null;

		String dir = System.getProperty("user.dir");
		FileInputStream readFile = null;
		BufferedReader readBuf;

		StringBuilder newContent = new StringBuilder(); 
		StringBuilder content = new StringBuilder();
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



		file = dir + "\\test\\temperature.html";

		try {
			readFile = new FileInputStream(file);
			readBuf = new BufferedReader(new InputStreamReader(readFile));
			while ((cs = readBuf.readLine()) != null)
				content.append(cs);

			readFile.close();


		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		String sqlDate = year + "-" + month + "-" + day;


		if(!dateFlag)
			selectStmt = "SELECT * FROM TEMPERATURES ORDER BY DATE";
		else 
			selectStmt= "SELECT * FROM TEMPERATURES WHERE DATE = ?";
		

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}


		try {
			Connection db = DriverManager.getConnection(DB_URL);
			PreparedStatement St = db.prepareStatement(selectStmt);
			
			if (dateFlag)
				St.setString(1, sqlDate);
			
			ResultSet rs = St.executeQuery();
			
			
			
			int i = 0;
			
			while(rs.next()){
				date = rs.getString("Date");
				temp1 = rs.getString("Temp1");
				temp2 = rs.getString("Temp2");
				temp3 = rs.getString("Temp3");

				if(!dateFlag){
					if(i==0){
						newContent.append("<table class = table>");
						newContent.append("<tr>");
						newContent.append("<th>Date</th>");
						newContent.append("<th>Temperature1</th>");
						newContent.append("<th>Temperature2</th>");
						newContent.append("<th>Temperature3</th>");
						newContent.append("</tr>");
					}
					newContent.append("<tr>");

					newContent.append("<td class=td>"+ date + "</td>");
					newContent.append("<td class=td>"+ temp1 + "</td>");
					newContent.append("<td class=td>"+ temp2 + "</td>");
					newContent.append("<td class=td>"+ temp3 + "</td>");

					newContent.append("</tr>");
					i++;
				} else {
					if (i==0){
						newContent.append("<?xml version=\"1.0\"?>");
						newContent.append("<Temperaturdaten>");
						i++;
					}
					newContent.append("<Temperaturen>");
					newContent.append("<date>" + date + "</date>");
					newContent.append("<temp1>" + temp1 + "</temp1>");
					newContent.append("<temp2>" + temp2 + "</temp2>");
					newContent.append("<temp3>" + temp3 + "</temp3>");
					newContent.append("</Temperaturen>");
				}
			}
			
			if(!dateFlag)
				newContent.append("</table>");
			else
				newContent.append("</Temperaturdaten>");
			
			
			
		} catch (SQLException e) { 
			
			e.printStackTrace();
		}
		
		
		if(!dateFlag){
			String[] split = content.toString().split("<div id=\"table\">");
			resp.setContent(split[0] + "<div id =\"table\">" + newContent.toString() + split[1]);
		} else {
			if(newContent.toString().equals("</Temperaturdaten>"))
				resp.setContent("Es wurden keine Temperaturdaten gefunden!");
			else
				resp.setContent(newContent.toString());
		
		}
		
		
		// resp.setContent(content.toString().toLowerCase());
		
		 
		return resp;
	}

}
