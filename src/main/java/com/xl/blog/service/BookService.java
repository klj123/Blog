package com.xl.blog.service;

import com.xl.blog.util.JsonResult;

import java.util.List;

/**
 * @author EDZ
 */
public interface BookService {
    /**
     * 根据用户id加载对应的用户笔记本
     * @param userId
     * @return json响应实体类
     */
    JsonResult loadUserBooks(String userId);
}
