package com.ydq.ihelp.pojo;

import javax.servlet.http.HttpServletRequest;

import com.ydq.ihelp.common.PublicUtil;

/**
 * 
 * @author yandaoqiu
 *
 */
public class SelfRequest {

	private String userid;
	private String token;
	private String ip;
	private String time;
	private String deviceid;
	private String action;
	
	public SelfRequest(HttpServletRequest request,Class<?> clz) throws Exception {
		String ip = PublicUtil.getIpAddress(request);
		String time = PublicUtil.getCurentTime();
		String userid = request.getParameter("userid");
		String token = request.getParameter("token");
		String deviceid = request.getParameter("deviceid");

		setIp(ip);
		setTime(time);
		setUserid(userid);
		setToken(token);
		setDeviceid(deviceid);
		setAction(clz.getName()+"->"+Thread.currentThread().getStackTrace()[1].getMethodName());
	}
	
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	public String getDeviceid() {
		return deviceid;
	}
	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}
	public String toString(){
		return "Time->"+time+" ip->"+ip+" token->"+token+" userid->"+userid+" action->"+action;
	}
}
