package cn.ityoudream.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: 商品详情
 * @author: zhoubiao
 * @create: 2020-05-10 20:22
 **/
@Data
public class DetailDTO {
    @ApiModelProperty("商品id")
    private Long productId;

    @ApiModelProperty("商品图片")
    private String productImg;

}
