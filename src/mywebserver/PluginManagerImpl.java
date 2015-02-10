package mywebserver;

import java.util.ArrayList;
import java.util.List;

import BIF.SWE1.interfaces.Plugin;
import BIF.SWE1.interfaces.PluginManager;

public class PluginManagerImpl implements PluginManager{

	List <Plugin> list;
	public PluginManagerImpl(){
		list = new ArrayList<Plugin>();
		list.add(new TestPluginImpl());
		
	}
	@Override
	public Iterable<Plugin> getPlugins() {
		// TODO Auto-generated method stub
		
		return list;
	}

}
