package cn.ityoudream.model;

import lombok.Data;

/**
    * 用户表
    */
@Data
public class Users {
    private Integer id;

    /**
    * 用户名
    */
    private String nickname;

    /**
    * 性别 0=女 1=男 
    */
    private Integer sex;

    /**
    * 年龄
    */
    private Integer age;

    /**
    * 0正常, 1删除
    */
    private Integer flag;
}