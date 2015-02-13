package mywebserver;

import java.io.IOException;
import java.net.Socket;

import BIF.SWE1.interfaces.Plugin;
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
			
			PluginManagerImpl pm = new  PluginManagerImpl();
			Plugin p = pm.selectPlugin(req);
			
			
			if (p!=null) {
				
				resp = p.handle(req);
				try {
					resp.send(socket.getOutputStream());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {
				
			  resp = new ResponseImpl();
			  resp.setStatusCode(404);
			  resp.setContent("<html><head><title>Site not foud</title></head>"
			  		+ "<body><br /><h1>Error</h><p> Server can not find the requested page \"" 
					  + req.getUrl().getPath() + "\"! <br /> </p> </body> </html>");
			  try {
				resp.send(socket.getOutputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

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
