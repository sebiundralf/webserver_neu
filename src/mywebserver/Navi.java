package mywebserver;

public class Navi implements Runnable {

	public static boolean thing;
	private Thread t;
	public static String data;
	
	
	public Navi(){
		
		
	}
	
	public void readInFile(){
		/* Do Stuff */
		
		for(int i = 5; i > 0 ; i--){
			
			System.out.println("I will wake up in : " + i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		NaviPlugin.readIn = false;
		NaviPlugin.readIn2= false;
		NaviPlugin.set = true;
			
	return;
		
	}
	

	public void start()
	{
	      if (t == null)
	      {
	         t = new Thread (this);
	         t.start ();
	      }
		
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		
		readInFile();
		
	}
	
	public String[] find(String street){
		
		String [] places = new String[2];
		
		places[0] = "Bludenz";
		places[1] = "Wien";
		
		
		
		return places;
	}
}
