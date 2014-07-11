package be.post.web.data;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class ReqRespVelocityCreator {

static Gson gson = new Gson();
	
	public static String create(String className, String jsonHar12Str){
		
		/* first, we init the runtime engine.  Defaults are fine. */
        try
        {
            Velocity.init();
        }
        catch(Exception e)
        {
            System.out.println("Problem initializing Velocity : " + e );
            return null;
        }		
        
        /* lets make a Context and put data into it */
        VelocityContext context = new VelocityContext();        
		
	   //getting user agent value	
	   JsonObject jsonObject = gson.fromJson(jsonHar12Str, JsonObject.class);
		
	   JsonArray jsonArrayHeaders = jsonObject.getAsJsonObject("log").getAsJsonArray("entries").get(0).getAsJsonObject().get("request").getAsJsonObject().get("headers").getAsJsonArray();
		
		for (int i = 0; i < jsonArrayHeaders.size(); i++) {
			 JsonElement jsonElement = jsonArrayHeaders.get(i);
		 if(jsonElement.getAsJsonObject().get("name").getAsString().equals("User-Agent")){
			 
			String userAgent =  jsonElement.getAsJsonObject().get("value").getAsString();
			context.put("userAgent", userAgent);
		 }
		}	
	
		List<HttpReq> requestList = new ArrayList<HttpReq>();
		
		JsonArray jsonArrayEntries = jsonObject.getAsJsonObject("log").getAsJsonArray("entries");
		
		for (int i = 0; i < jsonArrayEntries.size(); i++) {
			
			HttpReq httpReq = new HttpReq();
			
			JsonElement jsonElement = jsonArrayEntries.get(i).getAsJsonObject().get("request");
			
			httpReq.setUrl(jsonElement.getAsJsonObject().get("url").toString().replaceAll("\"", ""));
			httpReq.setMethod(jsonElement.getAsJsonObject().get("method").toString().replaceAll("\"", ""));
		
			if(jsonElement.getAsJsonObject().get("queryString")!=null){
			  JsonArray jsonArrayQryStr = jsonElement.getAsJsonObject().get("queryString").getAsJsonArray();
			  for (int k = 0; k < jsonArrayQryStr.size(); k++){
				
				httpReq.getQryParams().put(jsonArrayQryStr.get(k).getAsJsonObject().get("name").getAsString().replaceAll("\"", ""),
						     jsonArrayQryStr.get(k).getAsJsonObject().get("value").getAsString().replaceAll("\"", ""));
			  }
			}
			
			if(jsonElement.getAsJsonObject().get("postData")!=null){
				if(jsonElement.getAsJsonObject().get("postData").getAsJsonObject().get("params")!=null){	
			  JsonArray jsonArrayPost = jsonElement.getAsJsonObject().get("postData").getAsJsonObject().get("params").getAsJsonArray();
			  for (int k = 0; k < jsonArrayPost.size(); k++){
				
				httpReq.getPostParams().put (jsonArrayPost.get(k).getAsJsonObject().get("name").getAsString().replaceAll("\"", ""),
						       jsonArrayPost.get(k).getAsJsonObject().get("value").getAsString().replaceAll("\"", ""));
			  }
			}		  
				
			  }
			 requestList.add(httpReq);
		}
		
		context.put("requestList",requestList);
		
		 /* lets render a template */

        StringWriter w = new StringWriter();

        try
        {
            Velocity.mergeTemplate("./src/main/java/resources/reqRespJava.vm", "ISO-8859-1", context, w );
        }
        catch (Exception e )
        {
            System.out.println("Problem merging template : " + e );
        }

       return(" template : " + w );

    }
	
}
