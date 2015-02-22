package mywebserver;

import BIF.SWE1.interfaces.Plugin;
import BIF.SWE1.interfaces.Request;
import BIF.SWE1.interfaces.Response;

public class TemperaturePlugin implements Plugin{

	
	private static final String DB_URL = "jdbc:sqlserver://localhost;DataBaseName=SWE;user=test;password=hallo;";
	
	boolean dateFlag = true;
	String date, temp1, temp2, temp3, selectSQL, url, part1, year, month, day = null;
	
	
	@Override
	public float canHandle(Request req) {
		if (req.getMethod().equals("POST")) {
			System.out.println("POST url: " + req.getUrl().getPath());
			if (req.getUrl().getPath() ==  "/Temperature") 
				return 0.5f;
			
			return 0.0f;
		}
		 
		if(req.getMethod().equals("GET")){
			
			String url = req.getUrl().getRawUrl();
			
			System.out.println("GET url: " + url);
			
			if(url.equals("/Temperature")){
				dateFlag = false;
				return 0.6f;
			}/*
			else if(url.contains("/Temperature")){
				String[] parts = url.split("/");
				part1 = parts[1];
				year = parts[2];
				month = parts[3];
				day = parts[4];
				
				
				for(int i = 0; i<parts.length; i++){
					System.out.println("parts["+i+"]: " + parts[i] );
				}
				return 1.0f;
			} else {
				return 0.0f; 
			}*/
		}
		
		
		return 0f;
	}

	@Override
	public Response handle(Request req) {
		
		ResponseImpl resp = new ResponseImpl();
		String rspHold = " ";
		resp.setContent(rspHold);
		return resp;
	}

}
