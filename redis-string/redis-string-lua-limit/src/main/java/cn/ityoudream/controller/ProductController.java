package cn.ityoudream.controller;

import cn.ityoudream.utils.IpUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * @description: 防刷
 * @author: zhoubiao
 * @create: 2020-05-18 21:29
 **/
@RestController
@Api(tags = {"redis-lua防刷测试"})
@Slf4j
public class ProductController {
    @Resource
    private DefaultRedisScript<Long> limitScript;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping(value = "/productlist")
    @ApiOperation("查询商品")
    public String productList(HttpServletRequest request) {
        //获取请求ip
        String ip = IpUtils.getIpAddr(request);
        //设置redis 的key
        List<String> keys = Arrays.asList("pruductAPI:" + ip);
        //执行lua脚本，execute方法有3个参数，第一个参数是lua脚本对象，第二个是key列表，第三个是lua的参数数组
        //30代表30秒 ，10代表超过10次，也就是说同个ip 30秒内不能超过10次请求
        Long n = this.stringRedisTemplate.execute(this.limitScript, keys, "30", "10");
        String result="";
        //非法请求
        if (n == 0) {
            result= "非法请求";
        } else {
            result= "返回商品列表";
        }
        log.info("ip={}请求结果：{}", ip,result);
        return result;
    }

}
