package be.post.web.data.sites.csp;

public class CspHttpReqEnum {

	public String userAgent = "Mozilla/5.0 (Windows NT 6.1; rv:29.0) Gecko/20100101 Firefox/29.0";

	 public static enum Request0{

	  post_recipient("recipient", ""),
	  post_company("company", ""),
	  post_street("street", "wetstraat"),
	  post_number("number", "1"),
	  post_box("box", ""),
	  post_poBoxSearch("poBoxSearch", "false"),
	  post_postalCode("postalCode", "1000"),
	  post_city("city", ""),
	  post_method("method", "submitAddress"),
	  post_feedback("feedback", ""),
	  post_requestCorrelationKey("requestCorrelationKey", "Casper.1402909404143.14.978501541177646"),
	  post_alternativeStreet("alternativeStreet", ""),
	  post_alternativeNumber("alternativeNumber", ""),
	  post_alternativeBox("alternativeBox", ""),
	  post_alternativePostalCode("alternativePostalCode", ""),
	  post_alternativeCity("alternativeCity", "");


	private String name, value;
	Request0(String name, String value){
	   this.name = name;
	   this.value = value;
	}
	public String p_name(){ return name;}
	public String p_value(){ return value;}

	  public static String url = "http://csp-ac2.netpost/csp/ai/aiRequest.do";
	  public static String method = "POST";

	}
	 public enum Request1{

	qrystr_method("method", "investigate"),
	  post_recipient("recipient", ""),
	  post_company("company", ""),
	  post_street("street", "wetstraat"),
	  post_number("number", "1"),
	  post_box("box", ""),
	  post_poBoxSearch("poBoxSearch", "false"),
	  post_postalCode("postalCode", "1000"),
	  post_city("city", ""),
	  post_method("method", "submitAddress"),
	  post_feedback("feedback", "fff"),
	  post_requestCorrelationKey("requestCorrelationKey", "Casper.1402909788801.48.96935028631418"),
	  post_alternativeStreet("alternativeStreet", ""),
	  post_alternativeNumber("alternativeNumber", ""),
	  post_alternativeBox("alternativeBox", ""),
	  post_alternativePostalCode("alternativePostalCode", ""),
	  post_alternativeCity("alternativeCity", "");


	private String name, value;
	Request1(String name, String value){
	   this.name = name;
	   this.value = value;
	}
	public String p_name(){ return name;}
	public String p_value(){ return value;}

	  public static String url = "http://csp-ac2.netpost/csp/ai/aiRequest.do?method=investigate";
	  public static String method = "POST";

	}
	}
