package cn.ityoudream.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

/**
 * @description: 加载lua脚本
 * @author: zhoubiao
 * @create: 2020-05-17 19:22
 **/
@Configuration
public class LuaConfig {
    @Bean
    public DefaultRedisScript<Long> luaScript() {
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("lua/luaScript.lua")));
        redisScript.setResultType(Long.class);
        return redisScript;
    }
}
