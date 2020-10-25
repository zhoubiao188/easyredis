package cn.ityoudream.controller;

import cn.ityoudream.model.Users;
import cn.ityoudream.service.UserService;
import cn.ityoudream.vo.UsersVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "springcache测试")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @ApiOperation("根据用户id查询")
    @GetMapping("/findByUserId/{id}")
    public UsersVo findByUserId(@PathVariable int id) {
        Users user = userService.findUserById(id);
        UsersVo usersVo = new UsersVo();
        BeanUtils.copyProperties(user, usersVo);
        return usersVo;
    }

    @ApiOperation("更新某条数据")
    @PutMapping("/updateUser")
    public void  updateUser(@RequestBody UsersVo obj) {
        Users users = new Users();
        BeanUtils.copyProperties(obj, users);
        userService.updateUser(users);
    }

    @ApiOperation("根据id删除某条数据")
    @DeleteMapping("/del/{id}")
    public void deleteByUserId(@PathVariable int id) {
        this.userService.deleteUser(id);

    }
}
