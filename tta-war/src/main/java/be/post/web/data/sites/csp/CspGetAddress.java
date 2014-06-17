package be.post.web.data.sites.csp;

import java.net.URI;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class CspGetAddress {

	public static void postAddress() throws Exception {
		
		
		CspHttpRequests cspHttpRequests = new CspHttpRequests();
		
		// request 0
		CspHttpRequests.Request0 req0 = cspHttpRequests.new Request0();
		
		HttpPost httpPost = new HttpPost(req0.url);
		httpPost.addHeader("User-Agent", cspHttpRequests.userAgent);
		 BasicCookieStore cookieStore = new BasicCookieStore();
	        CloseableHttpClient httpclient = HttpClients.custom()
	                .setDefaultCookieStore(cookieStore)
	                .build();
	        try {
	            HttpGet httpget = new HttpGet(req0.url);
	            httpget.addHeader("User-Agent", cspHttpRequests.userAgent);
	            CloseableHttpResponse response1 = httpclient.execute(httpget);
	            try {
	                HttpEntity entity = response1.getEntity();

	                System.out.println("Login form get: " + response1.getStatusLine());
	                EntityUtils.consume(entity);

	                System.out.println("Initial set of cookies:");
	                List<Cookie> cookies = cookieStore.getCookies();
	                if (cookies.isEmpty()) {
	                    System.out.println("None");
	                } else {
	                    for (int i = 0; i < cookies.size(); i++) {
	                        System.out.println("- " + cookies.get(i).toString());
	                    }
	                }
	            } finally {
	                response1.close();
	            }

	            HttpUriRequest login = RequestBuilder.post()
	                    .setUri(new URI(req0.url))
	                    .addParameter("street", req0.post_street)
	                    .addParameter("box", req0.post_box)
	                    .addParameter("postalCode", req0.post_postalCode)
	                    .build();
	            CloseableHttpResponse response2 = httpclient.execute(login);
	            try {
	                HttpEntity entity = response2.getEntity();

	                System.out.println("Login form get: " + response2.getStatusLine());
	                EntityUtils.consume(entity);

	                System.out.println("Post logon cookies:");
	                List<Cookie> cookies = cookieStore.getCookies();
	                if (cookies.isEmpty()) {
	                    System.out.println("None");
	                } else {
	                    for (int i = 0; i < cookies.size(); i++) {
	                        System.out.println("- " + cookies.get(i).toString());
	                    }
	                }
	            } finally {
	                response2.close();
	            }
	        } finally {
	            httpclient.close();
	        }
	        
	}
}
