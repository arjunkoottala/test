package com.arjunkoottalasajayan.blogservice.domain.port.api;

import com.arjunkoottalasajayan.blogservice.rest.model.TopicBean;

import java.util.List;

public interface TopicService {
    List<TopicBean> fetchTopics();

    TopicBean saveTopic(TopicBean topicBean);

    List<TopicBean> fetchTopicsByIds(List<Integer> topicIdList);
}
