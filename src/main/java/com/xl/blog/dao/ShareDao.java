package com.xl.blog.dao;

import com.xl.blog.entity.Share;
import org.apache.ibatis.annotations.Insert;

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
}
