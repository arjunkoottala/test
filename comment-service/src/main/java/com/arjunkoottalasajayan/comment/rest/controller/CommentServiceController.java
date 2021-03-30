package com.arjunkoottalasajayan.comment.rest.controller;

import com.arjunkoottalasajayan.comment.rest.delegate.CommentServiceDataDelegate;
import com.arjunkoottalasajayan.comment.rest.model.request.CommentBean;
import com.arjunkoottalasajayan.comment.rest.model.request.CommentReactionBean;
import com.arjunkoottalasajayan.comment.rest.model.response.CommentResponseBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@Slf4j
public class CommentServiceController {

    @Autowired
    public CommentServiceDataDelegate commentServiceDataDelegate;

    @PostMapping("/")
    public ResponseEntity<CommentResponseBean> saveComment(@RequestBody CommentBean commentBean) {
        log.info("Inside saveComment method of CommentServiceController");
        return new ResponseEntity<>(commentServiceDataDelegate.saveComment(commentBean), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<CommentResponseBean>> fetchCommentBySourceID(@PathVariable("id") Long id,
                                                                            @RequestParam(value = "level", required = false) Integer level) {

        log.info("Inside fetchCommentBySourceID method of CommentServiceController");
        return new ResponseEntity<>(commentServiceDataDelegate.findCommentBySourceId(id, level), HttpStatus.OK);

    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<CommentResponseBean>> fetchCommentByUserID(@PathVariable("id") Long userId) {

        log.info("Inside fetchCommentByUserID method of CommentServiceController");
        return new ResponseEntity<>(commentServiceDataDelegate.findCommentsByUserId(userId), HttpStatus.OK);

    }

    @PostMapping("/react")
    public ResponseEntity<String> commentReaction(@RequestBody CommentReactionBean commentReactionBean) {

        log.info("Inside commentReaction method of CommentServiceController");
        return new ResponseEntity<>(commentServiceDataDelegate.reactToComment(commentReactionBean), HttpStatus.OK);

    }
}
