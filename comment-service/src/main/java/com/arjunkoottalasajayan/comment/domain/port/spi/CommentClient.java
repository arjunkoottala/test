package com.arjunkoottalasajayan.comment.domain.port.spi;

import com.arjunkoottalasajayan.comment.infrastructure.model.request.CommentEntity;
import com.arjunkoottalasajayan.comment.infrastructure.model.response.CommentResponseEntity;

import java.util.List;

public interface CommentClient {
    CommentResponseEntity saveComment(CommentEntity commentEntity);

    List<CommentResponseEntity> getCommentsByUserId(Long userId);

    List<Long> fetchLikedUserIdsByCommentId(Long commentId);

    List<Long> fetchDislikedUserIdsByCommentId(Long commentId);

    List<CommentResponseEntity> getCommentBySourceId(Long commentId, Integer level);

    Integer fetchReactionKey(Long commentId, Long userId);

    void reactToComment(Integer newReactionKey, Long commentId, Long userId);

    void updateReactToComment(Integer newReactionKey, Long commentId, Long userId);
}
