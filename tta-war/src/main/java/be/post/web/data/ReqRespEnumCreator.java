package be.post.web.data;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class ReqRespEnumCreator {

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
			
			sb.append("\n public enum Request" + i + "{\n\n" );
			
			JsonElement jsonElement = jsonArrayEntries.get(i).getAsJsonObject().get("request");
			
			JsonArray jsonArrayQryStr = jsonElement.getAsJsonObject().get("queryString").getAsJsonArray();
			for (int k = 0; k < jsonArrayQryStr.size(); k++){
				
				sb.append("qrystr_" + jsonArrayQryStr.get(k).getAsJsonObject().get("name").getAsString().replaceAll("\"", "") 
						+ "(\"" + jsonArrayQryStr.get(k).getAsJsonObject().get("name").getAsString().replaceAll("\"", "") + "\", "
						+ "\"" + jsonArrayQryStr.get(k).getAsJsonObject().get("value").getAsString().replaceAll("\"", "") + "\"),\n");
			}
			
			JsonArray jsonArrayPost = jsonElement.getAsJsonObject().get("postData").getAsJsonObject().get("params").getAsJsonArray();
			for (int k = 0; k < jsonArrayPost.size(); k++){
				
				sb.append("  post_" + jsonArrayPost.get(k).getAsJsonObject().get("name").getAsString().replaceAll("\"", "") 
						     + "(\"" +jsonArrayPost.get(k).getAsJsonObject().get("name").getAsString().replaceAll("\"", "") +"\", "
						     + "\"" + jsonArrayPost.get(k).getAsJsonObject().get("value").getAsString().replaceAll("\"", "") + "\"),\n");
			}
			
			//if we have something in the query or post params we need to replace  the last "," by ";"
			if(jsonArrayPost.size()+jsonArrayPost.size()>0){
				sb.replace(sb.lastIndexOf(","), sb.lastIndexOf(",")+1, ";");
			}
			
			sb.append("\n\n" );
			
			sb.append("private String name, value;\n");
			
			sb.append("Request" + i + "(String name, String value){\n");
			sb.append("   this.name = name;\n");
			sb.append("   this.value = value;\n}\n");
			sb.append("public String p_name(){ return name;}\n");
			sb.append("public String p_value(){ return value;}\n\n");	
			
			sb.append("  public static String url = " + jsonElement.getAsJsonObject().get("url") + ";\n");
			sb.append("  public static String method = " + jsonElement.getAsJsonObject().get("method") + ";\n");
		
			sb.append("\n}" );
		}
		
		sb.append("\n}" );
		return sb.toString();
	}
}
