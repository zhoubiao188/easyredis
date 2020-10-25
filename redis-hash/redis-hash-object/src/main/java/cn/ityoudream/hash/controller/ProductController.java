package cn.ityoudream.hash.controller;

import cn.ityoudream.hash.dto.ProductDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {

    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping(value = "/create")
    public void create(ProductDTO obj) {
        //TODO 先进db

        //创建商品，先把数据添加到数据库，再存入redis
        String key="product:"+1000;
        //将Object对象里面的属性和值转化成Map对象
        Map<String, Object> map=this.objectToMap(obj);
        //批量put操作 putAll 等于 hmset命令
        //String数据结构opsForValue  hash数据结构opsForHash
        this.redisTemplate.opsForHash().putAll(key,map);

        Object name=this.redisTemplate.opsForHash().get(key,"name");
        log.info("name={}",name);

        Object price=this.redisTemplate.opsForHash().get(key,"price");
        log.info("price={}",price);

        Object detail=this.redisTemplate.opsForHash().get(key,"detail");
        log.info("detail={}",detail);

    }

    @PostMapping(value = "/addPrice")
    public void addPrice(int id,int price) {
        String key="product:"+id;
        //商品价格涨价 increment等于  hincrby命令
        this.redisTemplate.opsForHash().increment(key,"price",price);
        Object price2=this.redisTemplate.opsForHash().get(key,"price");
        log.info("price={}",price2);
    }

    /**
     * 将Object对象里面的属性和值转化成Map对象
     * 这里把Object转化为Map使用的是反射实现，注意一般不推荐
     */
    public  Map<String, Object> objectToMap(Object obj)  {
        Map<String, Object> map = new HashMap<String,Object>();
        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object value = null;
            try {
                value = field.get(obj);
            } catch (IllegalAccessException e) {
                log.error(e.getMessage());
            }
            map.put(fieldName, value);
        }
        return map;
    }
}
