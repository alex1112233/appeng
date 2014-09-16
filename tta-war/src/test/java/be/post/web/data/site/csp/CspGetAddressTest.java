package be.post.web.data.site.csp;

import static org.junit.Assert.*;

import org.junit.Test;

import be.post.web.data.RegexUtils;
import be.post.web.data.sites.csp.CspGetAddress;

public class CspGetAddressTest {

	@Test
	public void testPostAddress() throws Exception {
		CspGetAddress.httpCaptcher(true);
	}
	
	String captStr = "before <script type=\"text/javascript\" src=\"https://www.google.com/recaptcha/api/challenge?k=6LetC8YSAAAAAP2xPcLoFq7VOepvm46ZGQNuFvzQ\"></script> after";
    
	String captChallStr = "before <input id=\"recaptcha_challenge_field\" type=\"hidden\" value=\"03AHJ_VutDkvxrKOb_MlCdr-5fkJqJBA7HYLC86OTPz78PoaIfxRZdhOf9qviuqEEEuG9kF8zH35nK_WuSuCikSU-lnCsCR7j0ZmrKeWxizEmXqi7RuiZ4VBDUr1kuuLmd1xQJfDbWoZ90RKuxNgWzOJjy4-QPG0DfhMa7NBHnz8DZG-NYwwpnpGVP4wJbwbCJIwXUSvCTfxlM\" name=\"recaptcha_challenge_field\"> after";
	  
	//@Test
	public void testGetGoogleCaptcha() throws Exception {
		System.out.println( RegexUtils.getFirstMatch( captStr, "(<script type=.*?www.google.com/recaptcha.*?</script>)", 1));
	}
	
	//@Test
	public void testGetGoogleCaptchaCahll() throws Exception {
			System.out.println( RegexUtils.getFirstMatch(captChallStr, "<input id=\"recaptcha_challenge_field\".*?value=\"(.*?)\"", 1));
	}
		
	
}
