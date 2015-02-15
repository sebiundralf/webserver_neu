package mywebserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	int port;
	static boolean closeServer = false;

	public Server(int port) {

		this.port = port;

	}

	public void run() {
		// TODO Auto-generated method stub

		ServerSocket listener;

		try {
			listener = new ServerSocket(port);    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Nix gangen");
			return;
		}

		System.out.println("Waiting for connections..");

		while (!closeServer) {
			
			Socket cSocket;
			try {
				cSocket = listener.accept();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return;
			}			
			new Thread(new Connection(cSocket));
		}
		
		try {
			listener.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Server closed.");

	}
}
