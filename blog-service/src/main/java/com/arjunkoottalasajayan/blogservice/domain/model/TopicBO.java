package com.arjunkoottalasajayan.blogservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TopicBO {
    private Integer topicId;
    private String topicName;
}
