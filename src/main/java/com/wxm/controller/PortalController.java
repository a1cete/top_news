package com.wxm.controller;

import com.wxm.pojo.vo.PortalVo;
import com.wxm.service.HeadlineService;
import com.wxm.service.TypeService;
import com.wxm.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("portal")
@CrossOrigin
public class PortalController {
    @Autowired
    private TypeService typeService;
    @Autowired
    private HeadlineService headlineService;

    @GetMapping("findAllTypes")
    public Result findAllTypes() {
        Result result = typeService.findAllTypes();

        return result;
    }

    /**
     * 首页分页查询
     *
     * @return
     */
    @PostMapping("findNewsPage")
    public Result findNewPage(@RequestBody PortalVo portalVo) {
        Result result = headlineService.findNewPage(portalVo);
        return result;
    }

    @PostMapping("showHeadlineDetail")
    public Result showHeadlineDetail(@RequestParam Integer hid) {
        Result result = headlineService.showHeadlineDetail(hid);
        return result;
    }
}
