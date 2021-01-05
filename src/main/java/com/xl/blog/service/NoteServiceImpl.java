package com.xl.blog.service;

import com.xl.blog.dao.NoteDao;
import com.xl.blog.dao.UserDao;
import com.xl.blog.entity.Note;
import com.xl.blog.entity.User;
import com.xl.blog.util.JsonResult;
import com.xl.blog.util.NoteUtil;
import org.aspectj.weaver.ast.Not;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author EDZ
 */
@Service
public class NoteServiceImpl implements NoteService {
    @Resource
    private NoteDao noteDao;
    @Override
    public JsonResult LoadNotes(String booksId) {
        JsonResult jsonResult = new JsonResult();
        List<Note> notes = noteDao.findByBookId(booksId);
        if (notes == null){
            jsonResult.setStatus(1);
            jsonResult.setMsg("没有查询出来");
            return jsonResult;
        }
        jsonResult.setStatus(0);
        jsonResult.setMsg("查询出来");
        jsonResult.setData(notes);
        return jsonResult;
    }

    @Override
    public JsonResult loadNote(String noteId) {
        JsonResult jsonResult = new JsonResult();
        Note note = noteDao.findById(noteId);
        jsonResult.setStatus(0);
        jsonResult.setMsg("加载出来了");
        jsonResult.setData(note);
        return jsonResult;
    }

    @Override
    public JsonResult update(String title, String body, String noteId) {
        JsonResult jsonResult = new JsonResult();
        Note note = Note.builder().cn_note_id(noteId).cn_note_title(title).cn_note_body(body).cn_note_last_modify_time(System.currentTimeMillis()).build();
        int i = noteDao.update(note);
        if (i > 0){
            jsonResult.setStatus(0);
            jsonResult.setMsg("保存笔记成功");
            return jsonResult;
        }
        jsonResult.setStatus(1);
        jsonResult.setMsg("保存笔记失败");
        return jsonResult;
    }

    @Override
    public JsonResult addNote(String userId, String bookId, String noteTitle) {
        JsonResult jsonResult = new JsonResult();
        Note note = Note.builder().cn_user_id(userId).cn_notebook_id(bookId).cn_note_title(noteTitle).cn_note_create_time(System.currentTimeMillis()).cn_note_id(NoteUtil.getUUID()).cn_note_status_id("1").cn_note_type_id("1").cn_note_last_modify_time(System.currentTimeMillis()).cn_note_body("").build();
        int i = noteDao.save(note);
        if (i > 0){
            jsonResult.setStatus(0);
            jsonResult.setMsg("创建笔记成功");
            jsonResult.setData(note);
            return jsonResult;
        }
        jsonResult.setStatus(1);
        jsonResult.setMsg("创建笔记失败");
        return jsonResult;
    }

    @Override
    public JsonResult deleteNote(String noteId) {
        JsonResult jsonResult = new JsonResult();
        int i = noteDao.updateStatus(noteId);
        if (i > 0){
            jsonResult.setStatus(0);
            jsonResult.setMsg("删除笔记成功");
            return jsonResult;
        }
        jsonResult.setStatus(1);
        jsonResult.setMsg("删除笔记失败");
        return jsonResult;
    }
}
