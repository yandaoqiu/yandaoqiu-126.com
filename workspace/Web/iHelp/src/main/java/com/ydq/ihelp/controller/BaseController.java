package com.ydq.ihelp.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.alibaba.druid.util.StringUtils;
import com.ydq.ihelp.common.Code;
import com.ydq.ihelp.common.CommonEnum;
import com.ydq.ihelp.pojo.BaseResponse;
import com.ydq.ihelp.pojo.SelfRequest;
import com.ydq.ihelp.service.IRequestHistoryService;

@Controller
public class BaseController {
	protected  Logger logger = Logger.getLogger(getClass());
	@Autowired
	private IRequestHistoryService mRequestHistoryService;
	
	/**
	 * 验证请求，并记录请求记录表
	 * @param request
	 * @return
	 */
	protected BaseResponse validateRequest(SelfRequest request) throws Exception{
		BaseResponse response = new BaseResponse();
		logger.debug(request.toString());
		//必输参数不全
		if(StringUtils.isEmpty(request.getUserid()) || StringUtils.isEmpty(request.getIp()) || StringUtils.isEmpty(request.getToken()))
		{
			response.setCode(Code.C_TY_NOPAM);
			return response;
		}
		
		int result = mRequestHistoryService.validateRequest(request);
		//黑名单用户
		if(result == CommonEnum.ENUM_USER_STATUS.BLACK){
			response.setCode(Code.C_U_BLACK);
			return response;
		}//用户不存在
		else if(result == CommonEnum.ENUM_USER_STATUS.NONE){
			response.setCode(Code.C_U_NONE);
			return response;
		}
		
		//用户被删除了
		else if(result == CommonEnum.ENUM_USER_STATUS.DELETED){
			response.setCode(Code.C_U_DELETED);
			return response;
		}
		//客户端接口请求超时
		else if(result == CommonEnum.ENUM_URL_STATUS.TIMEOUT){
			response.setCode(Code.C_SERVICE_URL_TIMEOUT);
			return response;
		}
		//客户端接口请求url异常
		else if(result == CommonEnum.ENUM_URL_STATUS.WARRING){
			response.setCode(Code.C_SERVICE_URL_WARRING);
			return response;
		}
		
		
		//继续子类中的参数传递
		
		return response;
	}
}
