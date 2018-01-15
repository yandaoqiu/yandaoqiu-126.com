package com.ydq.ihelp.controller.user;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.ydq.ihelp.cache.BlackUserCache;
import com.ydq.ihelp.common.Code;
import com.ydq.ihelp.common.PublicUtil;
import com.ydq.ihelp.controller.BaseController;
import com.ydq.ihelp.model.db.User;
import com.ydq.ihelp.pojo.BaseResponse;
import com.ydq.ihelp.pojo.SelfRequest;
import com.ydq.ihelp.pojo.user.UserResponse;
import com.ydq.ihelp.service.IUserService;


@Controller
public class UserController extends BaseController
{
	@Autowired
	private IUserService mUserService;
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse requestLogin(HttpServletRequest request,String userid,String token) throws Exception
	{
		SelfRequest SR = new SelfRequest(request,getClass());
		BaseResponse response = this.validateRequest(SR);
		
		
		if(response.getCode() != Code.C_SUCCESS)return response;
		
		
		UserResponse userResponse =  mUserService.login(SR);
		
		return userResponse;
	}
	
	
	
}
