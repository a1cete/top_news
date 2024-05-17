package com.wxm.service;

import com.wxm.pojo.Headline;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wxm.pojo.vo.PortalVo;
import com.wxm.utils.Result;

/**
 * @author WXM
 * @description 针对表【news_headline】的数据库操作Service
 * @createDate 2024-05-10 15:16:35
 */
public interface HeadlineService extends IService<Headline> {


    Result findNewPage(PortalVo portalVo);

    Result showHeadlineDetail(Integer hid);

    Result publish(Headline headline);

    Result findHeadlineByHid(Integer hid);

    Result updateHeadLine(Headline headline);
}
