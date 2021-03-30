package com.arjunkoottalasajayan.comment.domain.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentBO {
    private Long commentId;
    private String comment;
    private Long commentedUserId;
    private Long commentToId;
    private Integer level;
    private Timestamp createdTime;
}
