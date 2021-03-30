package com.arjunkoottalasajayan.comment.infrastructure.port;

import com.arjunkoottalasajayan.comment.infrastructure.model.request.CommentReactionEntity;
import com.arjunkoottalasajayan.comment.infrastructure.utils.Queries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CommentReactionRepository extends JpaRepository<CommentReactionEntity, Integer> {

    @Query(value = Queries.getLikedUserIdListQuery, nativeQuery = true)
    public List<Long> fetchLikedUserIdsByCommentId(Long commentId);

    @Query(value = Queries.getDislikedUserIdListQuery, nativeQuery = true)
    public List<Long> fetchDislikedUserIdsByCommentId(Long commentId);

    @Query(value = Queries.fetchReactionKeyQuery, nativeQuery = true)
    public Integer fetchReactionKey(Long commentId, Long userId);

    @Transactional
    @Modifying
    @Query(value = Queries.ReactToCommentQuery, nativeQuery = true)
    public void reactToComment(Integer newReactionKey, Long commentId, Long userId);

}
