package com.arjunkoottalasajayan.blogservice.rest.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BlogResponseBean {

    private Long blogId;
    private String blogHeading;
    private String blogContent;
    private Long blogTopicId;
    private Long createdUserId;
    private Integer numberOfHits;
    private Timestamp createdTime;
    private Timestamp updatedTime;
    private List<CommentResponseBean> comments;
    private List<Long> likedUserIds;
    private List<Long> dislikedUserIds;
}
