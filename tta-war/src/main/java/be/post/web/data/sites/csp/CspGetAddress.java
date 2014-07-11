package be.post.web.data.sites.csp;

import java.io.StringWriter;
import java.net.URI;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.appengine.repackaged.org.apache.http.entity.ContentType;


public class CspGetAddress {

	public static void postAddress(boolean useFiddlerProxy) throws Exception {
		
		
		CspHttpRequests cspHttpRequests = new CspHttpRequests();
		
		// request 0
		CspHttpRequests.Request0 req0 = cspHttpRequests.new Request0();
		
		HttpPost httpPost = new HttpPost(req0.url);
		httpPost.addHeader("User-Agent", cspHttpRequests.userAgent);
		 BasicCookieStore cookieStore = new BasicCookieStore();
		 
		 
	        CloseableHttpClient httpclient = null;
	        
	        if(useFiddlerProxy){
	        	
	        HttpHost proxy = new HttpHost("127.0.0.1", 8888, "http"); 	
	        httpclient = HttpClients.custom()
	                .setDefaultCookieStore(cookieStore)
	                .setProxy(proxy)
	                .build();
	        }else{
	        	httpclient = HttpClients.custom()
		                .setDefaultCookieStore(cookieStore)		                
		                .build();
	        }
	        
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
	                    .addParameter("poBoxSearch", req0.post_poBoxSearch)
	                    .addParameter("number", req0.post_number)
	                     .addParameter("method", req0.post_method)
	                    .addParameter("postalCode", req0.post_postalCode)
	                    .build();
	            CloseableHttpResponse response2 = httpclient.execute(login);
	            try {
	            	
	                HttpEntity entity = response2.getEntity();
	                
	                StringWriter writer = new StringWriter();
	                IOUtils.copy(entity.getContent(), writer, "UTF-8");
	                String theString = writer.toString();
	                
	                System.out.println("Login form get: " + response2.getStatusLine() + theString);
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
	
public static void postAddress1(boolean useFiddlerProxy) throws Exception {		
		
		CspHttpRequests cspHttpRequests = new CspHttpRequests();
		
		// request 0
		CspHttpRequests.Request0 req0 = cspHttpRequests.new Request0();		
		
		BasicCookieStore cookieStore = new BasicCookieStore();
		 
		 
	        CloseableHttpClient httpclient = null;
	        
	        if(useFiddlerProxy){
	        	
	        HttpHost proxy = new HttpHost("127.0.0.1", 8888, "http"); 	
	        httpclient = HttpClients.custom()
	                .setDefaultCookieStore(cookieStore)
	                .setProxy(proxy)
	                .build();
	        }else{
	        	httpclient = HttpClients.custom()
		                .setDefaultCookieStore(cookieStore)		                
		                .build();
	        }
	        
	        HttpUriRequest req1 = RequestBuilder.get().setUri(req0.url).addHeader("User-Agent", cspHttpRequests.userAgent).build();
	        
	        try {
	            
	            CloseableHttpResponse response1 = httpclient.execute(req1);
	            try {
	                HttpEntity entity = response1.getEntity();

	                System.out.println("Login form get: " + response1.getStatusLine());
	                EntityUtils.consume(entity);

	              
	            } finally {
	                response1.close();
	            }

	            
	            HttpUriRequest login = RequestBuilder.post()
	                    .setUri(new URI(req0.url))
	                    .addParameter("street", req0.post_street)
	                    .addParameter("poBoxSearch", req0.post_poBoxSearch)
	                    .addParameter("number", req0.post_number)
	                     .addParameter("method", req0.post_method)
	                    .addParameter("postalCode", req0.post_postalCode)
	                    .build();
	            CloseableHttpResponse response2 = httpclient.execute(login);
	            try {
	            	
	                HttpEntity entity = response2.getEntity();	                
	                StringWriter writer = new StringWriter();
	                IOUtils.copy(entity.getContent(), writer, "UTF-8");
	                String theString = writer.toString();
	                
	                System.out.println("Login form get: " + response2.getStatusLine() + theString);
	                EntityUtils.consume(entity);
	               
	            } finally {
	                response2.close();
	            }
	        } finally {
	            httpclient.close();
	        }
	        
	}	
	
public static void postAddressEnum(boolean useFiddlerProxy) throws Exception {
		
		
		CspHttpReqEnum cspHttpRequests = new CspHttpReqEnum();
		
		
		
		
		HttpPost httpPost = new HttpPost(CspHttpReqEnum.Request0.url);
		httpPost.addHeader("User-Agent", cspHttpRequests.userAgent);
		 BasicCookieStore cookieStore = new BasicCookieStore();
		 
		 
	        CloseableHttpClient httpclient = null;
	        
	        if(useFiddlerProxy){
	        	
	        HttpHost proxy = new HttpHost("127.0.0.1", 8888, "http"); 	
	        httpclient = HttpClients.custom()
	                .setDefaultCookieStore(cookieStore)
	                .setProxy(proxy)
	                .build();
	        }else{
	        	httpclient = HttpClients.custom()
		                .setDefaultCookieStore(cookieStore)		                
		                .build();
	        }
	        
	        try {
	            HttpGet httpget = new HttpGet(CspHttpReqEnum.Request0.url);
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
	                    .setUri(new URI(CspHttpReqEnum.Request0.url))
	                    .addParameter(CspHttpReqEnum.Request0.post_street.p_name(), CspHttpReqEnum.Request0.post_street.p_value())
	                    .addParameter(CspHttpReqEnum.Request0.post_poBoxSearch.p_name(), CspHttpReqEnum.Request0.post_poBoxSearch.p_value())
	                    .addParameter(CspHttpReqEnum.Request0.post_number.p_name(), CspHttpReqEnum.Request0.post_number.p_value())
	                     .addParameter(CspHttpReqEnum.Request0.post_method.p_name(), CspHttpReqEnum.Request0.post_method.p_value())
	                    .addParameter(CspHttpReqEnum.Request0.post_postalCode.p_name(), CspHttpReqEnum.Request0.post_postalCode.p_value())
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

public static void httpInteract(boolean useFiddlerProxy) throws Exception {		
	
	
	 BasicCookieStore cookieStore = new BasicCookieStore();		 
    CloseableHttpClient httpclient = null;
    try{   
       if(useFiddlerProxy){
       	
       HttpHost proxy = new HttpHost("127.0.0.1", 8888, "http"); 	
       httpclient = HttpClients.custom()
               .setDefaultCookieStore(cookieStore)
               .setProxy(proxy)
               .build();
       }else{
       	httpclient = HttpClients.custom()
	                .setDefaultCookieStore(cookieStore)		                
	                .build();
       }
       
       	        
       //request1 - http://csp-ac2.netpost/csp/ai/aiRequest.do 
       HttpUriRequest req1 = RequestBuilder.post()	                       
                      .setUri(new URI("http://csp-ac2.netpost/csp/ai/aiRequest.do"))
                      .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:29.0) Gecko/20100101 Firefox/29.0")
          	           	                       .addParameter("recipient", "")    				
		   	                       .addParameter("company", "")    				
		   	                       .addParameter("street", "wetstraat")    				
		   	                       .addParameter("number", "1")    				
		   	                       .addParameter("box", "")    				
		   	                       .addParameter("poBoxSearch", "false")    				
		   	                       .addParameter("postalCode", "1000")    				
		   	                       .addParameter("city", "")    				
		   	                       .addParameter("method", "submitAddress")    				
		   	                       .addParameter("feedback", "")    				
		   	                       .addParameter("requestCorrelationKey", "Casper.1402909404143.14.978501541177646")    				
		   	                       .addParameter("alternativeStreet", "")    				
		   	                       .addParameter("alternativeNumber", "")    				
		   	                       .addParameter("alternativeBox", "")    				
		   	                       .addParameter("alternativePostalCode", "")    				
		   	                       .addParameter("alternativeCity", "")    				
		   			               .build();
		        
		   CloseableHttpResponse resp1 = httpclient.execute(req1); 
		           
		   try{            
		        
		        HttpEntity entity1 = resp1.getEntity();	                
               StringWriter writer1 = new StringWriter();
               IOUtils.copy(entity1.getContent(), writer1, "UTF-8");
             	                
               System.out.println("resp1 status: " + resp1.getStatusLine() + writer1.toString());
               EntityUtils.consume(entity1);
              
           } finally {
               resp1.close();
           }      
        	        
       //request2 - http://csp-ac2.netpost/csp/ai/aiRequest.do?method=investigate 
       HttpUriRequest req2 = RequestBuilder.post()	                       
                      .setUri(new URI("http://csp-ac2.netpost/csp/ai/aiRequest.do?method=investigate"))
                      .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:29.0) Gecko/20100101 Firefox/29.0")
          	           	                       .addParameter("recipient", "")    				
		   	                       .addParameter("company", "")    				
		   	                       .addParameter("street", "wetstraat")    				
		   	                       .addParameter("number", "1")    				
		   	                       .addParameter("box", "")    				
		   	                       .addParameter("poBoxSearch", "false")    				
		   	                       .addParameter("postalCode", "1000")    				
		   	                       .addParameter("city", "")    				
		   	                       .addParameter("method", "submitAddress")    				
		   	                       .addParameter("feedback", "fff")    				
		   	                       .addParameter("requestCorrelationKey", "Casper.1402909788801.48.96935028631418")    				
		   	                       .addParameter("alternativeStreet", "")    				
		   	                       .addParameter("alternativeNumber", "")    				
		   	                       .addParameter("alternativeBox", "")    				
		   	                       .addParameter("alternativePostalCode", "")    				
		   	                       .addParameter("alternativeCity", "")    				
		   			               .build();
		        
		   CloseableHttpResponse resp2 = httpclient.execute(req2); 
		           
		   try{            
		        
		        HttpEntity entity2 = resp2.getEntity();	                
               StringWriter writer2 = new StringWriter();
               IOUtils.copy(entity2.getContent(), writer2, "UTF-8");
             	                
               System.out.println("resp2 status: " + resp2.getStatusLine() + writer2.toString());
               EntityUtils.consume(entity2);
              
           } finally {
               resp2.close();
           }      
		   
		   FileBody bin = new FileBody(null);
		   ContentBody content = new StringBody("text");
           ContentBody comment = new InputStreamBody(null, "file");

           HttpEntity reqEntity = MultipartEntityBuilder.create()
                   .addPart("bin", content)
                   .addPart("comment", comment)
                   .build();


       //    httppost.setEntity(reqEntity);

        	      
       } finally {
           httpclient.close();
       }
       
}

}
