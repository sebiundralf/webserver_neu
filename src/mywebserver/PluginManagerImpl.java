package mywebserver;

import java.util.List;

import BIF.SWE1.interfaces.Plugin;
import BIF.SWE1.interfaces.PluginManager;

public class PluginManagerImpl implements PluginManager{

	List <Plugin> list;
	
	@Override
	public Iterable<Plugin> getPlugins() {
		// TODO Auto-generated method stub
		return list;
	}

}
