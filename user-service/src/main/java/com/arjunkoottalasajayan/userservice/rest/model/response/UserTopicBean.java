package com.arjunkoottalasajayan.userservice.rest.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTopicBean {
    public Long userId;
    public Integer topicId;
}
