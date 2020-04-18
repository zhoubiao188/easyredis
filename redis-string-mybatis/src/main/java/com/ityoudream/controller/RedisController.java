package com.ityoudream.controller;

import com.ityoudream.dto.UserDTO;
import com.ityoudream.service.RedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@Api(tags = "redis增删改查")
public class RedisController {
    @Autowired
    private RedisService redisService;
    @GetMapping("/create")
    @ApiOperation("数据库初始化100条数据")
    public void initData() {
        for (int i = 0; i < 100; i ++) {
            String tempName = "girl" + i;
            Random r = new Random();
            int n = r.nextInt(2);
            int age = r.nextInt(6) + 5;
            UserDTO redisDto = new UserDTO();
            redisDto.setSex(n);
            redisDto.setAge(age);
            redisDto.setNickname(tempName);
            redisService.createUser(redisDto);
        }
    }

    @GetMapping("/findByUser/{id}")
    @ApiOperation("查询数据")
    public UserDTO findByUserId(@PathVariable int id) {
        UserDTO redisStringMybatisDTO = redisService.selectUserById(id);
        return redisStringMybatisDTO;
    }

    @PostMapping("/updateUser")
    @ApiOperation("更新数据")
    public void updateUser(@RequestBody UserDTO userDTO) {
        this.redisService.updateUser(userDTO);
    }

}
