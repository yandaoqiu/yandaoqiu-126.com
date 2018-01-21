package com.ydq.ihelp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ydq.ihelp.cache.BlackUserCache;
import com.ydq.ihelp.common.CommonEnum;
import com.ydq.ihelp.common.PublicUtil;
import com.ydq.ihelp.dao.db.RequestHistoryMapper;
import com.ydq.ihelp.model.db.RequestHistory;
import com.ydq.ihelp.model.db.User;
import com.ydq.ihelp.pojo.SelfRequest;
import com.ydq.ihelp.pojo.black.BlackUser;
import com.ydq.ihelp.service.IRequestHistoryService;
import com.ydq.ihelp.service.IUserService;
@Service("mRequestHistoryService")
public class IRequestHistoryServiceImpl implements IRequestHistoryService {

	@Autowired
	private IUserService mUserService;
	
	
	@Autowired
	private RequestHistoryMapper mRequestHistoryMapper;
	
	
	@Override
	public User getBlackUser(SelfRequest request) {
		
		//查黑名单缓存表,如果缓存为空则拉取一遍数据库。  //TODO 大数据量 隐患：黑名单用户太多,一次加载内存会挂
		long blackSize = BlackUserCache.getInstance().getBlackUserSize();
		if(blackSize == 0){
			List<User> blackUsers = mUserService.getBlackUsers();
			if(blackUsers != null && !blackUsers.isEmpty()){
				for (User user : blackUsers) {
					BlackUserCache.getInstance().put(user);
				}
			}
		}
		
		BlackUser blackUser = BlackUserCache.getInstance().isBlack(request.getUserid());
		if(blackUser == null)return null;
		return blackUser.user;
	}

	/**
	 * 验证本次请求是否合法
	 * @param record
	 * @return
	 */
	private boolean validateRequest(String userid,RequestHistory record){
		//查询请求记录表，看下上次请求时间，ip
		//规则就是 连续1分钟内访问60次以上就视为恶意攻击，15分钟后再次允许访问，一天
		//查询上一次的调用时间
		//检查 最近的1分钟内的请求，如果数量大于60 则封ip 15分钟
		long count = mRequestHistoryMapper.selectHistoryCountBy1Min(record.getIp());
		if(count > 60){
			//添加到黑名单
			BlackUserCache.getInstance().put(mUserService.getUser(userid));
			return false;
		}
		return true;
	}
	
	@Override
	public int validateRequest(SelfRequest request) {
		
		int status = CommonEnum.ENUM_USER_STATUS.NORMAL;
		User user = getBlackUser(request);
		if(user == null){
			//不是黑名单
			//检查url请求是否合法
			RequestHistory record = new RequestHistory();
			record.setDeviceId(request.getDeviceid());
			record.setDateTime(request.getTime());
			record.setIp(request.getIp());
			record.setToken(request.getToken());
			record.setUserId(request.getUserid());
			record.setAction(request.getAction());
			if(validateRequest(request.getUserid(),record)){
				//插入表,继续执行
				this.mRequestHistoryMapper.insert(record);
				//请求正常
				status = CommonEnum.ENUM_URL_STATUS.NORMAL;
			}else{
				//直接返回 黑名单
				return CommonEnum.ENUM_USER_STATUS.BLACK;
			}
			
			if(status != CommonEnum.ENUM_URL_STATUS.NORMAL)return status;
			
			user = mUserService.getUser(request);
			if(user == null){
				//用户不存在
				status = CommonEnum.ENUM_USER_STATUS.NONE;
			}else{
				//查看用户是不是被删了
				if(user.getUser_status() == CommonEnum.ENUM_USER_STATUS.DELETED){
					status = user.getUser_status();
				}
				else{
					//正常用户
					status = CommonEnum.ENUM_USER_STATUS.NORMAL;
				}
			}
		}else{
			//黑名单用户，返回
			status = CommonEnum.ENUM_USER_STATUS.BLACK;
		}
		return status;
	}

}
