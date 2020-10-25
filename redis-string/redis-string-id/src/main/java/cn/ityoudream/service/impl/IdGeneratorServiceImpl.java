package cn.ityoudream.service.impl;

import cn.ityoudream.service.IdGeneratorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @description: IdGeneratorServiceImpl
 * @author: zhoubiao
 * @create: 2020-05-10 20:17
 **/
@Slf4j
@Service
public class IdGeneratorServiceImpl implements IdGeneratorService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static final String ID_KEY = "id:generator:product";

    @Override
    public Long incrementId() {
        long key = this.stringRedisTemplate.opsForValue().increment(ID_KEY);
        return key;
    }
}
