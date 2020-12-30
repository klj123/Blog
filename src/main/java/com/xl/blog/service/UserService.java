package com.xl.blog.service;

import com.xl.blog.util.JsonResult;

import java.security.NoSuchAlgorithmException;

/**
 * @author EDZ
 */
public interface UserService {
    /**
     * 检查用户登录，返回响应实体类
     * @param username
     * @param password
     * @return
     */
    JsonResult checkLogin(String username,String password) throws NoSuchAlgorithmException;

    JsonResult addUser(String username, String password, String nickname);
}
