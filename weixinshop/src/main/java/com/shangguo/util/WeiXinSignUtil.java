package com.shangguo.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import org.apache.log4j.Logger;

public class WeiXinSignUtil {
    private static String token = "weixinshop";
    private static Logger logger = Logger.getLogger(WeiXinSignUtil.class); 
    public static boolean checkSignature(String signature, String timestamp,String nonce){
    	boolean result = false;
    	// 对token、timestamp和nonce按字典排序
    	String[] array= new String[] { token, timestamp, nonce };
    	Arrays.sort(array);

    	// 将三个参数字符串拼接成一个字符串
    	String str= array[0].concat(array[1]).concat(array[2]);
        String shaString = null;
    	// 对拼接后的字符串进行sha1加密
    	MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			logger.error("没有SHA-1算法");
		}
    	byte[] digest = md.digest(str.getBytes());
    	shaString = byte2str(digest);
    	
    	if(shaString != null && shaString.equals(signature)){
    		result = true;
    	}
		return result;
    	
    }
    
    /**
     * 将字节数组转换成字符串
     * @param array 字节数组
     * @return String
     */
    public static String byte2str(byte[] array) {
    	StringBuffer hexstr = new StringBuffer();
    	String shaHex = "";
    	for (int i = 0; i < array.length; i++) {
    		shaHex = Integer.toHexString(array[i] & 0xFF);
    		if (shaHex.length() < 2) {
    			hexstr.append(0);
    		}
    		hexstr.append(shaHex);
    	}
    	return hexstr.toString();
    }

}
