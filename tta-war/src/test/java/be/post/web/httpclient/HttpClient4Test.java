package be.post.web.httpclient;

import static org.junit.Assert.*;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.util.EntityUtils;
import org.junit.Test;



public class HttpClient4Test {

	@Test
	public void test() throws Exception {
	 CloseableHttpClient httpclient = HttpClients.createDefault();
		
//	DefaultHttpClient httpclient = new DefaultHttpClient(new GAEConnectionManager(), new BasicHttpParams());
		HttpGet httpget = new HttpGet("http://csp-dv2.netpost/");
		CloseableHttpResponse response = httpclient.execute(httpget);
		try {
			HttpEntity entity = response.getEntity();
			if (entity != null) {
			    entity = new BufferedHttpEntity(entity);
			   System.out.println(EntityUtils.toString(entity));
			    
			 
			}
		  System.out.println("content length: " + entity .getContentLength());
		} finally {
		    response.close();
		}
	}

}
