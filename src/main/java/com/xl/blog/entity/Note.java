package com.xl.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author EDZ
 * 笔记实体类
 */
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Note {
    private String cn_note_id;
    private String cn_notebook_id;
    private String cn_user_id;
    private String cn_note_status_id;
    private String cn_note_type_id;
    private String cn_note_title;
    private String cn_note_body;
    private long  cn_note_create_time;
    private long cn_note_last_modify_time;
}
