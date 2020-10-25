package cn.ityoudream.hash.dto;

import lombok.Data;

/**
 * @author zhoubiao
 * 模拟商品累
 */
@Data
public class ProductDTO {
    private Long id;
    /**
     * 产品名称
     */
    private String name;
    /**
     * 产品价格
     */
    private Integer price;
    /**
     * 产品详情
     */
    private String detail;
}
