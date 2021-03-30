package com.arjunkoottalasajayan.comment.domain.port.api;

import com.arjunkoottalasajayan.comment.domain.model.request.CommentBO;
import com.arjunkoottalasajayan.comment.domain.model.request.CommentReactionBO;
import com.arjunkoottalasajayan.comment.domain.model.response.CommentResponseBO;

import java.util.List;

public interface CommentService {
    public CommentResponseBO saveComment(CommentBO commentBO);

    public List<CommentResponseBO> findCommentsByUserId(Long userId);

    public List<CommentResponseBO> findCommentBySourceId(Long id, Integer level);

    public String reactToComment(CommentReactionBO commentReactionBO);
}
