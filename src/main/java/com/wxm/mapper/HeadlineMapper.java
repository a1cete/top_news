package com.wxm.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wxm.pojo.Headline;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wxm.pojo.vo.PortalVo;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @author WXM
 * @description 针对表【news_headline】的数据库操作Mapper
 * @createDate 2024-05-10 15:16:35
 * @Entity com.wxm.pojo.Headline
 */
public interface HeadlineMapper extends BaseMapper<Headline> {
    IPage<Map> selectMyPage(IPage page, @Param("portalVo") PortalVo portalVo);

    Map selectDetailMap(Integer hid);
}




