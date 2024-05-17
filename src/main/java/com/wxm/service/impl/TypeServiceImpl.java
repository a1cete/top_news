package com.wxm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wxm.pojo.Type;
import com.wxm.service.TypeService;
import com.wxm.mapper.TypeMapper;
import com.wxm.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author WXM
 * @description 针对表【news_type】的数据库操作Service实现
 * @createDate 2024-05-10 15:16:35
 */
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type>
        implements TypeService {
    @Autowired
    private TypeMapper typeMapper;

    @Override
    public Result findAllTypes() {
        List<Type> types = typeMapper.selectList(null);

        return Result.ok(types);
    }
}




