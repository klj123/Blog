package com.xl.blog.service;

import com.xl.blog.dao.BookDao;
import com.xl.blog.entity.Book;
import com.xl.blog.util.JsonResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
}
