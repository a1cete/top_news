package com.wxm.pojo.vo;

import lombok.Data;


/**
 * 接收页面信息和头条类型和关键字的接收类
 */
@Data
public class PortalVo {
    private String keyWords;
    private Integer type;
    private Integer pageNum = 1;
    private Integer pageSize = 10;
}
