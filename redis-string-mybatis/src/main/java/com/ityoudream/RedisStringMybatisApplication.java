package com.ityoudream;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ityoudream.mapper")
public class RedisStringMybatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisStringMybatisApplication.class, args);
	}

}
