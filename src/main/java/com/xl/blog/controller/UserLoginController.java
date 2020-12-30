package com.xl.blog.controller;

import com.xl.blog.service.UserService;
import com.xl.blog.util.JsonResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;

/**
 * @author EDZ
 */
@RestController
public class UserLoginController {
    @Resource
    private UserService userService;
    @PostMapping("/user/login.do")
    public JsonResult execute(String username,String password) throws NoSuchAlgorithmException {
        JsonResult jsonResult = userService.checkLogin(username, password);
        return jsonResult;
    }
}
