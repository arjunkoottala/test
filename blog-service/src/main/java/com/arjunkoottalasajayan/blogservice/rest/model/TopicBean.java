package com.arjunkoottalasajayan.blogservice.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TopicBean {
    private Integer topicId;
    private String topicName;
}
