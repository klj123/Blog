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
public class UpdateNoteController {
    @Resource
    private NoteService noteService;
    @PostMapping("/note/update.do")
    public JsonResult execute(String title,String body,String noteId){
        JsonResult jsonResult = noteService.update(title,body,noteId);
        return jsonResult;
    }
}
