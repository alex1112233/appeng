package be.post.web.httpclient;


import be.post.web.MailGetService;













import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

public class GetUrlContent {
	
	private static final Logger log = Logger.getLogger(GetUrlContent.class.getName());
	
	public static String getUrlContent(String urlString){
		
		HttpParams httpParams = new BasicHttpParams();
		ClientConnectionManager connectionManager = new GAEConnectionManager();
		 HttpClient client = new DefaultHttpClient(connectionManager, httpParams); 
		   HttpGet get = new HttpGet(urlString);
		   String result = null;
		    try {   
		        HttpResponse resp = client.execute(get);
		        HttpEntity entity = resp.getEntity();
				if (entity != null) {
				   entity = new BufferedHttpEntity(entity);
				   result = EntityUtils.toString(entity);
				}
		    } catch (Exception e) {
		        System.out.println("There was a problem.");
		        log.severe( e.getMessage() );
		    }
		    return result;
		}
	
   public static String getUrlContent1(String urlString){
	   
	   String result = null;
	   
	   try {
		    URL url = new URL(urlString);
		    BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
		    String line;
            StringBuilder sb = new StringBuilder();
		    while ((line = reader.readLine()) != null) {
		        sb.append(line);
		    }
		    reader.close();
            result = sb.toString();
		
		} catch (Exception e) {
			 log.severe( e.getMessage() );
		}
	   
	   return result;
   }
}
