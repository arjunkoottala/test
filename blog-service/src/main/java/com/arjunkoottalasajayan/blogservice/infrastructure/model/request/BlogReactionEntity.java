package com.arjunkoottalasajayan.blogservice.infrastructure.model.request;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "blog_reaction_entity")
public class BlogReactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer blogReactionId;
    @NotNull
    private Long blogId;
    @NotNull
    private Long reactedUserId;
    private Integer reactionKey = 0;
}
