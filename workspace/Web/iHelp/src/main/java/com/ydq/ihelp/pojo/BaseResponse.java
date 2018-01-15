package com.ydq.ihelp.pojo;

import com.ydq.ihelp.common.Code;

public class BaseResponse {
	
	//返回码
	private int code = Code.C_SUCCESS;
	
	//状态信息
	private String msg = "";
	
	//包含的内容
	private Object coentent;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getCoentent() {
		return coentent;
	}

	public void setCoentent(Object coentent) {
		this.coentent = coentent;
	}
}
