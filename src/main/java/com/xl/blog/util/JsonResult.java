package com.xl.blog.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 响应实体类
 * 各种响应最后都用这个响应实体类来封装
 * @author EDZ
 */
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class JsonResult {
    /**
     * 0状态成功登录
     * 响应状态
     */
    private Integer status;
    /**
     * 封装后台的响应信息
     */
    private String msg;
    /**
     * 封装后台传给前台的数据
     */
    private Object data;
}
