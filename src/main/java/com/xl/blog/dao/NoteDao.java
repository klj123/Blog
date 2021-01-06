package com.xl.blog.dao;

import com.xl.blog.entity.Note;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author EDZ
 *笔记dao层
 */
public interface NoteDao {
    /**
     * 通过bookId查询笔记本
     * @param booksId
     * @return
     */
    @Select("select * from cn_note where cn_notebook_id = #{booksId} and cn_note_status_id='1'")
    List<Note> findByBookId(String booksId);

    /**
     * 通过id查询笔记
     * @param noteId
     * @return
     */
    @Select("select * from cn_note where cn_note_id = #{noteId}")
    Note findById(String noteId);

    /**
     * 修改笔记内容
     * @param note
     * @return
     */
//    @Update("update cn_note set cn_note_title=#{cn_note_title},cn_note_body=#{cn_note_body},cn_note_last_modify_time=#{cn_note_last_modify_time} where cn_note_id = #{cn_note_id}")
//    int update(Note note);

    /**
     * 创建笔记
     * @param note
     * @return
     */
    @Insert("insert into cn_note(cn_note_id,cn_notebook_id,cn_user_id,cn_note_status_id,cn_note_type_id,cn_note_title,cn_note_create_time,cn_note_last_modify_time,cn_note_body) values(#{cn_note_id},#{cn_notebook_id},#{cn_user_id},#{cn_note_status_id},#{cn_note_type_id},#{cn_note_title},#{cn_note_create_time},#{cn_note_last_modify_time},#{cn_note_body})")
    int save(Note note);

    /**
     * 逻辑删除
     * @param noteId
     * @return
     */
//    @Update("update cn_note set cn_note_status_id='2' where cn_note_id=#{noteId}")
//    int updateStatus(String noteId);

    /**
     * 移动笔记，对bookId通过noteId进行修改
     * @param note
     * @return
     */
//    @Update("update cn_note set cn_notebook_id = #{cn_notebook_id} where cn_note_id = #{cn_note_id}")
//    int updateNote(Note note);

    /**
     * 分享笔记（修改cn_note_type_id = 2）
     * @param noteId
     * @return
     */
//    @Update("update cn_note set cn_note_type_id = '2' where cn_note_id = #{noteId}")
//    int updateTypeByNoteId(String noteId);
    /**
     * 动态更新笔记
     * @param note
     * @return
     */
    @Update("<script>update cn_note\n" +
            "        <set>\n" +
            "            <if test=\"cn_notebook_id != null\">\n" +
            "                cn_notebook_id = #{cn_notebook_id},\n" +
            "            </if>\n" +
            "            <if test=\"cn_user_id != null\">\n" +
            "                cn_user_id = #{cn_user_id},\n" +
            "            </if>\n" +
            "            <if test=\"cn_note_status_id != null\">\n" +
            "                cn_note_status_id = #{cn_note_status_id},\n" +
            "            </if>\n" +
            "            <if test=\"cn_note_type_id != null\">\n" +
            "                cn_note_type_id = #{cn_note_type_id},\n" +
            "            </if>\n" +
            "            <if test=\"cn_note_title != null\">\n" +
            "                cn_note_title = #{cn_note_title},\n" +
            "            </if>\n" +
            "            <if test=\"cn_note_body != null\">\n" +
            "                cn_note_body = #{cn_note_body},\n" +
            "            </if>\n" +
            "            <if test=\"cn_note_create_time != null\">\n" +
            "                cn_note_create_time = #{cn_note_create_time},\n" +
            "            </if>\n" +
            "            <if test=\"cn_note_last_modify_time != null\">\n" +
            "                cn_note_last_modify_time = #{cn_note_last_modify_time},\n" +
            "            </if>\n" +
            "        </set>\n" +
            "        where cn_note_id = #{cn_note_id}</script>")
    int dynamicUpdate(Note note);
}
