package com.xl.blog.service;

import com.xl.blog.dao.NoteDao;
import com.xl.blog.dao.UserDao;
import com.xl.blog.entity.Note;
import com.xl.blog.entity.User;
import com.xl.blog.util.JsonResult;
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
}
