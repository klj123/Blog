package com.xl.blog.controller;

import com.xl.blog.service.UserService;
import com.xl.blog.util.JsonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserAddController {
    @Resource
    private UserService userService;
    @RequestMapping("/user/add.do")
    public JsonResult execute(String username,String password,String nickname){
        JsonResult jsonResult = userService.addUser(username, password, nickname);
        return jsonResult;
    }
}
