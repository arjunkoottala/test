package com.arjunkoottalasajayan.blogservice.rest.controller;

import com.arjunkoottalasajayan.blogservice.infrastructure.model.request.BlogReactionEntity;
import com.arjunkoottalasajayan.blogservice.rest.delegate.BlogDelegate;
import com.arjunkoottalasajayan.blogservice.rest.model.request.BlogBean;
import com.arjunkoottalasajayan.blogservice.rest.model.response.BlogResponseBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blog")
@Slf4j
public class BlogController {

    @Autowired
    BlogDelegate blogDelegate;

    @PostMapping("/")
    public ResponseEntity<BlogResponseBean> saveBlog(@RequestBody BlogBean blogBean) {
        log.info("Inside saveBlog of BlogController");

        return new ResponseEntity<>(blogDelegate.saveBlog(blogBean), HttpStatus.OK);
    }

    @PostMapping("/react")
    public ResponseEntity<String> saveBlogReaction(@RequestBody BlogReactionEntity blogReactionEntity) {
        log.info("Inside saveBlogReaction of BlogController");

        return new ResponseEntity<>(blogDelegate.saveBlogReaction(blogReactionEntity), HttpStatus.OK);
    }

    @GetMapping("/topic/{id}")
    public ResponseEntity<List<BlogResponseBean>> fetchBlogByTopicId(@PathVariable("id") Integer topicId) {
        log.info("Inside fetchBlogByTopicId of BlogController");
        return new ResponseEntity<>(blogDelegate.fetchBlogByTopicId(topicId), HttpStatus.OK);
    }

    @PutMapping ("")
    public ResponseEntity<String> updateBlog(@RequestBody BlogBean blogBean){
        log.info("Inside updateBlog of BlogController");
        return new ResponseEntity<>(blogDelegate.updateBlog(blogBean), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public @ResponseBody ResponseEntity<String > deleteBlog(@PathVariable("id") Long userId,
                                              @RequestParam(value = "blogId", required = true) Long blogId){
        log.info("Inside deleteBlog of BlogController");
        return new ResponseEntity<>(blogDelegate.deleteBlog(userId, blogId), HttpStatus.OK);

    }

}
