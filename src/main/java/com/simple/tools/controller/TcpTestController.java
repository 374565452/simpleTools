package com.simple.tools.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.simple.tools.netty.DeviceFactory;
import com.simple.tools.utils.JsonResult;

@Controller
@RequestMapping("/tcp")
public class TcpTestController extends BaseController{
	
	@RequestMapping("/netty")
	@ResponseBody
	public JsonResult nettyTest() throws Exception{
		String str="hello world from web ";
		DeviceFactory.getInstance().sendToFrontDevice(101, str);
		System.out.println("from web to client send the data ,the data is "+str);
		int i=0;
		while(i<3){
			Object val=DeviceFactory.getInstance().fetchFromFrontDevice(101);
			i++;
			if(val == null){
				Thread.sleep(1000);
			}else{
				return new JsonResult(val);
			}
		}
		JsonResult result=new JsonResult();
		result.setFlag(false);
		result.setError("test the tcp ip error ----");
		return result;
	}
	
}
