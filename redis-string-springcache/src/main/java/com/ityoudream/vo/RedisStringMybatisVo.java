package com.ityoudream.vo;

import lombok.Data;

@Data
public class RedisStringMybatisVo {
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
