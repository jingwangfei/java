package com.net.test;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.junit.Test;

/**
 * HttpClient3.0 测试
 */
public class HttpClientTest {

	@Test
	public void testGet() throws Exception {

		// httpClient相当于一个浏览器
		HttpClient client = new HttpClient();
		
		// get请求协议
		GetMethod getMethod = new GetMethod();
		
		// 请求头, 包含协议版本, 请求路径, 查询字符串
		getMethod.setPath("http://www.baidu.com");
		getMethod.setQueryString("name=jingtao");
		
		// 设置请求行, 仅仅是数据的格式不同, 一个是字符串, 一个是Head对象
		getMethod.addRequestHeader("Content-Type", "text/html;charset=utf-8");
		
		// 发送get请求协议, 获得状态, 
		client.executeMethod(getMethod);
		
		// 获得响应内容, 响应有对应的获得各个部分的方法
		System.out.println(getMethod.getResponseBodyAsString());
	}
	
	@Test
	public void testPost() throws Exception {
		HttpClient httpClient = new HttpClient();
		
		// postMethod
		PostMethod postMethod = new PostMethod();
		
		postMethod.setPath("http://www.baidu.com");

		postMethod.addRequestHeader("Content-Type", "text/html;charset=utf-8");
		
		RequestEntity requestEntity = new StringRequestEntity("name=jingtao");
		postMethod.setRequestEntity(requestEntity);
		
		// 执行
		httpClient.executeMethod(postMethod);
		
		// 响应
		System.out.println(postMethod.getResponseBodyAsString());;
	}

}
