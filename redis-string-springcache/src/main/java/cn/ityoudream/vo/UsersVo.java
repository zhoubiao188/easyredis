package cn.ityoudream.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: UserVo
 * @author: zhoubiao
 * @create: 2020-05-10 11:27
 **/
@Data
public class UsersVo {
    private Integer id;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名称")
    private String nickname;

    /**
     * 性别 0=女 1=男
     */
    @ApiModelProperty("性别")
    private Integer sex;

    /**
     * 年龄
     */
    @ApiModelProperty("年龄")
    private Integer age;

    /**
     * 0正常, 1删除
     */
    @ApiModelProperty("逻辑删除值[0正常,1已删除]")
    private Integer flag;
}
