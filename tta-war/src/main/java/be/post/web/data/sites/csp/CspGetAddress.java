package be.post.web.data.sites.csp;

import java.io.File;
import java.io.StringWriter;
import java.net.URI;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.appengine.repackaged.org.apache.http.entity.ContentType;

public class CspGetAddress {

	public static void postAddress(boolean useFiddlerProxy) throws Exception {

		CspHttpRequests cspHttpRequests = new CspHttpRequests();

		// request 0
		CspHttpRequests.Request0 req0 = cspHttpRequests.new Request0();

		HttpPost httpPost = new HttpPost(req0.url);
		httpPost.addHeader("User-Agent", cspHttpRequests.userAgent);
		BasicCookieStore cookieStore = new BasicCookieStore();

		CloseableHttpClient httpclient = null;

		if (useFiddlerProxy) {

			HttpHost proxy = new HttpHost("127.0.0.1", 8888, "http");
			httpclient = HttpClients.custom()
					.setDefaultCookieStore(cookieStore).setProxy(proxy).build();
		} else {
			httpclient = HttpClients.custom()
					.setDefaultCookieStore(cookieStore).build();
		}

		try {
			HttpGet httpget = new HttpGet(req0.url);
			httpget.addHeader("User-Agent", cspHttpRequests.userAgent);
			CloseableHttpResponse response1 = httpclient.execute(httpget);
			try {
				HttpEntity entity = response1.getEntity();

				System.out.println("Login form get: "
						+ response1.getStatusLine());
				EntityUtils.consume(entity);

				System.out.println("Initial set of cookies:");
				List<Cookie> cookies = cookieStore.getCookies();
				if (cookies.isEmpty()) {
					System.out.println("None");
				} else {
					for (int i = 0; i < cookies.size(); i++) {
						System.out.println("- " + cookies.get(i).toString());
					}
				}
			} finally {
				response1.close();
			}

			HttpUriRequest login = RequestBuilder.post()
					.setUri(new URI(req0.url))
					.addParameter("street", req0.post_street)
					.addParameter("poBoxSearch", req0.post_poBoxSearch)
					.addParameter("number", req0.post_number)
					.addParameter("method", req0.post_method)
					.addParameter("postalCode", req0.post_postalCode).build();
			CloseableHttpResponse response2 = httpclient.execute(login);
			try {

				HttpEntity entity = response2.getEntity();

				StringWriter writer = new StringWriter();
				IOUtils.copy(entity.getContent(), writer, "UTF-8");
				String theString = writer.toString();

				System.out.println("Login form get: "
						+ response2.getStatusLine() + theString);
				EntityUtils.consume(entity);

				System.out.println("Post logon cookies:");
				List<Cookie> cookies = cookieStore.getCookies();
				if (cookies.isEmpty()) {
					System.out.println("None");
				} else {
					for (int i = 0; i < cookies.size(); i++) {
						System.out.println("- " + cookies.get(i).toString());
					}
				}
			} finally {
				response2.close();
			}
		} finally {
			httpclient.close();
		}

	}

	public static void postAddress1(boolean useFiddlerProxy) throws Exception {

		CspHttpRequests cspHttpRequests = new CspHttpRequests();

		// request 0
		CspHttpRequests.Request0 req0 = cspHttpRequests.new Request0();

		BasicCookieStore cookieStore = new BasicCookieStore();

		CloseableHttpClient httpclient = null;

		if (useFiddlerProxy) {

			HttpHost proxy = new HttpHost("127.0.0.1", 8888, "http");
			httpclient = HttpClients.custom()
					.setDefaultCookieStore(cookieStore).setProxy(proxy).build();
		} else {
			httpclient = HttpClients.custom()
					.setDefaultCookieStore(cookieStore).build();
		}

		HttpUriRequest req1 = RequestBuilder.get().setUri(req0.url)
				.addHeader("User-Agent", cspHttpRequests.userAgent).build();

		try {

			CloseableHttpResponse response1 = httpclient.execute(req1);
			try {
				HttpEntity entity = response1.getEntity();

				System.out.println("Login form get: "
						+ response1.getStatusLine());
				EntityUtils.consume(entity);

			} finally {
				response1.close();
			}

			HttpUriRequest login = RequestBuilder.post()
					.setUri(new URI(req0.url))
					.addParameter("street", req0.post_street)
					.addParameter("poBoxSearch", req0.post_poBoxSearch)
					.addParameter("number", req0.post_number)
					.addParameter("method", req0.post_method)
					.addParameter("postalCode", req0.post_postalCode).build();
			CloseableHttpResponse response2 = httpclient.execute(login);
			try {

				HttpEntity entity = response2.getEntity();
				StringWriter writer = new StringWriter();
				IOUtils.copy(entity.getContent(), writer, "UTF-8");
				String theString = writer.toString();

				System.out.println("Login form get: "
						+ response2.getStatusLine() + theString);
				EntityUtils.consume(entity);

			} finally {
				response2.close();
			}
		} finally {
			httpclient.close();
		}

	}

	public static void postAddressEnum(boolean useFiddlerProxy)
			throws Exception {

		CspHttpReqEnum cspHttpRequests = new CspHttpReqEnum();

		HttpPost httpPost = new HttpPost(CspHttpReqEnum.Request0.url);
		httpPost.addHeader("User-Agent", cspHttpRequests.userAgent);
		BasicCookieStore cookieStore = new BasicCookieStore();

		CloseableHttpClient httpclient = null;

		if (useFiddlerProxy) {

			HttpHost proxy = new HttpHost("127.0.0.1", 8888, "http");
			httpclient = HttpClients.custom()
					.setDefaultCookieStore(cookieStore).setProxy(proxy).build();
		} else {
			httpclient = HttpClients.custom()
					.setDefaultCookieStore(cookieStore).build();
		}

		try {
			HttpGet httpget = new HttpGet(CspHttpReqEnum.Request0.url);
			httpget.addHeader("User-Agent", cspHttpRequests.userAgent);
			CloseableHttpResponse response1 = httpclient.execute(httpget);
			try {
				HttpEntity entity = response1.getEntity();

				System.out.println("Login form get: "
						+ response1.getStatusLine());
				EntityUtils.consume(entity);

				System.out.println("Initial set of cookies:");
				List<Cookie> cookies = cookieStore.getCookies();
				if (cookies.isEmpty()) {
					System.out.println("None");
				} else {
					for (int i = 0; i < cookies.size(); i++) {
						System.out.println("- " + cookies.get(i).toString());
					}
				}
			} finally {
				response1.close();
			}

			HttpUriRequest login = RequestBuilder
					.post()
					.setUri(new URI(CspHttpReqEnum.Request0.url))
					.addParameter(CspHttpReqEnum.Request0.post_street.p_name(),
							CspHttpReqEnum.Request0.post_street.p_value())
					.addParameter(
							CspHttpReqEnum.Request0.post_poBoxSearch.p_name(),
							CspHttpReqEnum.Request0.post_poBoxSearch.p_value())
					.addParameter(CspHttpReqEnum.Request0.post_number.p_name(),
							CspHttpReqEnum.Request0.post_number.p_value())
					.addParameter(CspHttpReqEnum.Request0.post_method.p_name(),
							CspHttpReqEnum.Request0.post_method.p_value())
					.addParameter(
							CspHttpReqEnum.Request0.post_postalCode.p_name(),
							CspHttpReqEnum.Request0.post_postalCode.p_value())
					.build();
			CloseableHttpResponse response2 = httpclient.execute(login);
			try {
				HttpEntity entity = response2.getEntity();

				System.out.println("Login form get: "
						+ response2.getStatusLine());
				EntityUtils.consume(entity);

				System.out.println("Post logon cookies:");
				List<Cookie> cookies = cookieStore.getCookies();
				if (cookies.isEmpty()) {
					System.out.println("None");
				} else {
					for (int i = 0; i < cookies.size(); i++) {
						System.out.println("- " + cookies.get(i).toString());
					}
				}
			} finally {
				response2.close();
			}
		} finally {
			httpclient.close();
		}

	}

	public static void httpInteract(boolean useFiddlerProxy) throws Exception {

		BasicCookieStore cookieStore = new BasicCookieStore();
		CloseableHttpClient httpclient = null;
		try {
			if (useFiddlerProxy) {

				HttpHost proxy = new HttpHost("127.0.0.1", 8888, "http");
				httpclient = HttpClients.custom()
						.setDefaultCookieStore(cookieStore).setProxy(proxy)
						.build();
			} else {
				httpclient = HttpClients.custom()
						.setDefaultCookieStore(cookieStore).build();
			}

			// request1 - http://csp-dv2.netpost/csp/indexPage.do

			HttpUriRequest req1 = RequestBuilder
					.get()
					.setUri(new URI("http://csp-dv2.netpost/csp/indexPage.do"))
					.addHeader("User-Agent",
							"Mozilla/5.0 (Windows NT 6.1; rv:30.0) Gecko/20100101 Firefox/30.0")
					.build();

			CloseableHttpResponse resp1 = httpclient.execute(req1);

			try {

				HttpEntity entity1 = resp1.getEntity();
				StringWriter writer1 = new StringWriter();
				IOUtils.copy(entity1.getContent(), writer1, "UTF-8");

				System.out.println("resp1 status: " + resp1.getStatusLine()
						+ writer1.toString());
				EntityUtils.consume(entity1);

			} finally {
				resp1.close();
			}

			// request2 - http://csp-dv2.netpost/csp/j_security_check

			HttpUriRequest req2 = RequestBuilder
					.post()
					.setUri(new URI(
							"http://csp-dv2.netpost/csp/j_security_check"))
					.addHeader("User-Agent",
							"Mozilla/5.0 (Windows NT 6.1; rv:30.0) Gecko/20100101 Firefox/30.0")
					.addParameter("j_username", "u353002")
					.addParameter("j_password", "Jun2014").build();

			CloseableHttpResponse resp2 = httpclient.execute(req2);

			try {

				HttpEntity entity2 = resp2.getEntity();
				StringWriter writer2 = new StringWriter();
				IOUtils.copy(entity2.getContent(), writer2, "UTF-8");

				System.out.println("resp2 status: " + resp2.getStatusLine()
						+ writer2.toString());
				EntityUtils.consume(entity2);

			} finally {
				resp2.close();
			}

			// request3 - http://csp-dv2.netpost/csp/indexPage.do

			HttpUriRequest req3 = RequestBuilder
					.get()
					.setUri(new URI("http://csp-dv2.netpost/csp/indexPage.do"))
					.addHeader("User-Agent",
							"Mozilla/5.0 (Windows NT 6.1; rv:30.0) Gecko/20100101 Firefox/30.0")
					.build();

			CloseableHttpResponse resp3 = httpclient.execute(req3);

			try {

				HttpEntity entity3 = resp3.getEntity();
				StringWriter writer3 = new StringWriter();
				IOUtils.copy(entity3.getContent(), writer3, "UTF-8");

				System.out.println("resp3 status: " + resp3.getStatusLine()
						+ writer3.toString());
				EntityUtils.consume(entity3);

			} finally {
				resp3.close();
			}

			// request4 -
			// http://csp-dv2.netpost/csp/requestfile/uploadRequestFile.do?method=init&be.post.jkt.ui.menu.MenuBase.SELECTED=root.header1.uploadRequestFile

			HttpUriRequest req4 = RequestBuilder
					.get()
					.setUri(new URI(
							"http://csp-dv2.netpost/csp/requestfile/uploadRequestFile.do?method=init&be.post.jkt.ui.menu.MenuBase.SELECTED=root.header1.uploadRequestFile"))
					.addHeader("User-Agent",
							"Mozilla/5.0 (Windows NT 6.1; rv:30.0) Gecko/20100101 Firefox/30.0")
					.addParameter("method", "init")
					.addParameter("be.post.jkt.ui.menu.MenuBase.SELECTED",
							"root.header1.uploadRequestFile").build();

			CloseableHttpResponse resp4 = httpclient.execute(req4);

			try {

				HttpEntity entity4 = resp4.getEntity();
				StringWriter writer4 = new StringWriter();
				IOUtils.copy(entity4.getContent(), writer4, "UTF-8");

				System.out.println("resp4 status: " + resp4.getStatusLine()
						+ writer4.toString());
				EntityUtils.consume(entity4);

			} finally {
				resp4.close();
			}

			// request5 -
			// http://csp-dv2.netpost/csp/requestfile/uploadRequestFile.do

			HttpPost httppost5 = new HttpPost(
					"http://csp-dv2.netpost/csp/requestfile/uploadRequestFile.do");
			
	//		ContentBody content = new InputStreamBody(IOUtils.toInputStream(xmlinput), org.apache.http.entity.ContentType.TEXT_XML, "file");

			FileBody bin = new FileBody(new File("C:\\Projects\\CSP\\doc\\DEV_V0001.11.00\\testFiles\\testAlternatives1.XML"));
			
			HttpEntity reqEntity5 = MultipartEntityBuilder.create()
					
					.addPart("file", bin)
					.addTextBody("method", "upload")
					.build();
			httppost5.setEntity(reqEntity5);
			CloseableHttpResponse response5 = httpclient.execute(httppost5);
			try {
				HttpEntity resEntity5 = response5.getEntity();
				EntityUtils.consume(resEntity5);
			} finally {
				response5.close();
			}

		} finally {
			httpclient.close();
		}

	}
	
	static String xmlinput = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + 
			"<AddressInterpretationRequest xmlns=\"http://www.post.be/csp-request\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.post.be/csp-request csp-request-message-1.0.xsd\" sortingInfo=\"true\" sectorInfo=\"true\" homeInfo=\"true\" validationErrors=\"true\" suggestions=\"0\" suggestionsMinimumScore=\"60\" addressComponents=\"fr nl de\">" + 
			"	<Address id=\"1\">" + 
			"		<Street>RUE LEMAN</Street>" + 
			"		<CivicNumber>119</CivicNumber>" + 
			"		<PostalCode>7012</PostalCode>" + 
			"	</Address>" + 
			"	" + 
			"</AddressInterpretationRequest>";

}
