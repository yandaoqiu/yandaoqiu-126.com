package com.ydq.ihelp.dao.db;

import com.ydq.ihelp.model.db.Appeal;

public interface AppealMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Appeal record);

    int insertSelective(Appeal record);

    Appeal selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Appeal record);

    int updateByPrimaryKey(Appeal record);
}