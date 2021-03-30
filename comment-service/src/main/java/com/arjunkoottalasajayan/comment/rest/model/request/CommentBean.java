package com.arjunkoottalasajayan.comment.rest.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentBean {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long commentId;
    private String comment;
    private Long commentedUserId;
    private Long commentToId;   // ex: blogId or commentId itself in the case of reply to a comment
    private Integer level;  // ex: value 1 for reply to blog || 2 for reply to comment
    @CreationTimestamp
    private Timestamp createdTime;
}
