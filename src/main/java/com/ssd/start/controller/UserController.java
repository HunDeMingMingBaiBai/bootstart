package com.ssd.start.controller;

import com.ssd.start.entity.RbacUser;
import com.ssd.start.service.UserService;
import com.ssd.start.util.MD5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author WHD
 * @date 2020/7/23 14:12
 */
@Api("用户管理相关接口")
@RequestMapping("/user")
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    @ResponseBody
    @ApiOperation("根据id查询用户的接口")
    @ApiImplicitParam(name = "id", value = "用户id", required = true)
    public RbacUser getUserById(@PathVariable Long id) {
        RbacUser user = userService.getUserById(id);
        return user;
    }

    @PostMapping("/")
    @ResponseBody
    @ApiOperation("添加用户的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", defaultValue = "李四", required = true),
            @ApiImplicitParam(name = "password", value = "用户密码", defaultValue = "111111", required = true)
    }
    )
    public Integer addUser(@RequestParam(required = true) String username, @RequestParam(required = true) String password) {
        RbacUser user = new RbacUser(username, MD5Utils.md5Encode(password));
        Integer result = userService.insertUser(user);
        return result;
    }

    @PutMapping("/")
    @ResponseBody
    @ApiOperation("根据id更新用户的接口")
    public RbacUser updateUserById(@RequestBody RbacUser user) {
        int result = userService.updateUserById(user);
        return user;
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    @ApiOperation("根据id删除用户")
    @ApiImplicitParam(name = "id", value = "用户id", required = true)
    public Integer deleteUserById(@PathVariable Long id) {
        Integer result = userService.deleteUserById(id);
        return result;
    }

}
