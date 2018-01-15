package com.ydq.ihelp.dao.db;

import com.ydq.ihelp.model.db.TypeItem;

public interface TypeItemMapper {
    int deleteByPrimaryKey(Integer typeId);

    int insert(TypeItem record);

    int insertSelective(TypeItem record);

    TypeItem selectByPrimaryKey(Integer typeId);

    int updateByPrimaryKeySelective(TypeItem record);

    int updateByPrimaryKey(TypeItem record);
}