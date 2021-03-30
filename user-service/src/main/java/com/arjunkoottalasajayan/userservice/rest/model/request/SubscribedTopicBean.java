package com.arjunkoottalasajayan.userservice.rest.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubscribedTopicBean {
    public Long userId;
    public List<Integer> topicIds;
}
