package com.simple.tools.controller;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

public class BaseController {

	@Autowired
	protected HttpServletRequest request;
	
	@Autowired
	protected HttpServletResponse response;

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	public String urlParamsToUTF8(String src) throws Exception{
		return java.net.URLDecoder.decode(src,"UTF-8");
	}
	
	public void responseImageStream(BufferedImage image) throws Exception{
		response.setDateHeader("Expires", 0);
		response.addHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache, no-store, max-age=0");
		//response.setContentType("image/jpeg");
		response.setContentType("image/png");
		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(image, "png", out);
		out.flush();
		out.close();
	}
	
}
