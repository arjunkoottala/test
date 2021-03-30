package com.arjunkoottalasajayan.blogservice.infrastructure.port;

import com.arjunkoottalasajayan.blogservice.infrastructure.model.TopicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<TopicEntity, Integer> {
}
