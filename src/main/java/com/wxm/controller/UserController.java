package com.wxm.controller;

import com.alibaba.druid.util.StringUtils;
import com.wxm.pojo.User;
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
    @Autowired
    private UserService userService;

    @Autowired
    private JwtHelper jwtHelper;

    @PostMapping("login")
    public Result login(@RequestBody User user) {
        Result result = userService.login(user);
        return result;
    }

    @GetMapping("getUserInfo")
    public Result getUserInfo(@RequestHeader String token) {
        Result result = userService.getUserInfo(token);
        return result;
    }

    @PostMapping("checkUserName")
    public Result checkUserName(@RequestParam String username) {
        Result result = userService.checkUserName(username);

        return result;
    }

    @PostMapping("regist")
    public Result regist(@RequestBody User user) {
        Result result = userService.regist(user);
        return result;
    }

    /**
     * 修改业务
     * 1.查询version版本
     * 2.补全属性,修改时间 , 版本!
     *
     * @param
     * @return
     */
    @GetMapping("checkLogin")
    public Result checkLogin(@RequestHeader String token) {
        if (StringUtils.isEmpty(token) || jwtHelper.isExpiration(token)) {
            //没有传或者过期 未登录
            return Result.build(null, ResultCodeEnum.NOTLOGIN);
        }

        return Result.ok(null);

    }
}

