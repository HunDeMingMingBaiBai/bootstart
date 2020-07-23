package com.ssd.start.controller;

import com.ssd.start.entity.RbacUser;
import com.ssd.start.pojo.ResultObject;
import com.ssd.start.service.UserService;
import com.ssd.start.util.MD5Utils;
import com.ssd.start.util.TokenUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.TimeUnit;

/**
 * @author WHD
 * @date 2020/7/23 15:53
 */
@Api("用户登陆相关接口")
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    private static String CUSTOM_KEY = "custom_";

    @PostMapping("/")
    @ResponseBody
    @ApiOperation("用户登陆接口")
    public ResultObject login(@RequestBody RbacUser user){
        RbacUser userInDB = userService.selectByUserName(user.getUserName());
        if (StringUtils.equals(userInDB.getPassword(), MD5Utils.md5Encode(user.getPassword()))) {
            String token = TokenUtils.generateToken(CUSTOM_KEY);
            redisTemplate.opsForValue().set(token, userInDB, 60, TimeUnit.MINUTES);
            return ResultObject.success(token);
        } else {
            return ResultObject.error("用户名或者密码不正确");
        }
    }

}
