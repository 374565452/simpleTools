package com.simple.tools.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.simple.tools.model.QRCodeParam;
import com.simple.tools.utils.GuidUtils;
import com.simple.tools.utils.JsonResult;
import com.simple.tools.utils.Md5Utils;
import com.simple.tools.utils.QRErrorCorrection;
import com.simple.tools.utils.QRUtils;

@Controller
@RequestMapping("/function")
public class FunctionToolsController extends BaseController{

	@RequestMapping( value="/guid",method=RequestMethod.POST )
	@ResponseBody
	public JsonResult callGuid(){
		String uuid=GuidUtils.getGuid();
		return (new JsonResult(uuid));
	}
	
	@RequestMapping(value="/md5",method=RequestMethod.POST)
	@ResponseBody
	public JsonResult callMd5(String src,boolean half){
		String md5="";
		if(half){
			md5=Md5Utils.encrypt16Md5(src);
		}else{
			md5=Md5Utils.encrypt32Md5(src);
		}
		if(md5 != null && !md5.equals("")){
			return (new JsonResult(md5));
		}else{
			JsonResult result=new JsonResult();
			result.setFlag(false);
			result.setError("生成md5时发生错误！");
			return result;
		}
	}
	
	@RequestMapping(value="/ercode",method=RequestMethod.GET)
	@ResponseBody
	public void getErCode(@RequestParam String text,@RequestParam String level,@RequestParam int size,
			@RequestParam int margin,@RequestParam boolean trans){
		//System.out.println("text = "+text);
		//System.out.println("level = "+level);
		//System.out.println("size = "+size);
		//System.out.println("margin = "+margin);
		//System.out.println("trans = "+trans);
		
		if(level == null || level.equals("")){
			level="L";
		}
		QRErrorCorrection correction;
		if(level.equals("M")){
			correction=QRErrorCorrection.M;
		}else if(level.equals("Q")){
			correction=QRErrorCorrection.Q;
		}else if(level.equals("H")){
			correction=QRErrorCorrection.H;
		}else{
			correction=QRErrorCorrection.L;
		}
		
		BufferedImage qrImage=QRUtils.createImage(text, size, correction, margin);
		if(qrImage != null){
			response.setDateHeader("Expires", 0);
			response.addHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache, no-store, max-age=0");
			response.setContentType("image/jpeg");
			try {
				ServletOutputStream out = response.getOutputStream();
				ImageIO.write(qrImage, "png", out);
				out.flush();
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * 解决乱码问题，在js中使用两次encodeURI(encodeURI(url)) 进行编码
	 * 在后台程序中，执行java.net.URLDecoder.decode(text,"UTF-8");进行解码，便可解决中文乱码问题
	 * @param param
	 */
	@RequestMapping(value="/ercodeModel",method=RequestMethod.GET)
	@ResponseBody
	public void getErCodeWithModel(@ModelAttribute QRCodeParam param){
		String text=param.getText();
		try {
			text=java.net.URLDecoder.decode(text,"UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("text = "+text);
		System.out.println("level = "+param.getLevel());
		System.out.println("size = "+param.getSize());
		System.out.println("margin = "+param.getMargin());
		System.out.println("trans = "+param.isTrans());
		String level=param.getLevel();
		if(level == null || level.equals("")){
			level="L";
		}
		QRErrorCorrection correction;
		if(level.equals("M")){
			correction=QRErrorCorrection.M;
		}else if(level.equals("Q")){
			correction=QRErrorCorrection.Q;
		}else if(level.equals("H")){
			correction=QRErrorCorrection.H;
		}else{
			correction=QRErrorCorrection.L;
		}
		
		BufferedImage qrImage=QRUtils.createImage(text, param.getSize(), correction, param.getMargin());
		if(qrImage != null){
			response.setDateHeader("Expires", 0);
			response.addHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache, no-store, max-age=0");
			response.setContentType("image/jpeg");
			try {
				ServletOutputStream out = response.getOutputStream();
				ImageIO.write(qrImage, "png", out);
				out.flush();
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}
