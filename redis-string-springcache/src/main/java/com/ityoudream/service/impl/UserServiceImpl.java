package com.ityoudream.service.impl;

import com.ityoudream.mapper.RedisStringMybatisMapper;
import com.ityoudream.model.RedisStringMybatis;
import com.ityoudream.service.UserService;
import com.ityoudream.vo.RedisStringMybatisVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = {"user"})

public class UserServiceImpl implements UserService {
    @Autowired
    private RedisStringMybatisMapper redisDao;

    @Cacheable("#id")
    @Override
    public RedisStringMybatisVo findByUserId(Integer id) {
        RedisStringMybatis user = this.redisDao.selectByPrimaryKey(id);
        RedisStringMybatisVo userVo = new RedisStringMybatisVo();
        BeanUtils.copyProperties(user, userVo);
        return userVo;
    }

    @CachePut(key = "#obj.id")
    @Override
    public RedisStringMybatis updateUser(RedisStringMybatis obj) {
        this.redisDao.updateByPrimaryKeySelective(obj);
        return this.redisDao.selectByPrimaryKey(obj.getId());
    }

    @Override
    public void deleteByUserId(Integer id) {

    }
}
