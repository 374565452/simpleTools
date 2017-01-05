package com.simple.tools.test;

import org.junit.Test;

import com.simple.tools.utils.Base64Utils;

public class Base64Test {

	@Test
	public void testBase64Encode(){
		String src ="1234567890";
		String encode = Base64Utils.base64Encode(src);
		System.out.println(encode);
	}
	
	@Test
	public void testBase64Decode(){
		String src="MTIzNDU2Nzg5MA===";
		System.out.println(Base64Utils.base64Decode(src));
	}
	
	
}
