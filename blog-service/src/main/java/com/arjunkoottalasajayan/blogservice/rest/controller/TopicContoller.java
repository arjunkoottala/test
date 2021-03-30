package com.arjunkoottalasajayan.blogservice.rest.controller;

import com.arjunkoottalasajayan.blogservice.domain.port.api.TopicService;
import com.arjunkoottalasajayan.blogservice.rest.model.TopicBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/topic")
@RestController
@Slf4j
public class TopicContoller {

    @Autowired
    TopicService topicService;

    @GetMapping("")
    public ResponseEntity<List<TopicBean>> fetchTopics() {
        log.info("Inside fetchTopics of TopicController class");
        return new ResponseEntity<>(topicService.fetchTopics(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<TopicBean> saveTopic(@RequestBody TopicBean topicBean) {
        log.info("Inside saveTopic of TopicController class");
        return new ResponseEntity<>(topicService.saveTopic(topicBean), HttpStatus.OK);

    }

    @GetMapping("/{topicIdList}")
    public ResponseEntity<List<TopicBean>> findTopicBtIds(@PathVariable List<Integer> topicIdList) {

        return new ResponseEntity<>(topicService.fetchTopicsByIds(topicIdList), HttpStatus.OK);
    }
}
