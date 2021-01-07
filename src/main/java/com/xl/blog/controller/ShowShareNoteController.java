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
public class ShowShareNoteController {
    @Resource
    private NoteService noteService;
    @PostMapping("/note/showShareNote.do")
    public JsonResult execute(String shareId){
        JsonResult jsonResult = noteService.showShareNote(shareId);
        return jsonResult;
    }
}
