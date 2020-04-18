package com.ityoudream.service;

import com.ityoudream.model.RedisStringMybatis;
import com.ityoudream.vo.RedisStringMybatisVo;

public interface UserService {
    RedisStringMybatisVo findByUserId (Integer id);

    RedisStringMybatis updateUser(RedisStringMybatis userVo);

    void deleteByUserId(Integer id);
}
