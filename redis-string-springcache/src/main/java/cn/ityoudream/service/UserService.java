package cn.ityoudream.service;

import cn.ityoudream.model.Users;

/**
 * @description: UserService
 * @author: zhoubiao
 * @create: 2020-05-10 11:28
 **/
public interface UserService {
    /**
     * 根据用户id查询用户信息
     * @param id
     * @return Users
     */
    Users findUserById(Integer id);

    /**
     * 根据传入的Users对象来更新用户信息
     * @param obj
     * @return
     */
    Users updateUser(Users obj);

    /**
     * 根据传入的用户id来删除用户信息
     * @param id
     */
    void deleteUser(Integer id);
}
