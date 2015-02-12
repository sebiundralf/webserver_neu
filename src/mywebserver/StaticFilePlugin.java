package mywebserver;

import java.io.File;

import uebungen.UEB5Impl;
import BIF.SWE1.interfaces.Plugin;
import BIF.SWE1.interfaces.Request;
import BIF.SWE1.interfaces.Response;

public class StaticFilePlugin implements Plugin{

	@Override
	public float canHandle(Request req) {
		if(new File(UEB5Impl.fileurl).exists() == true)						//siehe UEB5Impl
			return 1;
		
		return 0;
	}

	@Override
	public Response handle(Request req) {
		
		return null;
	}

}
