package uebungen;

import java.io.InputStream;
import java.util.Date;

import mywebserver.NaviPlugin;
import mywebserver.PluginManagerImpl;
import mywebserver.RequestImpl;
import mywebserver.TemperaturePlugin;
import mywebserver.ToLowerPlugin;
import BIF.SWE1.interfaces.Plugin;
import BIF.SWE1.interfaces.PluginManager;
import BIF.SWE1.interfaces.Request;
import BIF.SWE1.interfaces.UEB6;

public class UEB6Impl implements UEB6 {

	@Override
	public void helloWorld() {
	}

	@Override
	public PluginManager getPluginManager() {
		// TODO Auto-generated method stub
		return new PluginManagerImpl();
	}

	@Override
	public Request getRequest(InputStream arg0) {
		// TODO Auto-generated method stub
		return new RequestImpl(arg0);
	}

	@Override
	public String getNaviUrl() {
		// TODO Auto-generated method stub
		return "/navi.html";
	}

	@Override
	public Plugin getNavigationPlugin() {
		// TODO Auto-generated method stub
		return new NaviPlugin();
	}

	@Override
	public Plugin getTemperaturePlugin() {
		// TODO Auto-generated method stub
		return new TemperaturePlugin();
	}

	@Override
	public String getTemperatureRestUrl(Date arg0, Date arg1) {
		// TODO Auto-generated method stub
		return "/GetTemperature";
	}

	@Override
	public String getTemperatureUrl(Date arg0, Date arg1) {
		// TODO Auto-generated method stub
		return "/Temperature.html";
	}

	@Override
	public Plugin getToLowerPlugin() {
		// TODO Auto-generated method stub
		return new ToLowerPlugin();
	}

	@Override
	public String getToLowerUrl() {
		// TODO Auto-generated method stub
		return "/toLower.html";
		//return null;
	}
}
