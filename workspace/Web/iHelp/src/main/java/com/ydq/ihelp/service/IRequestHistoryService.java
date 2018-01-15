package com.ydq.ihelp.service;

import com.ydq.ihelp.model.db.User;
import com.ydq.ihelp.pojo.SelfRequest;

/**
 * 请求记录服务
 * @author yandaoqiu
 *
 */
public interface IRequestHistoryService
{
	/**
	 * 获取黑名单
	 * @param request
	 * @return
	 */
	User getBlackUser(SelfRequest request);
	
	/**
	 * 验证请求，合法进入历史记录，不合法 进入
	 * @param request
	 * @return CommonEnum
	 */
	int validateRequest(SelfRequest request);
	
	
}
