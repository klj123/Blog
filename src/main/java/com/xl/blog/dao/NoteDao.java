package com.xl.blog.dao;

import com.xl.blog.entity.Note;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author EDZ
 *笔记dao层
 */
public interface NoteDao {
    @Select("select * from cn_note where cn_notebook_id = #{booksId}")
    List<Note> findByBookId(String booksId);
    @Select("select * from cn_note where cn_note_id = #{noteId}")
    Note findById(String noteId);
    @Update("update cn_note set cn_note_title=#{cn_note_title},cn_note_body=#{cn_note_body},cn_note_last_modify_time=#{cn_note_last_modify_time} where cn_note_id = #{cn_note_id}")
    int update(Note note);
}
