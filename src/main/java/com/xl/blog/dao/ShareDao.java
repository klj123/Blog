package com.xl.blog.dao;

import com.xl.blog.entity.Share;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author EDZ
 */
public interface ShareDao {
    /**
     * 分享笔记，对分享笔记表进行添加操作
     * @param share
     */
    @Insert("insert into cn_share values(#{cn_share_id},#{cn_share_title},#{cn_share_body},#{cn_note_id})")
    void save(Share share);

    /**
     * 查询分享笔记
     * @param keyword
     * @param start
     * @return
     */
    @Select("select * from cn_share where cn_share_title like #{keyword} limit #{start},5")
    List<Share> findLikeTitle(@Param("keyword") String keyword,@Param("start") int start);

    /**
     * 通过分享笔记id查找分享笔记全部内容
     * @param shareId
     * @return
     */
    @Select("select * from cn_share where cn_share_id = #{cn_share_id}")
    Share findByShareId(String shareId);
}
