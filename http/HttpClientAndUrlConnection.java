package com.http;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.cookie.CookieSpec;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

/*
 * Java原声 url 和  httpClient 的比较:
 * 		HttpClient比URL更加方便, 读取数据, 仅仅是一个方法. 或者是设置参数的的时候, 都是一个简单的方法.
 * 		HttpClient可以进行有状态的回话. 
 * 			
 *
 */
public class HttpClientAndUrlConnection {
	

	public static void main(String args[]) throws Exception {
		
		/**
		 * java原声httpUrlConnection
		 */
		
		/*
		// 网路资源
		URL url = new URL("https://www.baidu.com");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		
		
		// 请求
		connection.setRequestMethod("POST");
		
		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
		
		connection.setDoOutput(true);
		PrintWriter writer = new PrintWriter(connection.getOutputStream());
		writer.print("xmldata=" + xmlBody + "&ecid=280009999280013598");
		writer.flush();
		writer.close();

		// 响应
		System.out.println(connection.getResponseCode() + " " + connection.getResponseMessage());
		
		Map<String, List<String>> map = connection.getHeaderFields();
		for (Map.Entry<String, List<String>> entry : map.entrySet()) {
			System.out.print(entry.getKey() + ": ");
			for (String str : entry.getValue()) {
				System.out.print(str + ", ");
			}
			System.out.println();
		}
		
		BufferedReader reader = new BufferedReader(
									new InputStreamReader(connection.getInputStream(), "utf-8"));
		StringBuffer sb = new StringBuffer();
		String temp = null;
		while ((temp = reader.readLine()) != null) {
			sb.append(temp + "\n");
		}
		reader.close();
		System.out.println(sb.toString());
	 * 
	 */
	
		/**
		 * httpClient测试
		 */
		// 发送器
		HttpClient httpClient = new HttpClient();
		
		/**
		 * 注意, 如果是想有状态登陆, 必须设置HttpClient的host配置.
		 */
		httpClient.getHostConfiguration().setHost("localhost", 8199);

		
		// 请求
		PostMethod postMethod = new PostMethod();
		
		postMethod.setPath("/bb/loginServlet");
	
		postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		
		/*
		 * 1. 使用NameValuePiar设置参数
		 */
		NameValuePair nameValuePair1 = new NameValuePair("ecid", "1ewkffjdfd");
		NameValuePair nameValuePair2 = new NameValuePair("name", "jingtao");
		NameValuePair[] parametersBody = new NameValuePair[] {nameValuePair1, nameValuePair2};
		postMethod.setRequestBody(parametersBody);
		
		/*
		 * 2. 使用RequestEntity来设置参数 
		 */
//		RequestEntity requestEntity = new StringRequestEntity("name=jingtao");
//		postMethod.setRequestEntity(requestEntity);
		
		// 发送
		httpClient.executeMethod(postMethod);

		// 响应
		System.out.println(postMethod.getStatusLine());
		
		Header[] headers = postMethod.getResponseHeaders();
		for (Header header : headers) {
			System.out.println(header.getName() + ": " + header.getValue());
		}
		
		System.out.println(postMethod.getResponseBodyAsString());
		
		// 关闭连接
		postMethod.releaseConnection();
		
		// 获得Cookie信息
		CookieSpec cookieSpec = CookiePolicy.getDefaultSpec();
		Cookie[] cookies = cookieSpec.match("localhost", 8080, "/bb/loginServlet", false, httpClient.getState().getCookies());
		if(cookies.length == 0) {
			System.out.println("None");
		} else {
			for (Cookie cookie : cookies) {
				System.out.println(cookie.toString());
			}
		}
		
		// 模拟有状态回话
		HttpMethod getMethod = new GetMethod("/bb/loginServlet2");
		getMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		
		httpClient.executeMethod(getMethod);
		
		System.out.println(getMethod.getResponseBodyAsString());
		getMethod.releaseConnection();
	}
}
