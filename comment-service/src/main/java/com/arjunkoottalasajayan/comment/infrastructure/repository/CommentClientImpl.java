package com.arjunkoottalasajayan.comment.infrastructure.repository;


import com.arjunkoottalasajayan.comment.domain.port.spi.CommentClient;
import com.arjunkoottalasajayan.comment.infrastructure.model.request.CommentEntity;
import com.arjunkoottalasajayan.comment.infrastructure.model.request.CommentReactionEntity;
import com.arjunkoottalasajayan.comment.infrastructure.model.response.CommentResponseEntity;
import com.arjunkoottalasajayan.comment.infrastructure.port.CommentReactionRepository;
import com.arjunkoottalasajayan.comment.infrastructure.port.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@Slf4j
public class CommentClientImpl implements CommentClient {

    @Autowired
    CommentReactionRepository commentReactionRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    Mapper mapper;

    @Override
    public CommentResponseEntity saveComment(CommentEntity commentEntity) {
        commentEntity = commentRepository.save(commentEntity);
        CommentResponseEntity commentResponseEntity = mapper.map(commentEntity, CommentResponseEntity.class);
        return commentResponseEntity;
    }

    @Override
    public List<CommentResponseEntity> getCommentsByUserId(Long userId) {
        return commentRepository.getCommentsByUserId(userId).stream().map(comment -> mapper.map(comment, CommentResponseEntity.class)).
                collect(Collectors.toList());

    }

    @Override
    public List<Long> fetchLikedUserIdsByCommentId(Long commentId) {
        return commentReactionRepository.fetchLikedUserIdsByCommentId(commentId);
    }

    @Override
    public List<Long> fetchDislikedUserIdsByCommentId(Long commentId) {
        return commentReactionRepository.fetchDislikedUserIdsByCommentId(commentId);
    }

    @Override
    public List<CommentResponseEntity> getCommentBySourceId(Long commentId, Integer level) {
        return commentRepository.getCommentBySourceId(commentId, level).stream().map(comment -> mapper.map(comment, CommentResponseEntity.class)).
                collect(Collectors.toList());
    }

    @Override
    public Integer fetchReactionKey(Long commentId, Long userId) {
        return commentReactionRepository.fetchReactionKey(commentId, userId);
    }

    @Override
    public void reactToComment(Integer reactionKey, Long commentId, Long userId) {
        log.info("Inside saveReaction of CommentServiceImpl class");
        CommentReactionEntity commentReactionEntity = new CommentReactionEntity();
        commentReactionEntity.setCommentId(commentId);
        commentReactionEntity.setUserId(userId);
        commentReactionEntity.setReactionKey(reactionKey);
        commentReactionRepository.save(commentReactionEntity);
    }

    @Override
    public void updateReactToComment(Integer newReactionKey, Long commentId, Long userId) {
        commentReactionRepository.reactToComment(newReactionKey,commentId,userId);
    }
}
