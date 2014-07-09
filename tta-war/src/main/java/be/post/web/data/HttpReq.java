package be.post.web.data;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class HttpReq {

	private String url, method;
	private Map<String, String> qryParams= new LinkedHashMap<String, String>();
	private Map<String, String> postParams= new LinkedHashMap<String, String>();
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public Map<String, String> getQryParams() {
		return qryParams;
	}
	public void setQryParams(Map<String, String> qryParams) {
		this.qryParams = qryParams;
	}
	public Map<String, String> getPostParams() {
		return postParams;
	}
	public void setPostParams(Map<String, String> postParams) {
		this.postParams = postParams;
	}
	
	
}
