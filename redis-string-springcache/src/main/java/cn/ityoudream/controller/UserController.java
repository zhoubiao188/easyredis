package com.ityoudream.controller;

import com.ityoudream.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "springcache测试")
public class UserController {
    @Autowired
    private UserService userService;
    @ApiOperation("查询")
    @GetMapping("/findByUserId/{id}")
    public ResponseEntity<Object> findByUserId(@PathVariable int id) {
        RedisStringMybatisVo result = userService.findByUserId(id);
        return new ResponseEntity<Object>(result, HttpStatus.OK);
    }

    @PutMapping("/updateUser")
    @ApiOperation("更新")
    public void  updateUser(@RequestBody RedisStringMybatisVo uservo) {
        RedisStringMybatis user = new RedisStringMybatis();
        BeanUtils.copyProperties(uservo, user);
         userService.updateUser(user);
    }
}
