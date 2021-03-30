package com.arjunkoottalasajayan.blogservice.rest.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class CustomErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BlogServiceTechnicalException.class)
    public ResponseEntity<Object> handleBlogServiceTechnicalException(BlogServiceTechnicalException ex) {
        final ErrorNode errorNode = new ErrorNode(ex.getError(), ex.getError().getMessage());
        log.error("Blog service failed to process successfully. There is a technical exception", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorNode);
    }

    @ExceptionHandler(BlogServiceBusinessException.class)
    public ResponseEntity<Object> handleBlogServiceBusinessException(BlogServiceBusinessException ex) {
        final ErrorNode errorNode = new ErrorNode(ex.getError(), ex.getError().getMessage());
        log.error("Blog service failed to process successfully. There is a business exception", ex);
        return new ResponseEntity<>(errorNode,HttpStatus.BAD_REQUEST);
    }

}
