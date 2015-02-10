package mywebserver;

public class Server {
	public void run() {
		// TODO Auto-generated method stub
		
		
	System.out.println("Waiting for connections..");
		
		ServerSocket listener;
		
		try {
			listener = new ServerSocket(1338);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Nix gangen");
			return;
		}
	

	
		Socket s;
		try {
			s = listener.accept();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}
		
		BufferedReader in;
	
			try {
				in = new BufferedReader( 
						new InputStreamReader(s.getInputStream()));
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
		
		

		
		try {
			listener.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

	}
}
