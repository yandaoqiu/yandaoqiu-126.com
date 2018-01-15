package com.ydq.ihelp.common;

public class CommonEnum {

	//用户状态
	public static class ENUM_USER_STATUS{
		 public static final int NORMAL = 0x1000; //正常帐号
		 public static final int BLACK = 0x1001; //黑名单帐号
		 public static final int DELETED = 0x1002; //删除了的帐号
		 public static final int NONE = 0x1003; //账户不存在
	 }
	
	
	//请求状态
		public static class ENUM_URL_STATUS{
			 public static final int NORMAL = ENUM_USER_STATUS.NORMAL;//正常
			 public static final int WARRING = 0x2001; //URL异常
			 public static final int TIMEOUT = 0x2002; //URL超时
			 
		 }
}
