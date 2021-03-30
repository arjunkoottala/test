package com.arjunkoottalasajayan.blogservice.domain.service;

import com.arjunkoottalasajayan.blogservice.domain.model.TopicBO;
import com.arjunkoottalasajayan.blogservice.domain.port.api.TopicService;
import com.arjunkoottalasajayan.blogservice.domain.port.spi.TopicRepositoryClient;
import com.arjunkoottalasajayan.blogservice.rest.error.ErrorCode;
import com.arjunkoottalasajayan.blogservice.rest.error.TopicServiceTechnicalException;
import com.arjunkoottalasajayan.blogservice.rest.model.TopicBean;
import lombok.extern.slf4j.Slf4j;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class TopicServiceImpl implements TopicService {

    @Autowired
    Mapper mapper;

    @Autowired
    TopicRepositoryClient topicRepositoryClient;

    @Override
    public List<TopicBean> fetchTopics() {
        try {

            log.info("Inside fetchTopics of TopicServiceImpl");
            List<TopicBO> topicBOList = topicRepositoryClient.fetchTopics();
            List<TopicBean> topicBeanList = new ArrayList<>();
            for (TopicBO topicBO : topicBOList)
                topicBeanList.add(mapper.map(topicBO, TopicBean.class));
            return topicBeanList;

        } catch (Exception ex) {

            log.error("Exception occured while fetching topics ", ex);
            throw new TopicServiceTechnicalException(ErrorCode.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);

        }
    }

    @Override
    public TopicBean saveTopic(TopicBean topicBean) {

        log.info("Inside saveTopic of TopicServiceImpl");
        return mapper.map(topicRepositoryClient.saveTopic(
                mapper.map(topicBean, TopicBO.class)
        ), TopicBean.class);
    }

    @Override
    public List<TopicBean> fetchTopicsByIds(List<Integer> topicIdList) {
        try {

            log.info("Inside fetchTopics of TopicServiceImpl");
            List<TopicBO> topicBOList = topicRepositoryClient.fetchTopicsByIdList(topicIdList);
            List<TopicBean> topicBeanList = new ArrayList<>();
            for (TopicBO topicBO : topicBOList)
                topicBeanList.add(mapper.map(topicBO, TopicBean.class));
            return topicBeanList;

        } catch (Exception ex) {

            log.error("Exception occured while fetching topics ", ex);
            throw new TopicServiceTechnicalException(ErrorCode.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);

        }
    }
}
