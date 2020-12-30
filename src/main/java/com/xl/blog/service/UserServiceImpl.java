package com.xl.blog.service;

import com.xl.blog.dao.UserDao;
import com.xl.blog.entity.User;
import com.xl.blog.util.JsonResult;
import com.xl.blog.util.NoteUtil;
import org.springframework.stereotype.Service;
import sun.security.provider.MD5;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;

/**
 * @author EDZ
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;
    @Override
    public JsonResult checkLogin(String username, String password) throws NoSuchAlgorithmException {
        JsonResult jsonResult = new JsonResult();
        User user = userDao.findByName(username);
        /**
         * 账号不存在
         */
        if (user == null){
            jsonResult.setStatus(1);
            jsonResult.setMsg("用户名不存在");
            return jsonResult;
        }
        /**
         * 密码不正确
         */
        String md5 = NoteUtil.md5(password);
        if (!user.getCn_user_password().equals(md5)){
            jsonResult.setStatus(2);
            jsonResult.setMsg("账号或密码错误");
            return jsonResult;
        }
        /**
         * 成功登录
         */
        jsonResult.setStatus(0);
        jsonResult.setMsg("登录成功");
        /**
         *疲敝密码操作
         * 返回到前台数据的时候，不返回密码，防止网络的抓包
         */
        user.setCn_user_password("");
        jsonResult.setData(user);
        return jsonResult;
    }

    @Override
    public JsonResult addUser(String username, String password, String nickname) {
        JsonResult jsonResult = new JsonResult();
        //判断用户是否被占用
        User user = userDao.findByName(username);
        if (user != null){
            jsonResult.setStatus(1);
            jsonResult.setMsg("用户已存在");
            return jsonResult;
        }
        try {
            //执行插入操作
            User user1 = new User();
            user1.setCn_user_name(username);
            user1.setCn_user_nick(nickname);
            user1.setCn_user_password(NoteUtil.md5(password));
            user1.setCn_user_id(NoteUtil.getUUID());
            userDao.save(user1);
            jsonResult.setStatus(0);
            jsonResult.setMsg("添加用户成功");
            return jsonResult;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
