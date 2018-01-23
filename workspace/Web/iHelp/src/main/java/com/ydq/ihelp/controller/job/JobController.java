package com.ydq.ihelp.controller.job;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ydq.ihelp.common.Code;
import com.ydq.ihelp.controller.BaseController;
import com.ydq.ihelp.pojo.BaseResponse;
import com.ydq.ihelp.pojo.SelfRequest;
import com.ydq.ihelp.service.IJobService;

@Controller
public class JobController extends BaseController {
	
	@Autowired
	private IJobService mJobService;
	@RequestMapping(value = "getJobItem", method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse requestLogin(HttpServletRequest request,String location,int start,int length) throws Exception
	{
		SelfRequest SR = new SelfRequest(request,getClass());
		BaseResponse response = this.validateRequest(SR);
		
		if(response.getCode() != Code.C_SUCCESS)return response;
		
		
		return mJobService.getItem(SR, location, start, length);
	}
}
