package cn.ityoudream.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @description: 商品类
 * @author: zhoubiao
 * @create: 2020-05-10 20:21
 **/
@Data
public class ProductDTO {
    @ApiModelProperty("商品id")
    private Long id;

    @ApiModelProperty("商品名称")
    private String name;

    @ApiModelProperty("商品价格")
    private double price;

    @ApiModelProperty("商品详情")
    private List<DetailDTO> detailDTOS;
}
