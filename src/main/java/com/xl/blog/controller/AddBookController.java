package com.xl.blog.controller;

import com.xl.blog.service.BookService;
import com.xl.blog.util.JsonResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author EDZ
 */
@RestController
public class AddBookController {
    @Resource
    private BookService bookService;
    @PostMapping("/book/add.do")
    public JsonResult execute(String userId,String bookName){
        JsonResult jsonResult = bookService.addBook(userId,bookName);
        return jsonResult;
    }
}
