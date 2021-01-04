package com.xl.blog.service;

import com.xl.blog.dao.BookDao;
import com.xl.blog.entity.Book;
import com.xl.blog.util.JsonResult;
import com.xl.blog.util.NoteUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Resource
    private BookDao bookDao;
    @Override
    public JsonResult loadUserBooks(String userId) {
        JsonResult jsonResult = new JsonResult();
        List<Book> books = bookDao.findByUserId(userId);
        //创建返回结果
        jsonResult.setStatus(0);
        jsonResult.setMsg("显示成功");
        jsonResult.setData(books);
        return jsonResult;
    }

    @Override
    public JsonResult addBook(String userId, String bookName) {
        JsonResult jsonResult = new JsonResult();
        //cn_notebook_type_id是扩展类型
        Book book = Book.builder().cn_user_id(userId).cn_notebook_id(NoteUtil.getUUID()).cn_notebook_name(bookName).cn_notebook_createtime(new Timestamp(System.currentTimeMillis())).cn_notebook_type_id("5").build();
        int i = bookDao.save(book);
        if (i > 0){
            jsonResult.setStatus(0);
            jsonResult.setMsg("创建笔记本成功");
            jsonResult.setData(book);
            return jsonResult;
        }
        jsonResult.setStatus(1);
        jsonResult.setMsg("创建笔记异常");
        jsonResult.setData(null);
        return jsonResult;
    }
}
