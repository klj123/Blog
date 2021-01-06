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
public class MoveNoteController {
    @Resource
    private NoteService noteService;
    @PostMapping("/note/move.do")
    public JsonResult execute(String noteId,String bookId){
        JsonResult jsonResult = noteService.moveNote(noteId,bookId);
        return jsonResult;
    }
}
