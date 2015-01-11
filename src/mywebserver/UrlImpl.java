package mywebserver;

import java.util.HashMap;
import java.util.Map;

import BIF.SWE1.interfaces.Url;

public class UrlImpl implements Url {

	private String url;

	public UrlImpl()
	{
		url = "";
	}

	public UrlImpl(String raw)
	{
		if(raw != null)
			url = raw;
		else
			url="";
	}

	@Override
	public String getPath() {

		if(url.equals(""))
			return "";


		int index;

		if(url.contains("/")){
			index = url.indexOf("/");								//startindex des strings, 0 würde auch gehen

		if(url.contains("#"))	
			return url.substring(index, url.indexOf("#"));			//für ueb2 url_should_create_with_path_fragment()

		if(url.contains("?"))
			return url.substring(index, url.indexOf("?"));			// für ueb1 url_should_parse_return_path_without_parameter
		else														//
			return url.substring(index);							//

		}

		return url;
	}

	@Override
	public String getRawUrl() {
		// TODO Auto-generated method stub
		return url;
	}

	@Override
	public String geExtension() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String geFragment() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFileName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> getParameter() {
		HashMap<String, String> map = new HashMap<String, String>();		//keine ahnung warum hashmap, mit normaler map gehts nicht

		String[] params;

		int index = url.indexOf("?");


		String subparam = url.substring(index+1);					//subparam ist jetzt " x=1 " oder " x=1&y=2 "

		params = subparam.split("&");								//params[0] = x=1 , params[1] = y=2


		for(String str : params){									//for each str in params
			int i = str.indexOf("=");								//i = 1
			String val = str.substring(i+1);						//val = 1 (für x) oder val = 2 (für y)
			String param = str.substring(0,i);						//param = x  oder param = y
			map.put(param, val);									
		}

		return map;
	}

	@Override
	public String[] getSegments() {
		// TODO Auto-generated method stub
		return null;
	}

}
