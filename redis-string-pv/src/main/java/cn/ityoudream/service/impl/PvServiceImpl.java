package cn.ityoudream.service.impl;

import cn.ityoudream.service.PvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @description: PvServiceImpl
 * @author: zhoubiao
 * @create: 2020-05-10 15:53
 **/
@Service
public class PvServiceImpl implements PvService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static final String CACHE_ARTICLE = "article:";
    @Override
    public long view(Integer id) {
        /**
         * 使用redis的计数器命令increment(incr)
         */
        String  key = CACHE_ARTICLE + id;
        return this.stringRedisTemplate.opsForValue().increment(key);
    }
}
