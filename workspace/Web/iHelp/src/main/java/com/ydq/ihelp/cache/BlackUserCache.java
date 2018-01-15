package com.ydq.ihelp.cache;


import java.util.Map;

import com.ydq.ihelp.model.db.User;

import java.util.HashMap;
import java.util.List;


/**
 * Created by yandaoqiu on 2016/8/26.
 */
public class BlackUserCache
{
    private static BlackUserCache pc = null;
    private Map<String,User> personInfos;
    private BlackUserCache()
    {
        personInfos = new HashMap<String,User>();
    }
    public synchronized static BlackUserCache getInstance()
    {
        if (pc == null)
        {
            pc = new BlackUserCache();
        }
        return pc;
    }
    
    /**
     * 检查黑名单数量
     */
    public long getBlackUserSize()
    {
    	return personInfos.size();
    }
    
    /**
     * 获取缓存中的用户信息
     * @param tel
     * @return
     */
    public User isBlack(String userid)
    {
        return personInfos.get(userid);
    }

    /**
     * 加入黑名單
     * @param personInfoCode
     */
    public void put(User user)
    {
        if (user == null)return;
        personInfos.put(user.getUserId() ,user);
    }

    /**
     * 取消黑名單
     */
    public void remove(String userid)
    {
    	 if (userid == null)return;
    	personInfos.remove(userid);
    }
}
