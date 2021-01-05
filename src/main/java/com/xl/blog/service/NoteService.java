package com.xl.blog.service;

import com.xl.blog.util.JsonResult;

/**
 * @author EDZ
 * 笔记Service层
 */
public interface NoteService {
    /**
     * 加载该用户id的所有笔记
     * @param booksId
     * @return JsonResult
     */
    JsonResult LoadNotes(String booksId);

    /**
     * 加载笔记
     * @param noteId
     * @return
     */
    JsonResult loadNote(String noteId);

    /**
     * 更新笔记
     * @param title
     * @param body
     * @param noteId
     * @return
     */
    JsonResult update(String title, String body, String noteId);

    /**
     * 创建笔记
     * @param userId
     * @param bookId
     * @param noteTitle
     * @return
     */
    JsonResult addNote(String userId, String bookId, String noteTitle);

    /**
     * 删除笔记
     * @param noteId
     * @return
     */
    JsonResult deleteNote(String noteId);
}
