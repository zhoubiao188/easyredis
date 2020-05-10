package cn.ityoudream.service.impl;

import cn.ityoudream.dto.DetailDTO;
import cn.ityoudream.dto.ProductDTO;
import cn.ityoudream.service.IdGeneratorService;
import cn.ityoudream.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description: ProductServiceImpl
 * @author: zhoubiao
 * @create: 2020-05-10 20:27
 **/
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private IdGeneratorService idGeneratorService;
    @Override
    public void createProductOrder(ProductDTO productDTO) {
        /**
         * 通过redis获取生成的全局分布式id
         */
        long key = this.idGeneratorService.incrementId();
        productDTO.setId(key);
        for (DetailDTO detailDTO : productDTO.getDetailDTOS()) {
            detailDTO.setProductId(key);
        }

        int mysqlTable = (int) key % 10;
        String tableName = "taobao_product_" + mysqlTable;
        log.info("插入表名{}，插入内容{},插入内容{}",tableName,key, productDTO);
    }
}
