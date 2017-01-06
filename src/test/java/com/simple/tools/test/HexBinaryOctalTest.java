package com.simple.tools.test;

import org.junit.Test;

/**
 * java 程序中，十进制、八进制、二进制输出测试
 * @author Administrator
 *
 */
public class HexBinaryOctalTest {

	@Test
	public void testOutput(){
		int x =19;
		System.out.printf("%010x\n",x);//按10位八进制输出，向右靠齐，左边用0补齐
		System.out.printf("%010o\n",x);//按10位十六进制输出，向右靠齐，左边用0补齐
		//System.out.printf("%010b\n",x);//按10位十六进制输出，向右靠齐，左边用0补齐
		System.out.println(Integer.toBinaryString(x)); //以二进制输出
		System.out.println(Integer.toHexString(x)); //以十进制输出
		System.out.println(Integer.toOctalString(x)); //以八进制方式输出
		
	}
	
}
