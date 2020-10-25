package cn.ityoudream.service.impl;

import cn.ityoudream.mapper.UsersMapper;
import cn.ityoudream.model.Users;
import cn.ityoudream.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @description: UserServiceImpl
 * @author: zhoubiao
 * @create: 2020-05-10 11:34
 **/
@Slf4j
@CacheConfig(cacheNames = {"user"})
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UsersMapper usersMapper;

    @Cacheable(key = "#id")
    @Override
    public Users findUserById(Integer id) {
        return this.usersMapper.selectByPrimaryKey(id);
    }

    @CachePut(key = "#obj.id")
    @Override
    public Users updateUser(Users obj) {
        this.usersMapper.updateByPrimaryKeySelective(obj);
        return this.usersMapper.selectByPrimaryKey(obj.getId());
    }

    @CacheEvict(key = "#id")
    @Override
    public void deleteUser(Integer id) {
        Users user = new Users();
        user.setId(id);
        user.setFlag(1);
        this.usersMapper.updateByPrimaryKeySelective(user);
    }
}
