package com.arjunkoottalasajayan.blogservice.infrastructure.port;

import com.arjunkoottalasajayan.blogservice.infrastructure.model.request.BlogReactionEntity;
import com.arjunkoottalasajayan.blogservice.infrastructure.util.Queries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BlogReactionRepository extends JpaRepository<BlogReactionEntity, Integer> {

    @Query(value = Queries.fetchReactionKeyQuery, nativeQuery = true)
    Integer fetchReactionKey(Long blogId, Long userId);


    @Transactional
    @Modifying
    @Query(value = Queries.updateReactionQuery, nativeQuery = true)

    void updateReaction(Long reactedUserId1, Long blogId, Long reactedUserId);
}
