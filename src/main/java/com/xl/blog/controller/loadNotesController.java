package com.xl.blog.controller;

import com.xl.blog.service.NoteService;
import com.xl.blog.util.JsonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author EDZ
 */
@RestController
public class loadNotesController {
    @Resource
    private NoteService noteService;
    @RequestMapping("/note/loadNotes.do")
    public JsonResult execute(String bookId){
        JsonResult jsonResult = noteService.LoadNotes(bookId);
        return jsonResult;
    }
}
