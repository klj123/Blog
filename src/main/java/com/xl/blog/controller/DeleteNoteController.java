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
public class DeleteNoteController {
    @Resource
    private NoteService noteService;
    @PostMapping("/note/delete.do")
    public JsonResult execute(String noteId){
        JsonResult jsonResult = noteService.deleteNote(noteId);
        return jsonResult;
    }
}
