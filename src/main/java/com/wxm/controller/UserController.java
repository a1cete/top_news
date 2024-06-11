package com.wxm.controller;

import com.alibaba.druid.util.StringUtils;
import com.wxm.pojo.User;
import com.wxm.pojo.vo.RegisterVo;
import com.wxm.pojo.vo.UidVo;
import com.wxm.service.UserService;
import com.wxm.utils.JwtHelper;
import com.wxm.utils.Result;
import com.wxm.utils.ResultCodeEnum;
import org.apache.el.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.rtf.RTFEditorKit;

@RestController
@RequestMapping("user")
@CrossOrigin//跨域
public class UserController {

    //从ioc容器中注入该类的服务层接口
    @Autowired
    private UserService userService;

    //从ioc容器中注入token工具类
    @Autowired
    private JwtHelper jwtHelper;

    //登陆接口
    @PostMapping("login")
    public Result login(@RequestBody User user) {
        Result result = userService.login(user);
        return result;
    }

    //根据token获取用户信息
    @GetMapping("getUserInfo")
    public Result getUserInfo(@RequestHeader String token) {
        Result result = userService.getUserInfo(token);
        return result;
    }

    //判断用户是否存在
    @PostMapping("checkUserName")
    public Result checkUserName(@RequestParam String username) {
        Result result = userService.checkUserName(username);

        return result;
    }


    //重置密码接口
    @PostMapping("restPassword")
    public Result checkPassword(@RequestBody RegisterVo registerVo) {
        Result result = userService.restPassword(registerVo);

        return result;
    }

    @PostMapping("regist")
    public Result regist(@RequestBody User user) {
        Result result = userService.regist(user);
        return result;
    }

    //判断用户是否登陆或者token是否过期
    @GetMapping("checkLogin")
    public Result checkLogin(@RequestHeader String token) {
        if (StringUtils.isEmpty(token) || jwtHelper.isExpiration(token)) {
            //没有传或者过期 未登录
            return Result.build(null, ResultCodeEnum.NOTLOGIN);
        }

        return Result.ok(null);

    }


    //注销账户接口
    @PostMapping("logOutAccount")
    public Result logOutAccount(@RequestBody UidVo uidVo) {
        String uid = uidVo.getUid();
        Integer i = Integer.valueOf(uid);
        System.out.println(i);
        Boolean b = userService.removeByUid(i);
        System.out.println(b);
        return Result.ok(null);
    }
}

