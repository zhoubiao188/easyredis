package cn.ityoudream;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.ityoudream.mapper")
public class RedisStringSpringcacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisStringSpringcacheApplication.class, args);
    }

}
