package com.net.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;

import org.junit.Test;

/**
 * java net包核心类
 */
public class Java_Net_Test {

	public static void main(String[] args) {

	}

	/*
	 * InetAddress
	 */
	@Test
	public void testInetAddress() throws Exception {
		InetAddress netAddressa = InetAddress.getByName("www.baidu.com");
		System.out.println(netAddressa.isReachable(2000));
		System.out.println(netAddressa.getHostAddress());

		InetAddress netAddressb = InetAddress.getByAddress(new byte[] { 127, 0, 0, 1 });
		System.out.println(netAddressb.getHostName());
	}

	/*
	 * url
	 * httpurlConnection
	 */
	@Test
	public void testUrlConnection() throws Exception {
		
		// 创建一个网络资源
		URL url = new URL("http://www.baidu.com/");
		
		// 获得与网络资源的连接
		HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
		
		// 设置http请求协议
		// 请求行
		urlConnection.setRequestMethod("POST");
		
		// 请求头
		urlConnection.setRequestProperty("Content-Type", "text/html;charset=utf-8");
		urlConnection.addRequestProperty("connection", "Keep-Alive");
			
		// 请求体
		urlConnection.setDoOutput(true);
		urlConnection.setDoInput(true);
		PrintWriter printWriter = new PrintWriter(urlConnection.getOutputStream());
		printWriter.write("name=jingtao&password=jingtao");

		// 获得响应
		BufferedReader bufferedReader = new BufferedReader(
											new InputStreamReader(
												urlConnection.getInputStream(), "utf-8"));
		
		String temp = null;
		StringBuffer sb = new StringBuffer();
		while ((temp = bufferedReader.readLine()) != null) {
			sb.append(temp);
		}
		System.out.println(sb.toString());
		
		// 关闭资源
		printWriter.close();
		bufferedReader.close();
	}
	
}
