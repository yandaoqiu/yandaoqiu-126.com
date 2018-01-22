package com.ydq.ihelp.pojo.black;

import com.ydq.ihelp.model.db.User;

public class BlackUser {
	public User user;
	
	//默认3分钟 单位是s
	public long blackTime = 3 * 60;
	
	public BlackUser(User user){
		this.user = user;
	}

}
