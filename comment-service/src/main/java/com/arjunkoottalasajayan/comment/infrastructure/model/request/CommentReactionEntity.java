package com.arjunkoottalasajayan.comment.infrastructure.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentReactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer reactionId;
    private Long commentId;
    private Long userId;
    private Integer reactionKey = 0;
}
