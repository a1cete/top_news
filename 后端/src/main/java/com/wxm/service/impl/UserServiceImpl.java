package com.wxm.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wxm.pojo.User;
import com.wxm.pojo.vo.RegisterVo;
import com.wxm.service.UserService;
import com.wxm.mapper.UserMapper;
import com.wxm.utils.JwtHelper;
import com.wxm.utils.MD5Util;
import com.wxm.utils.Result;
import com.wxm.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author WXM
 * @description 针对表【news_user】的数据库操作Service实现
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JwtHelper jwtHelper;

    /**
     * 登录
     *
     * @param user
     * @return
     */
    @Override
    public Result login(User user) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUsername, user.getUsername());
        User loginUser = userMapper.selectOne(lambdaQueryWrapper);
        if (loginUser == null) {
            return Result.build(null, ResultCodeEnum.USERNAME_ERROR);
        }
        if (!StringUtils.isEmpty(user.getUserPwd()) && MD5Util.encrypt(user.getUserPwd()).equals(loginUser.getUserPwd())) {
            //根据id生成token
            String token = jwtHelper.createToken(Long.valueOf(loginUser.getUid()));
            //封装token返回
            Map data = new HashMap();
            data.put("token", token);
            return Result.ok(data);
        }

        return Result.build(null, ResultCodeEnum.PASSWORD_ERROR);
    }

    @Override
    public Result getUserInfo(String token) {
        boolean expiration = jwtHelper.isExpiration(token);
        if (expiration) {
            return Result.build(null, ResultCodeEnum.NOTLOGIN);
        }
        int userId = jwtHelper.getUserId(token).intValue();
        User loginUser = userMapper.selectById(userId);
        loginUser.setUserPwd("");
        Map data = new HashMap();
        data.put("loginUser", loginUser);
        return Result.ok(data);
    }

    @Override
    public Result checkUserName(String username) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);

        Long l = userMapper.selectCount(queryWrapper);
        if (l > 0) {
            return Result.build(null, ResultCodeEnum.USERNAME_USED);
        }
        return Result.ok(null);

    }

    @Override
    public Result regist(User user) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, user.getUsername());
        Long l = userMapper.selectCount(queryWrapper);
        if (l > 0) {
            return Result.build(null, ResultCodeEnum.USERNAME_USED);
        }
        user.setUserPwd(MD5Util.encrypt(user.getUserPwd()));
        userMapper.insert(user);
        return Result.ok(null);

    }

    @Override
    public Result restPassword(RegisterVo registerVo) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUid, registerVo.getUid());
        User loginUser = userMapper.selectOne(lambdaQueryWrapper);
        if (loginUser == null) {
            return Result.build(null, ResultCodeEnum.USERNAME_ERROR);
        }
        if (!MD5Util.encrypt(registerVo.getUserPwd()).equals(loginUser.getUserPwd())) {
            return Result.build(null, ResultCodeEnum.PASSWORD_ERROR);
        }
        int uid = registerVo.getUid();
        String newPassword = MD5Util.encrypt(registerVo.getNewPassword());
        userMapper.updateByUid(uid, newPassword);
        return Result.ok(null);


    }

    @Override
    public Boolean removeByUid(Integer uid) {

        Boolean b = userMapper.deleteByUid(uid);
        return b;
    }
}




