package com.simple.tools.utils;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QRUtils {

	private static final String CHARSET = "utf-8";
	// 二维码尺寸
	private static final int QRCODE_SIZE = 300;

	// LOGO宽度
	private static final int LOGO_WIDTH = 60;
	// LOGO高度
	private static final int LOGO_HEIGHT = 60;

	private static BitMatrix genMatrix(String content, int size, QRErrorCorrection level, int margin) throws Exception {
		Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
		hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
		hints.put(EncodeHintType.MARGIN, margin);
		switch (level) {
		case L: {
			hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
			break;
		}
		case M: {
			hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
			break;
		}
		case Q: {
			hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.Q);
			break;
		}
		case H: {
			hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
			break;
		}
		default: {
			hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
			break;
		}
		}
		int qrsize = size;
		if (size <= 0) {
			qrsize = QRCODE_SIZE;
		}
		BitMatrix matrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, qrsize, qrsize, hints);
		return matrix;
	}

	/**
	 * 生成不带有logo的二维码
	 * 
	 * @param content
	 * @param size
	 *            长与宽是一致的
	 * @return
	 */
	public static BufferedImage createImage(String content, int size, QRErrorCorrection level, int margin) {
		try {
			BitMatrix matrix = genMatrix(content, size, level, margin);
			int width = matrix.getWidth();
			int height = matrix.getHeight();
			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					image.setRGB(x, y, matrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
				}
			}
			return image;
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * 生成带有logo的二维码信息
	 * 
	 * @param content
	 * @param size
	 * @param level
	 * @param margin
	 * @param logoFilePath
	 * @param needCompress
	 * @return
	 */
	public static BufferedImage createImage(String content, int size, QRErrorCorrection level, int margin,
			String logoFilePath, boolean needCompress) {
		BufferedImage image = createImage(content, size, level, margin);
		if (image == null) {
			return null;
		}
		try {
			BufferedImage dest = insertImage(image, logoFilePath, needCompress);
			return dest;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 插入LOGO
	 * 
	 * @param source
	 *            二维码图片
	 * @param logoPath
	 *            LOGO图片地址
	 * @param needCompress
	 *            是否压缩
	 * @throws Exception
	 */
	private static BufferedImage insertImage(BufferedImage source, String logoPath, boolean needCompress)
			throws Exception {
		File file = new File(logoPath);
		if (!file.exists()) {
			throw new Exception("logo file not found.");
		}
		Image src = ImageIO.read(new File(logoPath));
		int width = src.getWidth(null);
		int height = src.getHeight(null);
		if (needCompress) { // 压缩LOGO
			if (width > LOGO_WIDTH) {
				width = LOGO_WIDTH;
			}
			if (height > LOGO_HEIGHT) {
				height = LOGO_HEIGHT;
			}
			Image image = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics g = tag.getGraphics();
			g.drawImage(image, 0, 0, null); // 绘制缩小后的图
			g.dispose();
			src = image;
		}
		int sourceWdith = source.getWidth();
		int sourceHeight = source.getHeight();
		// 插入LOGO
		Graphics2D graph = source.createGraphics();
		int x = (sourceWdith - width) / 2;
		int y = (sourceHeight - height) / 2;
		graph.drawImage(src, x, y, width, height, null);
		Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
		graph.setStroke(new BasicStroke(3f));
		graph.draw(shape);
		graph.dispose();
		return source;
	}

	/**
	 * 对指定路径下的二维码进行解码操作
	 * 
	 * @param qrpath
	 * @return
	 * @throws Exception
	 */
	public static Result qrDecode(String qrpath) throws Exception {
		File file = new File(qrpath);
		if (!file.exists()) {
			throw new Exception("qrcode file not found.");
		}
		BufferedImage image = ImageIO.read(file);
		LuminanceSource source = new BufferedImageLuminanceSource(image);
		Binarizer binarizer = new HybridBinarizer(source);
		BinaryBitmap bitmap = new BinaryBitmap(binarizer);
		Map<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();
		hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");

		Result result = new MultiFormatReader().decode(bitmap, hints);

		return result;
	}

	/**
	 * 产生条形码，利用zxing生成的条形码下面没有字符
	 * 
	 * @param content
	 * @param width
	 * @param height
	 * @param displayCode
	 * @param format
	 * @return
	 */
	public static BufferedImage createBarcode(String content, int width, int height, BarcodeFormat format) {
		return createBarcodeImage(content, width, height, format);
	}

	private static BufferedImage createBarcodeImage(String content, int width, int height, BarcodeFormat format) {
		MultiFormatWriter writer = new MultiFormatWriter();
		BitMatrix result = null;
		try {
			result = writer.encode(content, format, width, height, null);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		int barWidth = result.getWidth();
		int barHeight = result.getHeight();
		// int[] pixels = new int[width * height];
		// All are 0, or black, by default
		BufferedImage image = new BufferedImage(barWidth, barHeight, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, result.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
			}
		}
		return image;
	}
	
	private BufferedImage createCodeImage(String content,int width,int height){
		return null;
	}
}
