package com.arjunkoottalasajayan.blogservice.domain.port.spi;

import com.arjunkoottalasajayan.blogservice.domain.model.request.BlogBO;
import com.arjunkoottalasajayan.blogservice.domain.model.response.BlogResponseBO;
import com.arjunkoottalasajayan.blogservice.infrastructure.model.request.BlogEntity;
import com.arjunkoottalasajayan.blogservice.infrastructure.model.request.BlogReactionEntity;
import com.arjunkoottalasajayan.blogservice.infrastructure.model.response.BlogResponseEntity;
import com.arjunkoottalasajayan.blogservice.rest.model.request.BlogBean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BlogRepositoryClient {
    BlogBO saveBlog(BlogBO blogBo);

    Integer fetchReactionKey(Long blogId, Long userId);

    List<BlogResponseEntity> fetchBlogByTopicId(Integer topicId);

    void saveReaction(Long blogId, Long userId, Integer reactionKey);

    boolean isBlogPresent(Long blogId);

    void updateBlogReaction(BlogReactionEntity blogReactionEntity);

    void updateBlog(BlogEntity blogEntity);

    void deleteBlog(Long blogId);

    Long fetchUserIdByBlogId(Long blogId);
}
