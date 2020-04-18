package com.ityoudream.model;

import lombok.Data;

import java.io.Serializable;


@Data
public class User implements Serializable {
    /**
    * id
    */
    private Integer id;

    /**
    * 名称
    */
    private String nickname;

    /**
    * 年龄
    */
    private Integer age;

    /**
    * 性别
    */
    private Integer sex;
}