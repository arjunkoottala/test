package com.arjunkoottalasajayan.blogservice.infrastructure.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TopicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer topicId;
    private String topicName;
}
