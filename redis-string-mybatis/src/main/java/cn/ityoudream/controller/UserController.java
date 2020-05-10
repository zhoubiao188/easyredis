package cn.ityoudream.controller;

import cn.ityoudream.dto.UsersDTO;
import cn.ityoudream.model.Users;
import cn.ityoudream.service.UserSerivce;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

/**
 * @description: redis缓存测试
 * @author: zhoubiao
 * @create: 2020-05-10 12:27
 **/
@Api(tags = {"redis缓存测试"})
@RestController
@RequestMapping("/user")
public class    UserController {
    @Autowired
    private UserSerivce userSerivce;

    @ApiOperation("向数据库初始化100条数据")
    @GetMapping("/init")
    public void init() {
        for(int i = 0; i < 100; i ++) {
            Random rand = new Random();
            Users users = new Users();
            String tempprefix = "girl" + i;
            users.setNickname(tempprefix);
            int n  = rand.nextInt(2);
            int age = rand.nextInt(6) + 5;
            users.setSex(n);
            users.setAge(age);
            userSerivce.createUser(users);
        }
    }

    @ApiOperation("根据用户id查询某条数据")
    @GetMapping("/findByUser/{id}")
    public UsersDTO findByUserId(@PathVariable int id) {
        Users users = this.userSerivce.findByUserId(id);
        UsersDTO usersDTO = new UsersDTO();
        BeanUtils.copyProperties(users, usersDTO);
        return usersDTO;
    }

    @ApiOperation("修改某条数据")
    @PutMapping("/updateUser")
    public void updateUser(@RequestBody UsersDTO obj) {
        Users users = new Users();
        BeanUtils.copyProperties(obj, users);
        this.userSerivce.updateUser(users);
    }
}
