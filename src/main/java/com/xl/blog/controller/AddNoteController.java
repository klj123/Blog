package com.xl.blog.controller;

import com.xl.blog.service.NoteService;
import com.xl.blog.util.JsonResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author EDZ
 */
@RestController
public class AddNoteController {
    @Resource
    private NoteService noteService;
    @PostMapping("/note/add.do")
    public JsonResult execute(String userId,String bookId,String noteTitle){
        JsonResult jsonResult = noteService.addNote(userId,bookId,noteTitle);
        return jsonResult;
    }
}
