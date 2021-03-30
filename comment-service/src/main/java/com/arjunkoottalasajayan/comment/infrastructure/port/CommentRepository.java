package com.arjunkoottalasajayan.comment.infrastructure.port;

import com.arjunkoottalasajayan.comment.infrastructure.model.request.CommentEntity;
import com.arjunkoottalasajayan.comment.infrastructure.utils.Queries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    @Query(value = Queries.getCommentByUserId, nativeQuery = true)
    public List<CommentEntity> getCommentsByUserId(Long userId);

    @Query(value = Queries.getCommentsBySourceId, nativeQuery = true)
    public List<CommentEntity> getCommentBySourceId(Long commentId, Integer level);
}