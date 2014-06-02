package be.post.web.httpclient;

import static org.junit.Assert.*;

import org.junit.Test;

import com.google.appengine.repackaged.org.apache.http.HttpEntity;
import com.google.appengine.repackaged.org.apache.http.HttpResponse;
import com.google.appengine.repackaged.org.apache.http.client.methods.HttpGet;
import com.google.appengine.repackaged.org.apache.http.conn.scheme.PlainSocketFactory;
import com.google.appengine.repackaged.org.apache.http.conn.scheme.Scheme;
import com.google.appengine.repackaged.org.apache.http.conn.scheme.SchemeRegistry;
import com.google.appengine.repackaged.org.apache.http.entity.BufferedHttpEntity;
import com.google.appengine.repackaged.org.apache.http.impl.client.DefaultHttpClient;
import com.google.appengine.repackaged.org.apache.http.impl.conn.SingleClientConnManager;
import com.google.appengine.repackaged.org.apache.http.params.BasicHttpParams;
import com.google.appengine.repackaged.org.apache.http.util.EntityUtils;

public class HttpClientGAETest {

	@Test
	public void test() {
		String urlString = "http://google.be/";
		//String urlString = "http://csp-dv2.netpost/";

	    SchemeRegistry schemeRegistry = new SchemeRegistry(); 
	    schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80)); 
	    BasicHttpParams params = new BasicHttpParams(); 
	    SingleClientConnManager connmgr = new SingleClientConnManager(params, schemeRegistry); 
	    DefaultHttpClient client = new DefaultHttpClient(connmgr, params); 


	    HttpGet get = new HttpGet(urlString);
	    byte[] buf = null;
	    try {   
	        HttpResponse resp = client.execute(get);
	        HttpEntity entity = resp.getEntity();
			if (entity != null) {
			    entity = new BufferedHttpEntity(entity);
			   System.out.println(EntityUtils.toString(entity));
			}
	    } catch (Exception e) {
	        System.out.println("There was a problem.");
	        e.printStackTrace();
	    }
	    System.out.println( buf);
	}
	
	
	@Test
	public void getUrlFetch() throws Exception {
		System.out.println(GetUrlContent.getUrlContent1("http://csp-dv2.netpost/"));
	}

}
