package be.post.service;

import java.io.Serializable;

import javax.jms.Destination;

public class RequestTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String requestId;
	private String requestText;
	private Destination replyJmsDestination;
	private String response;
	
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public Destination getReplyJmsDestination() {
		return replyJmsDestination;
	}
	public void setReplyJmsDestination(Destination replyJmsDestination) {
		this.replyJmsDestination = replyJmsDestination;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public String getRequestText() {
		return requestText;
	}
	public void setRequestText(String requestText) {
		this.requestText = requestText;
	}	

}
