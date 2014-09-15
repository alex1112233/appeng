package be.post.web.data.site.csp;

import static org.junit.Assert.*;

import org.junit.Test;

import be.post.web.data.RegexUtils;
import be.post.web.data.sites.csp.CspGetAddress;

public class CspGetAddressTest {

	@Test
	public void testPostAddress() throws Exception {
		CspGetAddress.httpCaptcher(false);
	}
	
	String captStr = "before <script type=\"text/javascript\" src=\"https://www.google.com/recaptcha/api/challenge?k=6LetC8YSAAAAAP2xPcLoFq7VOepvm46ZGQNuFvzQ\"></script> after";
    
	//@Test
	public void testGetGoogleCaptcha() throws Exception {
		System.out.println( RegexUtils.getFirstMatch( captStr, "(<script type=.*?www.google.com/recaptcha.*?</script>)", 1));
	}
}
