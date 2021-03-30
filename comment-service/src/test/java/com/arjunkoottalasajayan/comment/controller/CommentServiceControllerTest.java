package com.arjunkoottalasajayan.comment.controller;

import com.arjunkoottalasajayan.comment.rest.controller.CommentServiceController;
import com.arjunkoottalasajayan.comment.rest.model.request.CommentBean;
import com.arjunkoottalasajayan.comment.rest.model.request.CommentReactionBean;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CommentServiceControllerTest {
    @InjectMocks
    CommentServiceController commentServiceController;

    @Mock
    CommentBean commentBean;

    @Mock
    CommentReactionBean commentReactionBean;

    @Before
    public void Initialize() {
        commentBean.setCommentedUserId(123L);
        commentBean.setCommentId(123L);
        commentBean.setComment("Test");
        commentBean.setCommentToId(222L);
        commentBean.setLevel(1);
        commentBean.setCreatedTime(null);

        commentReactionBean.setReactionId(1);
        commentReactionBean.setCommentId(333L);
        commentReactionBean.setUserId(444L);
        commentReactionBean.setReactionKey(1);

    }

    @Test
    public void saveCommentTest() {
        commentServiceController.saveComment(commentBean);
    }

    @Test
    public void fetchCommentBySourceIDTest() {
        commentServiceController.fetchCommentBySourceID(commentBean.getCommentId(), commentBean.getLevel());
    }

    @Test
    public void fetchCommentByUserIDTest() {
        commentServiceController.fetchCommentByUserID(commentBean.getCommentedUserId());
    }

    @Test
    public void commentReactionTest() {
        commentServiceController.commentReaction(commentReactionBean);
    }
}
