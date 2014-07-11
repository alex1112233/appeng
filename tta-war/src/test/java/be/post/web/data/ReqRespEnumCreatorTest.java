package be.post.web.data;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

public class ReqRespEnumCreatorTest {

	String jsonStr = "";
	
	
	
	@Before
	public void setup() {
		
	  try {
		jsonStr = FileUtils.readFileToString(new File("C:/Users/u353002/git/projW/tta-war/src/main/java/resources/cspAi.json"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	}
	 
	@Test
	public void test() {
		
	System.out.println( ReqRespEnumCreator.create("MyClassEnum", jsonStr)); 
		
	}

}
