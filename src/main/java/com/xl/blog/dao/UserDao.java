package com.xl.blog.dao;

import com.xl.blog.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author EDZ
 */
public interface UserDao {
    /**
     * 根据名字查询用户信息
     * @param username
     * @return
     */
    @Select("select * from cn_user where cn_user_name=#{username}")
    User findByName(String username);
    @Insert("insert into cn_user(cn_user_id,cn_user_name,cn_user_password,cn_user_token,cn_user_nick) values(#{cn_user_id},#{cn_user_name},#{cn_user_password},#{cn_user_token},#{cn_user_nick})")
    void save(User user);
}
