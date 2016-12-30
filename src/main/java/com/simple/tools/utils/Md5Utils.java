package com.simple.tools.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

public class Md5Utils {

	public static String encrytMD5(String data) {
		try {
			// 指定加密算法
			MessageDigest digest = MessageDigest.getInstance("MD5");
			digest.update(data.getBytes());
			return encryptMD5toString(digest.digest());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 将加密后的字节数组转化为固定长度的字符串
	private static String encryptMD5toString(byte[] data) {
		try {
			String str = "";
			String str16;
			for (int i = 0; i < data.length; i++) {
				// 转换为16进制数据
				// Integer.toHexString的参数是int，如果不进行&0xff，那么当一个byte会转换成int时，由于int是32位，而byte只有8位这时会进行补位，
				// 例如补码11111111的十进制数为-1转换为int时变为11111111111111111111111111111111好多1啊，呵呵！即0xffffffff但是这个数是不对的，这种补位就会造成误差。
				// 和0xff相与后，高24比特就会被清0了，结果就对了。
				str16 = Integer.toHexString(0xFF & data[i]);
				if (str16.length() == 1) {
					str = str + "0" + str16;
				} else {
					str = str + str16;
				}
			}
			return str;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
	public static String encrypt32Md5(String src){
		return encrytMD5(src);
	}
	
	public static String encrypt16Md5(String src){
		String _32=encrytMD5(src);
		if(_32 != null && _32.length() ==32){
			return _32.substring(8,24);
		}else{
			return null;
		}
	}
	
	 public static String getBytesMD5(byte[] bytes, int offset, int len){
	    if (bytes == null) {
	      return null;
	    }
	    MessageDigest digest = null;
	    try {
	      digest = MessageDigest.getInstance("MD5");
	      digest.update(bytes, offset, len);
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	      return null;
	    }
	    BigInteger bigInt = new BigInteger(1, digest.digest());
	    return bigInt.toString(16);
	  }
	
}
