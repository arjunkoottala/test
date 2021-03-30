package com.arjunkoottalasajayan.blogservice.rest.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommentResponseBean {
    private Long commentId;
    private String comment;
    private Long commentedUserId;
    private Timestamp createdTime;
    private List<Long> likedUserIds;
    private List<Long> dislikedUserIds;
}
