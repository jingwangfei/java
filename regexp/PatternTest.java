package com.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;


public class PatternTest {

	/**
	 * 正则表达式: 捕获组
	 * 	(pattern)---> 捕获组: 捕获内容,并自动分配组号;
	 * 	(?<name>pattern) | (?'name'pattern) ---> 命名捕获组: 捕获内容, 并分配组名称;
	 * 	\k ---> 捕获组反向引用: \1, \2..
	 *  \'name' | \ <name> ---> 命名捕获组反向引用: \'name'
	 * 
	 * 但是注意: 
	 * 		Java中并不支持, 命名捕获组
	 */
	@Test
	public void testGroup() throws Exception {
		
		String url = "https://github.com:80/jingwangfei";
		
		/*
		 * protocal捕获组 --> (\\w+)
		 * :/\\/\\ ---> 固定格式
		 * ([^//:]) --> serverName捕获组(除了/ : 的任意字符)
		 * (:/d+)? ---> 可有可否的端口号
		 * ([^#:]*) --> urlPath捕获组
		 */
		Pattern pattern = Pattern.compile("(\\w+)://([^/:]+)(:\\d+)?([^#:]*)"); // 编译成正则表达式
		
		// 获得某个字符串的匹配器
		Matcher matcher = pattern.matcher(url);
		
		// 输出捕获组的内容
		if (matcher.matches()) { // 注意与find方法的区别
			for (int i = 0; i <= matcher.groupCount(); i++) {
				System.out.println(matcher.group(i));
			}
		}
	}
	
	
	
	/*
	 * 利用捕获组的反向引用, 来获得一个字符串的重复字符
	 */
	@Test
	public void testBackGroup() throws Exception {
		/*
		 * (.)  ---> 捕获任意一个字符
		 * .* ---> 任意字符任意数量
		 * \\1 ---> 反向引用第一个捕获组内容
		 */
		String testStr = "abcdaxxe";
		Pattern pattern = Pattern.compile("(.).*\\1");
		Matcher matcher = pattern.matcher(testStr);
		
		// 输出
		while (matcher.find()) { // 注意与matcher方法的区别, matcher是整个字符, find是子序列
			for (int i = 0; i <= matcher.groupCount(); i++) {
				System.out.println(matcher.group(i));
			}
		}
	}

}
