package com.ydq.ihelp.dao.db;

import java.util.List;

import com.ydq.ihelp.model.db.RequestHistory;

public interface RequestHistoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RequestHistory record);

    int insertSelective(RequestHistory record);

    RequestHistory selectByPrimaryKey(Integer id);
    
    RequestHistory selectHistory(String ip);
    
    List<RequestHistory> selectHistoryBy1Min(String ip);

    int updateByPrimaryKeySelective(RequestHistory record);

    int updateByPrimaryKey(RequestHistory record);
}