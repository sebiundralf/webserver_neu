package mywebserver;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import BIF.SWE1.interfaces.Plugin;
import BIF.SWE1.interfaces.Request;
import BIF.SWE1.interfaces.Response;

public class Connection implements Runnable {

	static int connections;

	Socket socket;
	Request req;
	Response resp;
	public static Lock lock;
	private Thread t;
	
	public Connection(Socket socket) {

		this.socket = socket;
		connections++;

		System.out.println("Connection: " + connections);
		lock = new ReentrantLock();
	

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
			  		+ "<body><br /><h1>Error 404</h1><p> Server can not find the requested page \"" 
					  + req.getUrl().getPath() + "\"! <br /> </p> <a href=\"/index.html\"> go back to main page </a> </body> </html>");
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

	public void start()
	{
	      if (t == null)
	      {
	         t = new Thread (this);
	         t.start ();
	      }
		
		
	}

}
