package com.arjunkoottalasajayan.blogservice.domain.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BlogReactionBO {
    private Integer blogReactionId;
    private Long blogId;
    private Long reactedUserId;
    private Integer reactionKey = 0;
}
