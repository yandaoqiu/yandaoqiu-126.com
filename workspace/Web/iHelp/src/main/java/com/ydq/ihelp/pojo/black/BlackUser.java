package com.ydq.ihelp.pojo.black;

import com.ydq.ihelp.model.db.User;

public class BlackUser {
	public User user;
	
	//默认3分钟
	public long blackTime = 3 * 60 * 60;
	
	public BlackUser(User user){
		this.user = user;
	}

}
