package cn.ityoudream.service;

import cn.ityoudream.model.Users;

/**
 * @description: UserService
 * @author: zhoubiao
 * @create: 2020-05-10 12:04
 **/
public interface UserSerivce {
    /**
     * 根据传入的Users对象创建用户
     * @param obj
     */
   void createUser(Users obj);


    /**
     * 根据传入的Users对象更新用户信息
     * @param obj
     */
   void updateUser(Users obj);

    /**
     * 根据用户传入的用户id查询用户信息
     * @param userId
     * @return
     */
   Users findByUserId(Integer userId);
}
