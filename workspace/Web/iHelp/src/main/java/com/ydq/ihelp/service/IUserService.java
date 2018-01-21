package com.ydq.ihelp.service;

import java.util.List;

import com.ydq.ihelp.model.db.User;
import com.ydq.ihelp.pojo.SelfRequest;
import com.ydq.ihelp.pojo.user.UserResponse;

/**
 * 用户操作服务
 * @author yandaoqiu
 *
 */
public interface IUserService 
{
	/**
	 * 登录接口
	 * @param request
	 * @return
	 */
	UserResponse login(SelfRequest request);
	
	/**
	 * 注册
	 * @param request
	 * @return
	 */
	User register(SelfRequest request);
	
	
	/**
	 * 获取黑名单用户
	 * @return
	 */
	List<User> getBlackUsers();
	
	
	/**
	 * 获取用户
	 * @param request
	 * @return
	 */
	User getUser(SelfRequest request);
	
	/**
	 * 获取用户
	 * @param String userid
	 * @return
	 */
	User getUser(String userid);
	
}
