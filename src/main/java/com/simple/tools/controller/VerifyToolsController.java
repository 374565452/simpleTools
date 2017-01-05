package com.simple.tools.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.simple.tools.model.BarCodeParam;
import com.simple.tools.utils.JsonResult;

@Controller
@RequestMapping("/verify")
public class VerifyToolsController extends BaseController {

	@RequestMapping(value = "/barcode", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult verifyBarcodeParam(@ModelAttribute BarCodeParam param) {
		/*System.out.println(param.getContent() + "===" + param.getWidth() + "====" + param.getHeight() + "===="
				+ param.getType() + "====" + param.isTrans());*/
		JsonResult result = new JsonResult();
		result.setFlag(false);
		if (param.getType().equals("EAN_13")) {
			String src = param.getContent();
			if (src == null || src.equals("")) {
				result.setError("选中EAN_13模式下，产生barcode数据不能为空");
			} else if (src.length() != 13) {
				result.setError("选中EAN_13模式下，产生barcode数据长度或格式不正确");
			} else if (!src.matches("[0-9]+")) {
				result.setError("选中EAN_13模式下，产生barcode数据不能包含其他非数字字符");
			}else{
				result.setFlag(true);
				result.setData("");
			}
			return result;
		}else if(param.getType().equals("CODE_128")){
			result.setFlag(true);
			result.setData("");
			return result;
		}else{
			result.setFlag(false);
			result.setError("功能努力开发中。。。。。。");
			return result;
		}
		//return new JsonResult("");

	}

}
