package com.arjunkoottalasajayan.blogservice.infrastructure.port;

import com.arjunkoottalasajayan.blogservice.infrastructure.model.request.BlogEntity;
import com.arjunkoottalasajayan.blogservice.infrastructure.util.Queries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<BlogEntity, Long> {

    @Query(value = Queries.fetchByTopicIdQuery, nativeQuery = true)
    List<BlogEntity> fetchBlogByTopicId(Integer topicId);

    @Transactional
    @Modifying
    @Query(value = Queries.updateBlogDetails, nativeQuery = true)
    void updateBlog(String blogContent, String blogContent1, Long blogId);

    @Query(value = Queries.fetchUserIdQuery, nativeQuery = true)
    Long fetchUserIdByBlogId(Long blogId);
}
