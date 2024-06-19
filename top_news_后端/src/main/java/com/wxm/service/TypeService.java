package com.wxm.service;

import com.wxm.pojo.Type;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wxm.utils.Result;

/**
 * @author WXM
 * @description 针对表【news_type】的数据库操作Service
 * @createDate 2024-05-10 15:16:35
 */
public interface TypeService extends IService<Type> {

    Result findAllTypes();
}
