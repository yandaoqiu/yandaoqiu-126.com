package com.ydq.ihelp.dao.db;

import com.ydq.ihelp.model.db.JobScore;

public interface JobScoreMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JobScore record);

    int insertSelective(JobScore record);

    JobScore selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JobScore record);

    int updateByPrimaryKey(JobScore record);
}