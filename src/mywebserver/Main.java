package mywebserver;

public class Main {
	public static void main(String[] args) {
		
		Server Server = new Server(Integer.parseInt(args[0]));
		
		Server.run();
		
	}
}

