package com.ydq.ihelp.cache;


import java.util.Map;

import com.ydq.ihelp.job.BlackUserJob;
import com.ydq.ihelp.job.JobManager;
import com.ydq.ihelp.model.db.User;
import com.ydq.ihelp.pojo.black.BlackUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


/**
 * Created by yandaoqiu on 2016/8/26.
 */
public class BlackUserCache
{
    private static BlackUserCache pc = null;
    private Map<String,BlackUser> personInfos;
    private BlackUserCache()
    {
        personInfos = new HashMap<String,BlackUser>();
    }
    public synchronized static BlackUserCache getInstance()
    {
        if (pc == null)
        {
            pc = new BlackUserCache();
            JobManager.getInstance().addJob(new BlackUserJob());
        }
        return pc;
    }
    
    /**
     * 检查黑名单数量
     */
    public long getBlackUserSize()
    {
    	synchronized (personInfos){
    		return personInfos.size();
    	}
    }
    
    /**
     * 获取缓存中的用户信息
     * @param tel
     * @return
     */
    public BlackUser isBlack(String userid)
    {
    	synchronized (personInfos){
    		return personInfos.get(userid);
    	}
    }

    /**
     * 加入黑名單
     * @param personInfoCode
     */
    public void put(User user)
    {
        if (user == null)return;
        synchronized (personInfos){
        	personInfos.put(user.getUserId() ,new BlackUser(user));
        }
    }

    /**
     * 取消黑名單
     */
    public void remove(String userid)
    {
    	 if (userid == null)return;
    	 synchronized (personInfos) {
    		 personInfos.remove(userid);
		}
    }
    
    /**
     * 刷新 频率1s
     */
    public void refush(){
    	synchronized (personInfos){
    		List<String> needRemoveUser = new ArrayList<String>();
    		Iterator it = personInfos.entrySet().iterator();
    		while (it.hasNext()) {
    			String key = (String) it.next();
    			BlackUser blackUser = personInfos.get(key);
    			blackUser.blackTime -= 1000;
    			if(blackUser.blackTime <= 0){
    				needRemoveUser.add(key);
    			}
			}
    		personInfos.remove(needRemoveUser);
    	}
    }
    
}
