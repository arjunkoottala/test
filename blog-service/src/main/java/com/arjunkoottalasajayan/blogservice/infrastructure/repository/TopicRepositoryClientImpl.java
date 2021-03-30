package com.arjunkoottalasajayan.blogservice.infrastructure.repository;

import com.arjunkoottalasajayan.blogservice.domain.model.TopicBO;
import com.arjunkoottalasajayan.blogservice.domain.port.spi.TopicRepositoryClient;
import com.arjunkoottalasajayan.blogservice.infrastructure.model.TopicEntity;
import com.arjunkoottalasajayan.blogservice.infrastructure.port.TopicRepository;
import lombok.extern.slf4j.Slf4j;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class TopicRepositoryClientImpl implements TopicRepositoryClient {

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    Mapper mapper;

    @Override
    public List<TopicBO> fetchTopics() {
        log.info("Inside fetchTopics of TopicRepositoryClientImpl class");
        List<TopicBO> topicBOList = new ArrayList<>();
        for (TopicEntity topicEntity : topicRepository.findAll())
            topicBOList.add(mapper.map(topicEntity, TopicBO.class));
        return topicBOList;
    }

    @Override
    public TopicBO saveTopic(TopicBO topicBO) {
        log.info("Inside saveTopic of TopicRepositoryClientImpl class");
        return mapper.map(topicRepository.save(
                mapper.map(topicBO, TopicEntity.class)
        ), TopicBO.class);
    }

    @Override
    public List<TopicBO> fetchTopicsByIdList(List<Integer> topicIdList) {
        log.info("Inside fetchTopicsByIdList of TopicRepositoryClientImpl class");
        List<TopicBO> topicBOList = new ArrayList<>();
        for (TopicEntity topicEntity : topicRepository.findAllById(topicIdList))
            topicBOList.add(mapper.map(topicEntity, TopicBO.class));
        return topicBOList;
    }
}
