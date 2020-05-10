package cn.ityoudream.service.impl;

import cn.ityoudream.mapper.UsersMapper;
import cn.ityoudream.model.Users;
import cn.ityoudream.service.UserSerivce;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

/**
 * @description: UserServiceImpl
 * @author: zhoubiao
 * @create: 2020-05-10 12:08
 **/
@Slf4j
@Service
public class UserServiceImpl implements UserSerivce {
    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    private static final String CACHE_KEY_USER = "user:";
    @Override
    public void createUser(Users obj) {
        /**
         * 通过传入的obj对象，向数据库插入数据
         */
        this.usersMapper.insertSelective(obj);

        /**
         * 组装Redis的key
         * redis中一般key都是xxxx:xxx这种格式的
         */
        String key = CACHE_KEY_USER + obj.getId();

        /**
         * String类型使用opsForValue来操作
         * 这里的set相当于redis的的set的命令
         */
        obj = this.usersMapper.selectByPrimaryKey(obj.getId());
        redisTemplate.opsForValue().set(key,obj);
    }

    @Override
    public void updateUser(Users obj) {
        /**
         * 更新先更新数据库的数据，然后再缓存
         */
        this.usersMapper.updateByPrimaryKeySelective(obj);

        /**
         * 组装Rdis的key
         */
        String key = CACHE_KEY_USER + obj.getId();

        /**
         * 先将数据查询出来，然后再更新到redis中
         * 这里注意的是redis没有update命令，都是重新设置值
         */
        obj = this.usersMapper.selectByPrimaryKey(obj.getId());
        redisTemplate.opsForValue().set(key, obj);
    }

    @Override
    public Users findByUserId(Integer userId) {
        /**
         * 这里是获取redis中所有的数据集合
         */
        ValueOperations<String, Users> operations = redisTemplate.opsForValue();

        /**
         * 组装Redis的key
         */
        String key = CACHE_KEY_USER + userId;

        /**
         * 先去redis中查询，如果没有，再到数据库中去查询
         */
        Users user =  operations.get(key);

        if (user == null) {
            user = this.usersMapper.selectByPrimaryKey(userId);
            /**
             * 查询到数据后，再次将数据缓冲到redis中
             */
            operations.set(key, user);
        }

        return user;
    }
}
