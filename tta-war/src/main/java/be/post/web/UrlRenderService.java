package be.post.web;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.post.web.httpclient.GetUrlContent;

public class UrlRenderService extends HttpServlet {
	  
	@Override
	  public void doGet(HttpServletRequest req, HttpServletResponse resp)
	      throws IOException {
	   

	    String url = req.getParameter("url");
	    
	    String result = GetUrlContent.getUrlContent(url);
	   
	    resp.getWriter().write(result);
	  }
	}
