package cn.ityoudream.controller;

import cn.ityoudream.dto.ProductDTO;
import cn.ityoudream.service.ProductService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 模拟商品生成
 * @author: zhoubiao
 * @create: 2020-05-10 20:31
 **/
@Api(tags = {"全局分布式唯一商品id"})
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @PostMapping("/create")
    public void createProduct(@RequestBody ProductDTO productDTO) {
        productService.createProductOrder(productDTO);
    }
}
