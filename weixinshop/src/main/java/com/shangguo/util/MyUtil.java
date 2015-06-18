package com.shangguo.util;

/**
 * 工具类
 * 
 * @author lzc
 * 
 */
public class MyUtil {
	private MyUtil() {

	}

	/**
	 * 判断String是否为空或者为空字符串，
	 * 
	 * @param s
	 * @return 是则true,否则false
	 */
	public static boolean isEmpty(String s) {
		if (s == null || "".equals(s)) {
			return true;

		} else {
			return false;
		}
	}

	/**
	 * 判断String是否为空或者为空字符串，
	 * 
	 * @param s
	 * @return 是则false,否则 true
	 */
	public static boolean isNotEmpty(String s) {
		if (s == null || "".equals(s)) {
			return false;

		} else {
			return true;
		}
	}

}
