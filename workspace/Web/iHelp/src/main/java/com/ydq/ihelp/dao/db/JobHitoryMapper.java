package com.ydq.ihelp.dao.db;

import com.ydq.ihelp.model.db.JobHitory;

public interface JobHitoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JobHitory record);

    int insertSelective(JobHitory record);

    JobHitory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JobHitory record);

    int updateByPrimaryKey(JobHitory record);
}