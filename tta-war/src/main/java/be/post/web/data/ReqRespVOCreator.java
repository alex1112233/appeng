package be.post.web.data;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class ReqRespVOCreator {

	
	static Gson gson = new Gson();
	
	public static String create(String className, String jsonHar12Str){
		
		StringBuilder sb = new StringBuilder();
		String userAgent = "";
		
	   JsonObject jsonObject = gson.fromJson(jsonHar12Str, JsonObject.class);
		
	   JsonArray jsonArrayHeaders = jsonObject.getAsJsonObject("log").getAsJsonArray("entries").get(0).getAsJsonObject().get("request").getAsJsonObject().get("headers").getAsJsonArray();
		
		for (int i = 0; i < jsonArrayHeaders.size(); i++) {
			 JsonElement jsonElement = jsonArrayHeaders.get(i);
		 if(jsonElement.getAsJsonObject().get("name").getAsString().equals("User-Agent")){
			 
			userAgent =  jsonElement.getAsJsonObject().get("value").getAsString();
		 }
		}
		
		sb.append("public class " + className + "{\n\n" );
		sb.append("public String userAgent = \"" + userAgent + "\";\n");
		
		JsonArray jsonArrayEntries = jsonObject.getAsJsonObject("log").getAsJsonArray("entries");
		
		for (int i = 0; i < jsonArrayEntries.size(); i++) {
			
			sb.append("\n public class Request" + i + "{\n\n" );
			JsonElement jsonElement = jsonArrayEntries.get(i).getAsJsonObject().get("request");
			sb.append("  public String url = " + jsonElement.getAsJsonObject().get("url") + ";\n");
			sb.append("  public String method = " + jsonElement.getAsJsonObject().get("method") + ";\n");
			
			JsonArray jsonArrayQryStr = jsonElement.getAsJsonObject().get("queryString").getAsJsonArray();
			for (int k = 0; k < jsonArrayQryStr.size(); k++){
				
				sb.append("  public String qrystr_" + jsonArrayQryStr.get(k).getAsJsonObject().get("name").getAsString().replaceAll("\"", "") +
						 " = " + jsonArrayQryStr.get(k).getAsJsonObject().get("value") + ";\n");			
			}
			
			JsonArray jsonArrayPost = jsonElement.getAsJsonObject().get("postData").getAsJsonObject().get("params").getAsJsonArray();
			for (int k = 0; k < jsonArrayPost.size(); k++){
				
				sb.append("  public String post_" + jsonArrayPost.get(k).getAsJsonObject().get("name").getAsString().replaceAll("\"", "") +
						 " = " + jsonArrayPost.get(k).getAsJsonObject().get("value") + ";\n");			
			}
			sb.append("\n\n}" );
			
		}
		
		sb.append("\n}" );
		return sb.toString();
	}
}
