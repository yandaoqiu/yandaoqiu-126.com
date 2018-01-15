package com.ydq.ihelp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ydq.ihelp.common.Code;
import com.ydq.ihelp.common.CommonEnum;
import com.ydq.ihelp.dao.db.UserMapper;
import com.ydq.ihelp.model.db.User;
import com.ydq.ihelp.pojo.SelfRequest;
import com.ydq.ihelp.pojo.user.UserResponse;
import com.ydq.ihelp.service.IUserService;

@Service("mUserService")
public class IUserServiceImpl implements IUserService {

	@Autowired
	private UserMapper mUserMapper;
	
	@Override
	public UserResponse login(SelfRequest request) {
		
		UserResponse response = new UserResponse();
		
		User user = getUser(request);
		if(user != null){
			response.setCoentent(user);
			response.setCode(Code.C_SUCCESS);
		}
		else{
			response.setCode(Code.C_U_NONE);
		}
		return response;
	}

	@Override
	public User register(SelfRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getBlackUsers() {
		
		return mUserMapper.selectUserListByStatus(CommonEnum.ENUM_USER_STATUS.BLACK);
	}
	
	
	@Override
	public User getUser(SelfRequest request) {
		
		return mUserMapper.selectUserByUseroid(request.getUserid());
	}


}
