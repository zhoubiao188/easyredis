package cn.ityoudream.service;

import cn.ityoudream.dto.ProductDTO;

/**
 * @description: 模拟商品订单接口
 * @author: zhoubiao
 * @create: 2020-05-10 20:20
 **/
public interface ProductService {
    /**
     * 模拟创建商品
     * @param productDTO
     */
    void createProductOrder(ProductDTO productDTO);
}
