package com.simple.tools.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import org.jbarcode.JBarcode;
import org.jbarcode.JBarcodeFactory;
import org.jbarcode.encode.InvalidAtributeException;
import org.jbarcode.paint.EAN13TextPainter;
import org.jbarcode.paint.TextPainter;
import org.jbarcode.paint.WidthCodedPainter;

public class BarCodeUtils {
	private static final String FONT_FAMILY = "consola";
	private static final int FONT_SIZE = 12;

	/**
	 * 自定义的 TextPainter， 允许定义字体，大小等等。
	 */
	static class CustomTextPainter implements TextPainter {
		public static CustomTextPainter instance = new CustomTextPainter();

		public static CustomTextPainter getInstance() {
			return instance;
		}

		@Override
		public void paintText(BufferedImage barCodeImage, String text, int width) {
			Graphics g2d = barCodeImage.getGraphics();

			Font font = new Font(FONT_FAMILY, Font.PLAIN, FONT_SIZE * width);
			g2d.setFont(font);
			FontMetrics fm = g2d.getFontMetrics();
			int height = fm.getHeight();
			int center = (barCodeImage.getWidth() - fm.stringWidth(text)) / 2;

			g2d.setColor(Color.WHITE);
			g2d.fillRect(0, 0, barCodeImage.getWidth(), barCodeImage.getHeight() * 1 / 20);
			g2d.fillRect(0, barCodeImage.getHeight() - (height * 9 / 10), barCodeImage.getWidth(), (height * 9 / 10));
			g2d.setColor(Color.BLACK);
			g2d.drawString(text, center, barCodeImage.getHeight() - (height / 10) - 2);
		}
	}

	public static BufferedImage createBarCode(String content, String dimension, String barHeight, BarCodeFormat format)
			throws Exception {

		JBarcode barcode = null;
		switch (format) {
		case All_1D: {
			barcode = JBarcodeFactory.getInstance().createCode11();
			break;
		}
		case CODABAR: {
			barcode = JBarcodeFactory.getInstance().createCodabar();
			break;
		}
		case CODE_128: {
			barcode = JBarcodeFactory.getInstance().createCode128();
			return genCode128Image(content, barcode, dimension, barHeight);
			//break;
		}

		case CODE_39: {
			barcode = JBarcodeFactory.getInstance().createCode39();
			break;
		}
		case CODE_93: {
			barcode = JBarcodeFactory.getInstance().createCode93();
			break;
		}
		case EAN_13: {
			barcode = JBarcodeFactory.getInstance().createEAN13();
			// configBarcode(barcode, dimension, barHeight);
			return genEAN13Image(content, barcode, dimension, barHeight);
			// break;
		}
		case EAN_8: {
			barcode = JBarcodeFactory.getInstance().createEAN8();
			break;
		}
		/*
		 * case : {
		 * barcode=JBarcodeFactory.getInstance().createInterleaved2of5(); break;
		 * }
		 */
		case MSI: {
			barcode = JBarcodeFactory.getInstance().createMSIPlessey();
			break;
		}
		/*
		 * case : { barcode=JBarcodeFactory.getInstance().createPostNet();
		 * break; }
		 */
		/*
		 * case : { barcode=JBarcodeFactory.getInstance().createStandard2of5();
		 * break; }
		 */
		case UPC_A: {
			barcode = JBarcodeFactory.getInstance().createUPCA();
			break;
		}
		case UPC_E: {
			barcode = JBarcodeFactory.getInstance().createUPCE();
			break;
		}
		}

		return null;
	}

	/*
	 * private static void configBarcode(JBarcode barcode, String dimension,
	 * String barHeight) throws Exception { if (barcode != null) {
	 * 
	 * // 设置显示字体 //barcode.setTextPainter(BaseLineTextPainter.getInstance());
	 * //barcode.setTextPainter(CustomTextPainter.getInstance() ); } }
	 */

	private static BufferedImage genCode128Image(String content, JBarcode barcode, String dimension, String barHeight)
			throws Exception {
		barcode.setPainter(WidthCodedPainter.getInstance());
		barcode.setTextPainter(EAN13TextPainter.getInstance());
		// 尺寸，面积，大小程度
		barcode.setXDimension(Double.valueOf(dimension).doubleValue());
		barcode.setBarHeight(Double.valueOf(barHeight).doubleValue());
		barcode.setWideRatio(Double.valueOf(30).doubleValue());
		barcode.setShowText(true);
		return barcode.createBarcode(content);
	}

	private static BufferedImage genEAN13Image(String content, JBarcode barcode, String dimension, String barHeight)
			throws Exception {
		if (checkEAN13(content, barcode)) {
			barcode.setPainter(WidthCodedPainter.getInstance());
			barcode.setTextPainter(EAN13TextPainter.getInstance());
			// 尺寸，面积，大小程度
			barcode.setXDimension(Double.valueOf(dimension).doubleValue());
			barcode.setBarHeight(Double.valueOf(barHeight).doubleValue());
			barcode.setWideRatio(Double.valueOf(30).doubleValue());
			barcode.setShowText(true);
			int len = content.length();
			String code = content.substring(0, len - 1);
			return barcode.createBarcode(code);
		}
		return null;
	}

	private static boolean checkEAN13(String content, JBarcode barcode) {
		int len = content.length();
		if (len != 13) {
			return false;
		}
		// 获取到前12位
		String barCode = content.substring(0, len - 1);

		// 获取到校验位
		String code = content.substring(len - 1, len);
		String checkCode = "";
		try {
			checkCode = barcode.calcCheckSum(barCode);

		} catch (InvalidAtributeException e) {
			e.printStackTrace();
			return false;
		}
		// 在这里进行拉标准一维条形码的验证，可以在这里不进行数据的验证工作
		// 只有按照标准来做，才有可能与市面上的扫码枪进行匹配
		/*
		 * if (!code.equals(checkCode)) { return false; }
		 */
		return true;
	}

}
