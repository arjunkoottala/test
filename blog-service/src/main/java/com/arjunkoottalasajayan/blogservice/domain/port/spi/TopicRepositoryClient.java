package com.arjunkoottalasajayan.blogservice.domain.port.spi;

import com.arjunkoottalasajayan.blogservice.domain.model.TopicBO;

import java.util.List;

public interface TopicRepositoryClient {

    List<TopicBO> fetchTopics();

    TopicBO saveTopic(TopicBO map);

    List<TopicBO> fetchTopicsByIdList(List<Integer> topicIdList);
}
