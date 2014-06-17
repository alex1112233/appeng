package be.post.web.httpclient.json;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class GsonTest {

	@Test
	public void test() throws Exception {
		Gson gson = new Gson();
		int[] ints = {1, 2, 3, 4, 5};
		String[] strings = {"abc", "def", "ghi"};

		
		System.out.println( gson.toJson(ints) );  //   ==> prints [1,2,3,4,5]
		System.out.println( gson.toJson(strings) );
		
		String jsonStr = FileUtils.readFileToString(new File("C:/Users/u353002/git/projW/tta-war/src/main/java/resources/cspAi.json"));
		JsonObject jsonObject = gson.fromJson(jsonStr, JsonObject.class);
		System.out.println(jsonObject.getAsJsonObject("log"));
		System.out.println(jsonObject.getAsJsonObject("log").getAsJsonArray("entries").get(0).getAsJsonObject().get("request"));
		System.out.println(jsonObject.getAsJsonObject("log").getAsJsonArray("entries").get(0).getAsJsonObject().get("request").getAsJsonObject().get("headers"));
		System.out.println(jsonObject.getAsJsonObject("log").getAsJsonArray("entries").get(0).getAsJsonObject().get("request").getAsJsonObject().get("headers").getAsJsonArray().isJsonArray());

		JsonArray jsonArray = jsonObject.getAsJsonObject("log").getAsJsonArray("entries").get(0).getAsJsonObject().get("request").getAsJsonObject().get("headers").getAsJsonArray();
		
		for (int i = 0; i < jsonArray.size(); i++) {
			 JsonElement jsonElement = jsonArray.get(i);
		 System.out.println( jsonElement.getAsJsonObject().get("name") + " : " +  jsonElement.getAsJsonObject().get("value"));
		 if(jsonElement.getAsJsonObject().get("name").getAsString().equals("User-Agent")){
			 
			 System.out.println("found : " +  jsonElement.getAsJsonObject().get("value"));
		 }
		}

	}

	

}
