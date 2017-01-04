package com.simple.tools.test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.simple.tools.utils.QRErrorCorrection;
import com.simple.tools.utils.QRUtils;

public class QRTest {

	@Test
	public void TestQR(){
		String content="1234567890";
		int margin=1;
		int size=300;
		BufferedImage image=QRUtils.createImage(content, size, QRErrorCorrection.L, margin);
		try {
			ImageIO.write(image, "png", new File("e:\\" + "/" +RandomStringUtils.randomAlphanumeric(8)+".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}   
	}
	
	@Test
	public void TestQRWithLogo(){
		String content="1234567890";
		int margin=1;
		int size=300;
		String logoFile="e:\\conn.png";
		BufferedImage image=QRUtils.createImage(content, size, QRErrorCorrection.L, margin,logoFile,true);
		try {
			ImageIO.write(image, "png", new File("e:\\" + "/" +RandomStringUtils.randomAlphanumeric(8)+".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
	}
	
	@Test
	public void testDecoceQR(){
		String qrPath="e:\\erCode.png";
		try {
			Result qrDecode = QRUtils.qrDecode(qrPath);
			System.out.println(qrDecode.getText());
			System.out.println("result="+qrDecode.toString());
			System.out.println(qrDecode.getBarcodeFormat());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCreateBarCode(){
		
		String content="6923450656150";
		int width=250;
		int height=100;
		BufferedImage image=QRUtils.createBarcode(content, width, height, BarcodeFormat.EAN_13);
		if(image != null){
			try {
				ImageIO.write(image, "png", new File("e:\\" + "/" +RandomStringUtils.randomAlphanumeric(8)+".png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
