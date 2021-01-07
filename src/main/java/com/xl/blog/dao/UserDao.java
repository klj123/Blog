package com.xl.blog.dao;

import com.xl.blog.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

    /**
     * 注册用户
     * @param user
     */
    @Insert("insert into cn_user(cn_user_id,cn_user_name,cn_user_password,cn_user_token,cn_user_nick) values(#{cn_user_id},#{cn_user_name},#{cn_user_password},#{cn_user_token},#{cn_user_nick})")
    void save(User user);

    /**
     * 通过用户id查找用户
     * @param userId
     * @return
     */
    @Select("select * from cn_user where cn_user_id = #{cn_user_id}")
    User findById(String userId);

    /**
     * 修改密码
     * @param user1
     * @return
     */
    @Update("update cn_user set cn_user_password = #{cn_user_password} where cn_user_id = #{cn_user_id}")
    int changePwd(User user1);
}
