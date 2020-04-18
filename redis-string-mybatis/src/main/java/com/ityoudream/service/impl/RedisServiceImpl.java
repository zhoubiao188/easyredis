package com.ityoudream.service.impl;

import com.ityoudream.dto.UserDTO;
import com.ityoudream.mapper.UserMapper;
import com.ityoudream.model.User;
import com.ityoudream.service.RedisService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    private UserMapper redisDao;

    @Autowired
    private RedisTemplate redisTemplate;

    private static final String CHECK_USER_KEY = "zb:";
    @Override
    public void createUser(UserDTO userDto) {
        User redisEntity =  new User();
        BeanUtils.copyProperties(userDto, redisEntity);
        redisDao.insertSelective(redisEntity);

        //缓冲key
        String key = CHECK_USER_KEY + redisEntity.getId();
        redisEntity = redisDao.selectByPrimaryKey(redisEntity.getId());
        redisTemplate.opsForValue().set(key, redisEntity);


    }

    @Override
    public void updateUser(UserDTO userDto) {
        //先更新数据库，然后在缓存到redis
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        this.redisDao.updateByPrimaryKeySelective(user);
        String key = CHECK_USER_KEY + user.getId();
        user = this.redisDao.selectByPrimaryKey(user.getId());
        this.redisTemplate.opsForValue().set(key, user);
    }

    @Override
    public UserDTO selectUserById(Integer id) {
        ValueOperations<String, User> operations = redisTemplate.opsForValue();
        //缓冲key
        String key = CHECK_USER_KEY + id;
        //去redis中去查询
        User user = operations.get(key);
        UserDTO userdto = new UserDTO();
        if (user != null) {
            BeanUtils.copyProperties(user, userdto);
        }

        if (user == null) {
            user = redisDao.selectByPrimaryKey(id);
            //将数据库查询的数据 set到redis
            operations.set(key, user);
        }
        return userdto;
    }
}
