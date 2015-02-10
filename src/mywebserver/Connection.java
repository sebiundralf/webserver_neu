package mywebserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import BIF.SWE1.interfaces.Request;
import BIF.SWE1.interfaces.Response;


public class Connection implements Runnable{

	
	static int connections;
	Socket socket;
	Request req;
	Response resp;
	
	
	
	public Connection(Socket socket){
		
	
		this.socket  = socket;
		connections++;
		System.out.println("Connection: " + connections);
		run();
		
		
	}
	
	
	
	@Override
	public void run(){
		
		
		
		//System.out.println("in Connection");
		
		try {
			req = new RequestImpl(socket.getInputStream());
			//System.out.println("Neuer Request angelegt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
	
		
		if(req.isValid()){
			//System.out.println("request is valid");
			TestPluginImpl tp = new TestPluginImpl();
		
			if(tp.canHandle(req)>0){
				//System.out.println("Can handle request");

				resp = tp.handle(req);
				try {
					resp.send(socket.getOutputStream());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}else {
			
			}
	
		}
		/*BufferedReader in;
	
			try {
				in = new BufferedReader( 
						new InputStreamReader(socket.getInputStream()));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return;
			}

		String line;
		try {
			while((line = in.readLine()) != null) {
					System.out.println(line);
			
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		*/
		
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}




	
	
}
