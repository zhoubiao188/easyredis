package com.ityoudream.mapper;

import com.ityoudream.model.RedisStringMybatis;

public interface RedisStringMybatisMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RedisStringMybatis record);

    int insertSelective(RedisStringMybatis record);

    RedisStringMybatis selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RedisStringMybatis record);

    int updateByPrimaryKey(RedisStringMybatis record);
}