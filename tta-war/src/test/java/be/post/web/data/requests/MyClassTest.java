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
	}

}
