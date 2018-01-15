package com.ydq.ihelp.common;

public class Code {

	//默认错误
	public static final int C_ERROR = -1; 
	
	//成功
	public static final int C_SUCCESS = 200;
	
	//通用
	//无参数
	public static final int C_TY_NOPAM = 0x90001; 
	
	/**************用户相关**************/
	//黑名单
	public static final int C_U_BLACK = 0x10001;
	
	//无该用户
	public static final int C_U_NONE = 0x10002;
	
	//用户已删除
	public static final int C_U_DELETED = 0x10003;
	
	
	
	/**************请求接口相关**************/
	//url异常
	public static final int C_SERVICE_URL_WARRING = 0x11001;
	//url超时
	public static final int C_SERVICE_URL_TIMEOUT = 0x11002;
	
}
