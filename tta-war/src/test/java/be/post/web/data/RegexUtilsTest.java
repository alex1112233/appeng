package be.post.web.data;

import static org.junit.Assert.*;

import org.junit.Test;

public class RegexUtilsTest {

	@Test
	public void testGetFirstMatch() {
		System.out.println(RegexUtils.getFirstMatch(repsTest, "iframe src=\"(.*?)\"", 1));
	}
	
	
	String repsTest = "	<div id=\"feedbackZone\">\r\n" + 
			"						<textarea name=\"feedback\" cols=\"40\" rows=\"4\">test comment</textarea><br/>\r\n" + 
			"						\r\n" + 
			"							<script type=\"text/javascript\">\r\n" + 
			"								var RecaptchaOptions = { \r\n" + 
			"									theme : 'white',\r\n" + 
			"									lang : 'fr'\r\n" + 
			"								};\r\n" + 
			"							</script>\r\n" + 
			"							<script type=\"text/javascript\" src=\"https://www.google.com/recaptcha/api/challenge?k=6LetC8YSAAAAAP2xPcLoFq7VOepvm46ZGQNuFvzQ\"></script>\r\n" + 
			"							<noscript>\r\n" + 
			"								<iframe src=\"https://www.google.com/recaptcha/api/noscript?k=6LetC8YSAAAAAP2xPcLoFq7VOepvm46ZGQNuFvzQ\" height=\"300\" width=\"500\" frameborder=\"0\"></iframe><br>\r\n" + 
			"								<textarea name=\"recaptcha_challenge_field\" rows=\"3\" cols=\"40\"></textarea>\r\n" + 
			"								<input type=\"hidden\" name=\"recaptcha_response_field\" value=\"manual_challenge\">\r\n" + 
			"							</noscript>";

}
