package mywebserver;

import java.io.IOException;
import java.net.Socket;

import BIF.SWE1.interfaces.Request;
import BIF.SWE1.interfaces.Response;

public class Connection implements Runnable {

	static int connections;
	Socket socket;
	Request req;
	Response resp;

	public Connection(Socket socket) {

		this.socket = socket;
		connections++;
		System.out.println("Connection: " + connections);
		run();

	}

	@Override
	public void run() {

		try {
			req = new RequestImpl(socket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}

		if (req.isValid()) {
			
			TestPluginImpl tp = new TestPluginImpl();
			if (tp.canHandle(req) > 0) {
				
				resp = tp.handle(req);
				try {
					resp.send(socket.getOutputStream());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {

			}

		}

		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
