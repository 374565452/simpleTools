package com.simple.tools.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.simple.tools.utils.GuidUtils;
import com.simple.tools.utils.JsonResult;
import com.simple.tools.utils.Md5Utils;

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
	
}
