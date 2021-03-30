package com.arjunkoottalasajayan.comment.domain.service;

import com.arjunkoottalasajayan.comment.domain.model.request.CommentBO;
import com.arjunkoottalasajayan.comment.domain.model.request.CommentReactionBO;
import com.arjunkoottalasajayan.comment.domain.model.response.CommentResponseBO;
import com.arjunkoottalasajayan.comment.domain.port.api.CommentService;
import com.arjunkoottalasajayan.comment.domain.port.spi.CommentClient;
import com.arjunkoottalasajayan.comment.domain.util.ReactionResponses;
import com.arjunkoottalasajayan.comment.infrastructure.model.request.CommentEntity;
import com.arjunkoottalasajayan.comment.infrastructure.model.response.CommentResponseEntity;
import com.arjunkoottalasajayan.comment.rest.error.CommentServiceTechnicalException;
import com.arjunkoottalasajayan.comment.rest.error.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.arjunkoottalasajayan.comment.domain.util.ReactionResponses.*;

@Slf4j
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    public CommentClient commentClient;

    @Autowired
    Mapper mapper;

    @Override
    public CommentResponseBO saveComment(CommentBO commentBO) {
        CommentResponseEntity commentResponseEntity = commentClient.saveComment(mapper.map(commentBO, CommentEntity.class));
        commentClient.reactToComment(0, commentBO.getCommentId(), commentBO.getCommentedUserId());
        return mapper.map(commentResponseEntity, CommentResponseBO.class);
    }

    @Override
    public List<CommentResponseBO> findCommentsByUserId(Long userId) {
        log.info("Inside findCommentsByUserId of CommentServiceImpl class");
        List<CommentResponseBO> commentResponseList = new ArrayList<>();
        try {
            List<CommentResponseEntity> commentList = commentClient.getCommentsByUserId(userId);
            commentResponseList = commentList.stream().map(commentResponseEntity ->
                    mapper.map(commentResponseEntity, CommentResponseBO.class)).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Exception occurred at findCommentsByUserId", e);
            throw new CommentServiceTechnicalException(ErrorCode.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
        return commentResponseList;
    }

    @Override
    public List<CommentResponseBO> findCommentBySourceId(Long commentId, Integer level) {
        try {
            log.info("Inside findCommentBySourceId of CommentServiceImpl class");
            List<CommentResponseEntity> commentList =
                    commentClient.getCommentBySourceId(commentId, level);
            List<CommentResponseBO> commentResponseList = commentList.stream().map(commentResponseEntity ->
                    mapper.map(commentResponseEntity, CommentResponseBO.class)).collect(Collectors.toList());

            commentResponseList.stream().forEach(commentResponseBO -> {

                commentResponseBO.setDislikedUserIds(
                        commentClient.fetchDislikedUserIdsByCommentId(commentResponseBO.getCommentId()));
                commentResponseBO.setLikedUserIds(
                        commentClient.fetchLikedUserIdsByCommentId(commentResponseBO.getCommentId()));

            });
            return commentResponseList;
        } catch (Exception e) {
            log.error("Exception occured at findCommentBySourceId", e);
            throw new CommentServiceTechnicalException(ErrorCode.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @Override
    public String reactToComment(CommentReactionBO commentReactionBO) {
        log.info("Inside reactToComment of CommentServiceImpl class");
        try {
            Long commentId = commentReactionBO.getCommentId();
            Long userId = commentReactionBO.getUserId();
            Integer currentReactionKey = commentClient.fetchReactionKey(commentId, userId);
            Integer newReactionKey = commentReactionBO.getReactionKey();
            if (currentReactionKey == null) {
                commentClient.reactToComment(newReactionKey, commentId, userId);
                return newReactionKey == 1 ? LIKED.toString() : DISLIKE.toString();
            }
            newReactionKey = newReactionKey == 1 && currentReactionKey == 1 ? 0 :
                    newReactionKey == 1 ? 1 :
                            newReactionKey == -1 && currentReactionKey == -1 ? 0 :
                                    newReactionKey == -1 ? -1 : 0;
            commentClient.updateReactToComment(newReactionKey, commentId, userId);
            ReactionResponses reactionResponses = newReactionKey == 1 ? ReactionResponses.LIKED :
                    newReactionKey == 0 ? NOREACTION :
                            DISLIKE;
            return reactionResponses.toString();
        } catch (Exception e) {
            log.error("Exception occured at reactToComment", e);
            throw new CommentServiceTechnicalException(ErrorCode.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }


}
