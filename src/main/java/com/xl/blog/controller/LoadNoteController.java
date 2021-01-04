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
public class LoadNoteController {
    @Resource
    private NoteService noteService;
    @RequestMapping("/note/loadNote.do")
    public JsonResult execute(String noteId){
        JsonResult jsonResult = noteService.loadNote(noteId);
        return jsonResult;
    }
}
