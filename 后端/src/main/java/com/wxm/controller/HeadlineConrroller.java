package com.wxm.controller;

import com.wxm.pojo.Headline;
import com.wxm.service.HeadlineService;
import com.wxm.utils.JwtHelper;
import com.wxm.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 头条具体信息
 */
@RestController
@RequestMapping("headline")
@CrossOrigin
public class HeadlineConrroller {
    @Autowired
    private JwtHelper jwtHelper;
    @Autowired
    private HeadlineService headlineService;

    /**
     * token获取userId
     * 封装headline数据
     * 插入数据
     */
    @PostMapping("publish")
    public Result publish(@RequestBody Headline headline, @RequestHeader String token) {
        int userId = jwtHelper.getUserId(token).intValue();
        headline.setPublisher(userId);
        Result result = headlineService.publish(headline);
        return result;
    }

    //通过头条id获取头条详情
    @PostMapping("findHeadlineByHid")
    public Result findHeadlineByHid(Integer hid) {
        Result result = headlineService.findHeadlineByHid(hid);
        return result;
    }

    //修改头条数据
    @PostMapping("update")
    public Result update(@RequestBody Headline headline) {
        Result result = headlineService.updateHeadLine(headline);
        return result;
    }

    //根据头条id删除头条
    @PostMapping("removeByHid")
    public Result removeById(Integer hid) {
        headlineService.removeById(hid);
        return Result.ok(null);
    }
}
