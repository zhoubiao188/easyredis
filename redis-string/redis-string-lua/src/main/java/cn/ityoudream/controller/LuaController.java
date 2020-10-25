package cn.ityoudream.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @description: lua测试
 * @author: zhoubiao
 * @create: 2020-05-17 19:28
 **/
@RestController
@Slf4j
@Api(tags = {"使用lua优化redis"})
public class LuaController {
    @Resource
    private DefaultRedisScript<Long> compareAndSetScript;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 修改用户名称
     */
    @GetMapping(value = "/updateuser")
    @ApiOperation("优化之前")
    public void updateUser(Integer uid,String uname) {
        String key="user:"+uid;
        //优化点：第一次发送redis请求
        String old=this.stringRedisTemplate.opsForValue().get(key);
        if(StringUtils.isEmpty(old)){
            //优化点：第二次发送redis请求
            this.stringRedisTemplate.opsForValue().set(key,uname);
            return;
        }
        if(old.equals(uname)){
            log.info("{}不用修改", key);
        }else{
            log.info("{}从{}修改为{}", key,old,uname);
            //优化点：第二次发送redis请求
            this.stringRedisTemplate.opsForValue().set(key,uname);
        }
        /*
        以上代码，看似简单，但是在高并发的情况下，还是有一点性能瓶颈，在性能方面主要是发送了2次redis请求。
        那如何优化呢？
        我们可以采用lua技术，把2次redis请求合成一次。
         */
    }


    @GetMapping(value = "/updateuserlua")
    @ApiOperation("优化之后")
    public void updateUserLua(Integer uid,String uname) {
        String key="user:"+uid;
        //设置redis的key
        List<String> keys = Arrays.asList(key);
        //执行lua脚本，execute方法有3个参数，第一个参数是lua脚本对象，第二个是key列表，第三个是lua的参数数组
        Long n = this.stringRedisTemplate.execute(this.compareAndSetScript, keys, uname);
        if (n == 0) {
            log.info("{}不用修改", key);
        } else {
            log.info("{}修改为{}", key,uname);
        }
    }

}
