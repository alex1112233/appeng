package be.post.web.data.requests;

import static org.junit.Assert.*;

import org.junit.Test;

import be.post.web.data.sites.csp.CspHttpRequests;

public class MyClassTest {

	CspHttpRequests myClass = new CspHttpRequests();
	
	@Test
	public void test() {
		CspHttpRequests.Request0 req0 = myClass.new Request0();
		System.out.println(req0.method);
		
		System.out.println(Req1.url);
	}

	
	public enum Req1{
		post_name("name", "value");
		
		private String name, value;
		
		public static String url ="myUrl";
		
		Req1(String name, String value){
			this.name = name;
			this.value = value;
		}
		
		public String p_name(){ return name;}
		public String p_value(){ return value;}
		
	}
}
