package com.arjunkoottalasajayan.blogservice.infrastructure.port;


import com.arjunkoottalasajayan.blogservice.infrastructure.model.response.CommentResponseEntity;

import java.util.List;

public interface CommentServiceData {
    public List<CommentResponseEntity> fetchComments(Long blogId);
}
