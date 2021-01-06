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
public class ShareNoteController {
    @Resource
    private NoteService noteService;
    @PostMapping("/note/share.do")
    public JsonResult execute(String noteId){
        JsonResult jsonResult = noteService.shareNote(noteId);
        return jsonResult;
    }
}
