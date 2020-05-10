//package cn.ityoudream.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//@Configuration
//public class RedisConfig {
//    @Bean
//  public   RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory connectionFactory) {
//        RedisTemplate redisTemplate = new RedisTemplate();
//        redisTemplate.setConnectionFactory(connectionFactory);
//        //创建一个json序列化对象
//        GenericJackson2JsonRedisSerializer jackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
//        //设置redis序列化方式为json
//        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
//        //设置key的序列化为String
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        //设置hash key 序列化为String
//        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
//
//        //设置hash value 为json
//        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
//
//        redisTemplate.afterPropertiesSet();
//
//        return redisTemplate;
//    }
//}
