package com.arjunkoottalasajayan.blogservice.rest.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BlogReactionBean {
    private Integer blogReactionId;
    private Long blogId;
    private Long reactedUserId;
    private Integer reactionKey = 0;
}
