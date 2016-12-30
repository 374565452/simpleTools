package com.simple.tools.utils;

import java.io.Serializable;

public class JsonResult implements Serializable{

	private static final long serialVersionUID = 1L;

	private String error;
	
	private boolean flag;
	
	private Object data;

	public JsonResult(){
		
	}
	
	public JsonResult(Object data){
		flag=true;
		this.data=data;
		error="";
	}
	
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
}
