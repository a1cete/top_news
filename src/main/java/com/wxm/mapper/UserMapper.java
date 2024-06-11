package com.wxm.mapper;

import com.wxm.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author WXM
 * @description 针对表【news_user】的数据库操作Mapper
 */
public interface UserMapper extends BaseMapper<User> {

    int updateByUid(Integer uid, String newPassword);


    Boolean deleteByUid(Integer uid);
}




