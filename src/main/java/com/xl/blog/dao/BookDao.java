package com.xl.blog.dao;

import com.xl.blog.entity.Book;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author EDZ
 */
public interface BookDao {
    /**
     * 根据用户id查询用户拥有的笔记本
     * @param UserId
     * @return 笔记本列表
     */
    @Select("select * from cn_notebook where cn_user_id = #{UserId}")
    List<Book> findByUserId(String UserId);

    /**
     * 创建笔记本
     * @param book
     * @return
     */
    @Insert("insert into cn_notebook(cn_notebook_id,cn_user_id,cn_notebook_type_id,cn_notebook_name,cn_notebook_createtime) values(#{cn_notebook_id},#{cn_user_id},#{cn_notebook_type_id},#{cn_notebook_name},#{cn_notebook_createtime})")
    int save(Book book);
}
