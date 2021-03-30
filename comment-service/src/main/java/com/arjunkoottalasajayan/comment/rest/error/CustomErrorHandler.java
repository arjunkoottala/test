package com.arjunkoottalasajayan.comment.rest.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class CustomErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CommentServiceTechnicalException.class)
    public ResponseEntity<Object> handleCommentServiceTechnicalException(CommentServiceTechnicalException ex) {
        final ErrorNode errorNode = new ErrorNode(ex.getError(), ex.getError().getMessage());
        log.error("Comment service failed to process successfully. There is a technical exception", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorNode);
    }

    @ExceptionHandler(CommentServiceBusinessException.class)
    public ResponseEntity<Object> handleCommentServiceBusinessException(CommentServiceBusinessException ex) {
        final ErrorNode errorNode = new ErrorNode(ex.getError(), ex.getError().getMessage());
        log.error("Comment service failed to process successfully. There is a business exception", ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorNode);
    }

}
