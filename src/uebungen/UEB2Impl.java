package uebungen;

import mywebserver.UrlImpl;
import BIF.SWE1.interfaces.UEB2;
import BIF.SWE1.interfaces.Url;

public class UEB2Impl implements UEB2 {

	@Override
	public Url GetUrl(String path) {
		return new UrlImpl(path);
	}

	@Override
	public void HelloWorld() {
	}
}
