package mywebserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


public class Connection implements Runnable{

	

	Socket socket;
	
	public Connection(Socket socket){
		
	
		this.socket  = socket;
		run();
		
	}
	
	
	
	@Override
	public void run(){
		

		BufferedReader in;
	
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
		
		
		
		
		
	}




	
	
}
