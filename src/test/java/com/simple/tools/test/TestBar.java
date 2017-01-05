package com.simple.tools.test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import com.simple.tools.utils.BarCodeFormat;
import com.simple.tools.utils.BarCodeUtils;

public class TestBar {

	@Test
	public void testJBarcode(){
		String content="6923450656150";
		try {
			BufferedImage image=BarCodeUtils.createBarCode(content, "0.6", "20", BarCodeFormat.EAN_13);
			if(image != null){
				try {
					ImageIO.write(image, "png", new File("e:\\" + "/" +RandomStringUtils.randomAlphanumeric(8)+".png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
