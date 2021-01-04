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
     * @return 用户笔记本的情况
     */
    JsonResult loadUserBooks(String userId);

    /**
     * 添加笔记本
     * @param userId
     * @param bookName
     * @return 添加笔记本的情况
     */
    JsonResult addBook(String userId, String bookName);
}
