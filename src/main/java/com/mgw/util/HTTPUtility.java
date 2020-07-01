package com.mgw.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HTTPUtility {
	private static final Logger log = LoggerFactory.getLogger(HTTPUtility.class);
	
	public static final int METHOD_GET = 1;
	public static final int METHOD_POST = 2;
	
	private static final String DEFAULT_ENCODING = "UTF-8";
	
	public static String doHTTPRequest (int method, String urlStr, HashMap<String, String> header, String requestBody) {
		return doHTTPRequest(method, urlStr, DEFAULT_ENCODING, header, requestBody);
	}
	
	public static String doHTTPRequest (int method, String urlStr, String encoding, HashMap<String, String> header, String requestBody) {
		OutputStream os = null;
		BufferedReader in = null;
		String responseBody = null;
		
		try {
			log.info(String.format("connect to [%s]", urlStr));
			URL url = new URL(urlStr);
			
			URLConnection conn = url.openConnection();
			
			if (checkIsSSL(urlStr)) {
				log.info("bypass ssl certificate checking");
			    HttpsURLConnection httpsConn = (HttpsURLConnection) conn;
			    TrustManager[] trustAllCerts = new TrustManager[]{
				        new X509TrustManager() {
				            public X509Certificate[] getAcceptedIssuers() {
				            	return new X509Certificate[0];
				            }
				            public void checkClientTrusted(
				                X509Certificate[] certs, String authType) {
				            }
				            public void checkServerTrusted(
				                X509Certificate[] certs, String authType) {
				            }
				        }
				    };
				SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
		        sslContext.init(null, trustAllCerts, new SecureRandom());
		        SSLSocketFactory ssf = sslContext.getSocketFactory();
		        
		        httpsConn.setHostnameVerifier(new HostnameVerifier() {
					@Override
					public boolean verify(String hostname, SSLSession session) {
						return true;
					}
				});
		        httpsConn.setSSLSocketFactory(ssf);
			}
			
			String methodStr = fetchHTTPMethod(method);
			if (methodStr == null) {
				return null;
			}
			
			HttpURLConnection httpConn = (HttpURLConnection) conn;
			putRequestHeader(httpConn, header);
			
			httpConn.setRequestMethod(methodStr);
			httpConn.setUseCaches(false);
			httpConn.setConnectTimeout(10000);
			httpConn.setReadTimeout(10000);
			
			if (!StringUtility.isNullOrEmpty(requestBody)) {
				httpConn.setDoOutput(true);
				
				log.info(String.format("request body:[%s]", requestBody));
				os = httpConn.getOutputStream();
				os.write(requestBody.getBytes(DEFAULT_ENCODING));
				os.flush();
			}
			
			int responseCode = httpConn.getResponseCode();
			log.info(String.format("response code:[%d]", responseCode));
			if (responseCode == java.net.HttpURLConnection.HTTP_OK) {
				in = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				
				responseBody = response.toString();
				log.info(String.format("response body:[%s]", responseBody));
			}
		} catch (Exception ex) {
			log.error("doHTTPRequest error:", ex);
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
		}
		
		return responseBody;
	}
	
	private static boolean checkIsSSL(String url) {
		if (url.startsWith("https")) {
			return true;
		} else {
			return false;
		}
	}

	private static String fetchHTTPMethod(int method) {
		switch (method) {
		case METHOD_GET:
			return "GET";
		case METHOD_POST:
			return "POST";
		default:
			log.error("illegal http method!");
			return null;
		}
	}
	
	private static void putRequestHeader(HttpURLConnection conn, HashMap<String, String> header) {
		log.info("request header:");
		if (header != null) {
			for (Map.Entry<String, String> entry : header.entrySet()) {
				log.info(String.format("%s:[%s]", entry.getKey(), entry.getValue()));
				conn.setRequestProperty(entry.getKey(), entry.getValue());
			}
		}
	}
	
	public static String genParamsData(Map<String, Object> params) {
		StringBuilder postData = new StringBuilder();
		for (Map.Entry<String, Object> param : params.entrySet()) {
			if (postData.length() != 0)
				postData.append('&');
			postData.append(param.getKey());
			postData.append('=');
			postData.append(String.valueOf(param.getValue()));
		}
		return postData.toString();
	}
}
