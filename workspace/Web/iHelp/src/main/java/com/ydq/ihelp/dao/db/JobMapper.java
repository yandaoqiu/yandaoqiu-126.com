package com.ydq.ihelp.dao.db;

import java.util.List;

import com.ydq.ihelp.model.db.Job;

public interface JobMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Job record);

    int insertSelective(Job record);

    Job selectByPrimaryKey(Integer id);
    
    List<Job> selectAll();

    int updateByPrimaryKeySelective(Job record);

    int updateByPrimaryKey(Job record);
}