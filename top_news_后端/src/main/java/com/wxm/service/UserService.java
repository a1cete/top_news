package com.wxm.service;

import com.wxm.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wxm.pojo.vo.RegisterVo;
import com.wxm.utils.Result;

/**
 * @author WXM
 * @description 针对表【news_user】的数据库操作Service
 * @createDate 2024-05-10 15:16:35
 */
public interface UserService extends IService<User> {
    /**
     * 登录
     *
     * @param user
     * @return
     */
    Result login(User user);

    Result getUserInfo(String token);

    Result checkUserName(String username);

    Result regist(User user);

    Result restPassword(RegisterVo registerVo);

    Boolean removeByUid(Integer uid);
}
