package com.xl.blog.service;

import com.xl.blog.dao.NoteDao;
import com.xl.blog.dao.ShareDao;
import com.xl.blog.dao.UserDao;
import com.xl.blog.entity.Note;
import com.xl.blog.entity.Share;
import com.xl.blog.entity.User;
import com.xl.blog.util.JsonResult;
import com.xl.blog.util.NoteUtil;
import org.aspectj.weaver.ast.Not;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author EDZ
 */
@Service
public class NoteServiceImpl implements NoteService {
    @Resource
    private NoteDao noteDao;
    @Resource
    private ShareDao shareDao;
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
        int i = noteDao.dynamicUpdate(note);
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
        Note note = Note.builder().cn_note_id(noteId).cn_note_status_id("2").build();
        int i = noteDao.dynamicUpdate(note);
        if (i > 0){
            jsonResult.setStatus(0);
            jsonResult.setMsg("删除笔记成功");
            return jsonResult;
        }
        jsonResult.setStatus(1);
        jsonResult.setMsg("删除笔记失败");
        return jsonResult;
    }

    @Override
    public JsonResult moveNote(String noteId, String bookId) {
        JsonResult jsonResult = new JsonResult();
        Note note = Note.builder().cn_note_id(noteId).cn_notebook_id(bookId).build();
        int i = noteDao.dynamicUpdate(note);
        if (i > 0){
            jsonResult.setStatus(0);
            jsonResult.setMsg("移动笔记成功");
            return jsonResult;
        }
        jsonResult.setStatus(1);
        jsonResult.setMsg("移动笔记失败");
        return jsonResult;
    }

    /**
     * 事务回滚
     * @param noteId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public JsonResult shareNote(String noteId) {
        /**
         * 1.查询笔记是否被分享过
         * 2.修改笔记状态为分享  cn_note_type_id = '2'
         * 3.分享过的笔记插入到share表中
         */
        JsonResult jsonResult = new JsonResult();
        Note note = noteDao.findById(noteId);
        if (note.getCn_note_type_id().equals("2")){
            jsonResult.setStatus(1);
            jsonResult.setMsg("笔记已经分享过");
            return jsonResult;
        }
        Note note1 = Note.builder().cn_note_id(noteId).cn_note_type_id("2").build();
        int i = noteDao.dynamicUpdate(note1);
        if (i > 0){
            Share share = Share.builder().cn_note_id(noteId).cn_share_body(note.getCn_note_body()).cn_share_id(NoteUtil.getUUID()).cn_share_title(note.getCn_note_title()).build();
            shareDao.save(share);
            jsonResult.setStatus(0);
            jsonResult.setMsg("分享笔记成功");
            return jsonResult;
        }
        jsonResult.setStatus(2);
        jsonResult.setMsg("分享笔记失败");
        return jsonResult;
    }

    @Override
    public JsonResult searchShareNote(String keyword, int page) {
        JsonResult jsonResult = new JsonResult();
        int start = (page - 1) * 5;
        //模糊查询需要手动加%
        keyword = "%" + keyword + "%";
        List<Share> shares = shareDao.findLikeTitle(keyword,start);
        jsonResult.setStatus(0);
        jsonResult.setMsg("查询分享笔记成功");
        jsonResult.setData(shares);
        return jsonResult;
    }

    @Override
    public JsonResult showShareNote(String shareId) {
        JsonResult jsonResult = new JsonResult();
        Share share = shareDao.findByShareId(shareId);
        if (share != null){
            jsonResult.setStatus(0);
            jsonResult.setMsg("显示分享笔记成功");
            jsonResult.setData(share);
            return jsonResult;
        }
        return null;
    }
}
