package com.ydq.ihelp.dao.db;

import java.util.List;

import com.ydq.ihelp.model.db.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    
    /**
     * 查询黑名单用户群,只查询关键字段
     * @return
     */
    List<User> selectUserListByStatus(int status);
    
    /**
     * 查询指定用户
     * @param userid
     * @return
     */
    User selectUserByUseroid(String userid);
}