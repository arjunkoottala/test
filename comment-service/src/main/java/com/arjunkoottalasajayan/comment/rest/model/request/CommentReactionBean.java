package com.arjunkoottalasajayan.comment.rest.model.request;

import com.sun.istack.NotNull;
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
public class CommentReactionBean {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer reactionId;
    @NotNull
    private Long commentId;
    @NotNull
    private Long userId;
    private Integer reactionKey = 0;
}
