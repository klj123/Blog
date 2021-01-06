package com.xl.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author EDZ
 */
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Share {
    private String cn_share_id;
    private String cn_share_title;
    private String cn_share_body;
    private String cn_note_id;
}
