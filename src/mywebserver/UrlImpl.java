package mywebserver;

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
    	url = raw;
    	
    }

	@Override
	public String getPath() {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getSegments() {
		// TODO Auto-generated method stub
		return null;
	}

}
