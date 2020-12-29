package com.xl.blog.dao;

import com.xl.blog.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author EDZ
 */
public interface UserDao {
    @Select("select * from cn_user")
    List<User> findAll();
}
