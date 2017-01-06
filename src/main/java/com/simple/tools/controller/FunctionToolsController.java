package com.simple.tools.controller;

import java.awt.image.BufferedImage;
import java.io.UnsupportedEncodingException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.simple.tools.model.BarCodeParam;
import com.simple.tools.model.QRCodeParam;
import com.simple.tools.utils.BarCodeFormat;
import com.simple.tools.utils.BarCodeUtils;
import com.simple.tools.utils.Base64Utils;
import com.simple.tools.utils.GuidUtils;
import com.simple.tools.utils.JsonFormatUtils;
import com.simple.tools.utils.JsonResult;
import com.simple.tools.utils.JsonUtils;
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
			try {
				responseImageStream(qrImage);
			} catch (Exception e) {
				e.printStackTrace();
			}
			/*response.setDateHeader("Expires", 0);
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
			}*/
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
			try {
				responseImageStream(qrImage);
			} catch (Exception e) {
				e.printStackTrace();
			}
			/*response.setDateHeader("Expires", 0);
			response.addHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache, no-store, max-age=0");
			response.setContentType("image/jpeg");
			try {
				ServletOutputStream out = response.getOutputStream();
				ImageIO.write(qrImage, "png", out);
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}*/
		}
	}
	
	@RequestMapping(value="/barCode",method=RequestMethod.GET)
	@ResponseBody
	public void getBarCode(@ModelAttribute BarCodeParam param){
		try {
			String content=urlParamsToUTF8(param.getContent());
			String width=urlParamsToUTF8(param.getWidth());
			String height=urlParamsToUTF8(param.getHeight());
			String type=urlParamsToUTF8(param.getType());
			System.out.println("content = "+content);
			System.out.println("width = "+width);
			System.out.println("height = "+height);
			System.out.println("type = "+type);
			BufferedImage image=null;
			if(type.equals("EAN_13")){
				image=BarCodeUtils.createBarCode(content, width, height, BarCodeFormat.EAN_13);
			}else if(type.equals("CODE_128")){
				image=BarCodeUtils.createBarCode(content, width, height, BarCodeFormat.CODE_128);
			}
			if(image != null){
				System.out.println("----------------");
					responseImageStream(image);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/base64encode",method=RequestMethod.POST)
	@ResponseBody
	public JsonResult getBase64Encode(@RequestParam(name="txt") String src){
		//System.out.println("the src is  ==== "+src);
		String encode=Base64Utils.base64Encode(src);
		return new JsonResult(encode);
	}
	
	@RequestMapping(value="/base64decode",method=RequestMethod.POST)
	@ResponseBody
	public JsonResult getBase64Decode(@RequestParam(value="txt") String src){
		//经测试，@RequestParam 这里用name="txt"与value="txt"效果是一样的，但设置value="txt"后面可以增加required=true这个属性
		System.out.println("the src is  ==== "+src);
		String encode=Base64Utils.base64Decode(src);
		return new JsonResult(encode);
	}
	
	@RequestMapping(value="/jsonformat",method=RequestMethod.POST)
	@ResponseBody
	public JsonResult getFormatJson(@RequestParam(name="json")String json){
		JsonResult result=new JsonResult();
		try {
			//这里利用fastjson来判断传入的字符串是否符合json格式，如果符合，则不会抛同异常信息，不符合则会抛出异常信息
			JSONObject.parseObject(json);
			String formatString=JsonFormatUtils.formatJson2(json);
			result.setFlag(true);
			result.setData(formatString);
			//new ObjectMapper().readV
		} catch (Exception e) {
			e.printStackTrace();
			result.setFlag(false);
			result.setError("传入的字符串格式不正确。。。。。。");
		}
		return result;
	}
	
	@RequestMapping(value="/jsonparse",method=RequestMethod.POST)
	@ResponseBody
	public JsonResult jsonParseBean(@RequestParam(name="json")String json,
			@RequestParam(name="packageName")String packageName,@RequestParam String className){
		JsonResult result=new JsonResult();
		try {
			//这里利用fastjson来判断传入的字符串是否符合json格式，如果符合，则不会抛同异常信息，不符合则会抛出异常信息
			JSONObject.parseObject(json);
			String javaBeanString=JsonUtils.getJavaFromJson(packageName,className, json);
			result.setFlag(true);
			result.setData(javaBeanString);
			//new ObjectMapper().readV
		} catch (Exception e) {
			e.printStackTrace();
			result.setFlag(false);
			result.setError("传入的字符串格式不正确。。。。。。");
		}
		return result;
	}
}
