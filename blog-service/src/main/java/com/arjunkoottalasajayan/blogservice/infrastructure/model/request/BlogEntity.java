package com.arjunkoottalasajayan.blogservice.infrastructure.model.request;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "blog_entity")
public class BlogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long blogId;
    @NotNull
    private String blogHeading;
    @NotNull
    private String blogContent;
    @NotNull
    private Long blogTopicId;
    @NotNull
    private Long createdUserId;
    private Integer numberOfHits = 0;
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Timestamp createdTime;
    @UpdateTimestamp
    private Timestamp updatedTime;
}
