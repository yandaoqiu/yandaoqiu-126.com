package com.ydq.ihelp.dao.db;

import java.util.List;

import org.apache.ibatis.annotations.Param;

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
    
    /**
     * 更新用户状态
     * @param user_id
     * @param user_status
     * @return
     */
    int updateUserStatus(@Param("user_id") String user_id,@Param("user_status")int user_status);
}