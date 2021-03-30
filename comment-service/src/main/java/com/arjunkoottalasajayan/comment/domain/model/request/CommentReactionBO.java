package com.arjunkoottalasajayan.comment.domain.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentReactionBO {

    private Integer reactionId;
    private Long commentId;
    private Long userId;
    private Integer reactionKey = 0;
}
