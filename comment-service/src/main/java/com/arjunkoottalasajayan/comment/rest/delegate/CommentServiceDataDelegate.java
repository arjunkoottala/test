package com.arjunkoottalasajayan.comment.rest.delegate;

import com.arjunkoottalasajayan.comment.domain.model.request.CommentBO;
import com.arjunkoottalasajayan.comment.domain.model.request.CommentReactionBO;
import com.arjunkoottalasajayan.comment.domain.model.response.CommentResponseBO;
import com.arjunkoottalasajayan.comment.domain.port.api.CommentService;
import com.arjunkoottalasajayan.comment.rest.model.request.CommentBean;
import com.arjunkoottalasajayan.comment.rest.model.request.CommentReactionBean;
import com.arjunkoottalasajayan.comment.rest.model.response.CommentResponseBean;
import lombok.extern.slf4j.Slf4j;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j

public class CommentServiceDataDelegate {

    @Autowired
    CommentService commentService;

    @Autowired
    Mapper mapper;

    public CommentResponseBean saveComment(CommentBean commentBean) {

        CommentResponseBO commentResponseBO = commentService.saveComment(mapper.map(commentBean, CommentBO.class));
        return mapper.map(commentResponseBO, CommentResponseBean.class);
    }

    public List<CommentResponseBean> findCommentBySourceId(Long id, Integer level) {
        level = level == null ? 1 : level;
        List<CommentResponseBO> commentResponseBOList = commentService.findCommentBySourceId(id, level);
        List<CommentResponseBean> commentResponseBeanList = commentResponseBOList.stream().map(commentResponseBO ->
                mapper.map(commentResponseBO, CommentResponseBean.class)).collect(Collectors.toList());
        log.info("Successfully fetched comments using sourceid {} ", commentResponseBeanList);
        return commentResponseBeanList;
    }

    public List<CommentResponseBean> findCommentsByUserId(Long userId) {
        List<CommentResponseBO> commentResponseBOList = commentService.findCommentsByUserId(userId);
        List<CommentResponseBean> commentResponseBeanList = commentResponseBOList.stream().map(commentResponseBO ->
                mapper.map(commentResponseBO, CommentResponseBean.class)).collect(Collectors.toList());
        return commentResponseBeanList;
    }

    public String reactToComment(CommentReactionBean commentReactionBean) {

        return commentService.reactToComment(mapper.map(commentReactionBean, CommentReactionBO.class));
    }
}
