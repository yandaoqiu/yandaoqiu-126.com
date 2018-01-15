package com.ydq.ihelp.dao.db;

import com.ydq.ihelp.model.db.JobComment;

public interface JobCommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JobComment record);

    int insertSelective(JobComment record);

    JobComment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JobComment record);

    int updateByPrimaryKey(JobComment record);
}