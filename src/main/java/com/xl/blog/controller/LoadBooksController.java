package com.xl.blog.controller;

import com.xl.blog.service.BookService;
import com.xl.blog.util.JsonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.xml.bind.util.JAXBSource;

/**
 * @author EDZ
 */
@RestController
public class LoadBooksController {
    @Resource
    private BookService bookService;
    @RequestMapping("/book/loadbooks.do")
    public JsonResult execute(String userId){
        JsonResult jsonResult = bookService.loadUserBooks(userId);
        return jsonResult;
    }
}
