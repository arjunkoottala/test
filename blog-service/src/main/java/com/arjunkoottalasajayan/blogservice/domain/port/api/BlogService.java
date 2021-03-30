package com.arjunkoottalasajayan.blogservice.domain.port.api;

import com.arjunkoottalasajayan.blogservice.domain.model.request.BlogBO;
import com.arjunkoottalasajayan.blogservice.domain.model.response.BlogResponseBO;
import com.arjunkoottalasajayan.blogservice.infrastructure.model.request.BlogReactionEntity;
import com.arjunkoottalasajayan.blogservice.rest.model.request.BlogBean;

import java.util.List;

public interface BlogService {
    BlogResponseBO saveBlog(BlogBean BlogBean);

    String saveBlogReaction(BlogReactionEntity blogReactionEntity);

    List<BlogResponseBO> fetchBlogByTopicId(Integer topicId);

    boolean isBlogPresent(Long blogId);

    String updateBlog(BlogBO blogBO);

    String deleteBlog(Long blogId);

    Long fetchUserIdByBlogId(Long blogId);

}
