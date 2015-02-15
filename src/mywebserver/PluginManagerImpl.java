package mywebserver;

import java.util.ArrayList;
import java.util.List;

import BIF.SWE1.interfaces.Plugin;
import BIF.SWE1.interfaces.PluginManager;
import BIF.SWE1.interfaces.Request;

public class PluginManagerImpl implements PluginManager{

	List <Plugin> list;
	
	
	public PluginManagerImpl(){
		list = new ArrayList<Plugin>();
		
		list.add(new TestPluginImpl());
		list.add(new StaticFilePlugin());
		list.add(new ToLowerPlugin());
		list.add(new TemperaturePlugin());
		list.add(new NaviPlugin());
	}
	
	
	@Override
	public Iterable<Plugin> getPlugins() {
		
		return list;
		
	}
	
	public Plugin selectPlugin(Request req) {
		
		if(req.getUrl().getPath().toLowerCase().equals("/debug=on")){
			RequestImpl.debug = true;
			
			return null;
		}
			
		if(req.getUrl().getPath().toLowerCase().equals("/debug=off")){
			RequestImpl.debug = false;
		
			return null;
		}
		
		if(req.getUrl().getPath().toLowerCase().equals("/close")){
			Server.closeServer = true;
		
			return null;
		}
			
			
		Plugin plugin = null;
		float max = 0;
		for (Plugin p : getPlugins()) {
			float canHandle = p.canHandle(req);
			if (canHandle > max) {
                max = canHandle;
				plugin = p;
			}
		}

		return plugin;
	}

}
