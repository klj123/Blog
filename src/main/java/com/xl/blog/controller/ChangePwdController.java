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
public class ChangePwdController {
    @Resource
    private UserService userService;
    @PostMapping("/user/changePwd.do")
    public JsonResult execute(String lastPwd,String newPwd,String userId) throws NoSuchAlgorithmException {
        JsonResult jsonResult = userService.changePwd(lastPwd,newPwd,userId);
        return jsonResult;
    }
}
