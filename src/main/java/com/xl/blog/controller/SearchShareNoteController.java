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
public class SearchShareNoteController {
    @Resource
    private NoteService noteService;
    @PostMapping("/note/search_share.do")
    public JsonResult execute(String keyword,int page){
        JsonResult jsonResult = noteService.searchShareNote(keyword,page);
        return jsonResult;
    }
}
