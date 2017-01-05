package com.simple.tools.utils;

import org.apache.commons.codec.binary.Base64;

public class Base64Utils {

	/**
	 * 编码操作
	 * @param str
	 * @return
	 */
	public static String base64Decode(String str){
		byte[] bs = Base64.decodeBase64(str);
		return new String(bs);
	}
	
	
	/**
	 * 解码
	 * @param src
	 * @return
	 */
	public static String base64Encode(String src){
		byte[] srcByte=src.getBytes();
		return new String(Base64.encodeBase64(srcByte));
	}
	
}
