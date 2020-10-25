package cn.ityoudream.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: UsersDTO
 * @author: zhoubiao
 * @create: 2020-05-10 12:02
 **/
@Data
public class UsersDTO {
    @ApiModelProperty("用户id")
    private Integer id;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String nickname;

    /**
     * 性别 0=女 1=男
     */
    @ApiModelProperty("性别[0女，1男]")
    private Integer sex;

    /**
     * 年龄
     */
    @ApiModelProperty("年龄")
    private Integer age;

    /**
     * 0正常, 1删除
     */
    @ApiModelProperty("逻辑删除[0正常，1删除]")
    private Integer flag;
}
