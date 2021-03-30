package com.arjunkoottalasajayan.blogservice.rest.delegate;

import com.arjunkoottalasajayan.blogservice.domain.model.request.BlogBO;
import com.arjunkoottalasajayan.blogservice.domain.port.api.BlogService;
import com.arjunkoottalasajayan.blogservice.infrastructure.model.request.BlogReactionEntity;
import com.arjunkoottalasajayan.blogservice.rest.error.BlogServiceBusinessException;
import com.arjunkoottalasajayan.blogservice.rest.error.ErrorCode;
import com.arjunkoottalasajayan.blogservice.rest.model.request.BlogBean;
import com.arjunkoottalasajayan.blogservice.rest.model.response.BlogResponseBean;
import lombok.extern.slf4j.Slf4j;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.arjunkoottalasajayan.blogservice.domain.util.ReactionResponses.*;

@Component
@Slf4j
public class BlogDelegate {

    @Autowired
    BlogService blogService;

    @Autowired
    Mapper mapper;

    public BlogResponseBean saveBlog(BlogBean blogBean) {
        log.info("Inside saveblog of delegate class");
        Long blogId = blogBean.getBlogId();
        if(blogId !=null && blogService.isBlogPresent(blogId))
            throw new BlogServiceBusinessException(ErrorCode.BLOG_ALREADY_EXIST);
        return mapper.map(blogService.saveBlog(blogBean), BlogResponseBean.class);
    }

    public String saveBlogReaction(BlogReactionEntity blogReactionEntity) {
        if(!blogService.isBlogPresent(blogReactionEntity.getBlogId()))
            throw new BlogServiceBusinessException(ErrorCode.BLOG_NOT_FOUND);

        return blogService.saveBlogReaction(blogReactionEntity);
    }

    public List<BlogResponseBean> fetchBlogByTopicId(Integer topicId) {
        return blogService.fetchBlogByTopicId(topicId).stream().map(
                blogResponseBO -> mapper.map(blogResponseBO, BlogResponseBean.class)).collect(Collectors.toList());

    }

    public String updateBlog(BlogBean blogBean) {
        if(!blogService.isBlogPresent(blogBean.getBlogId()))
            throw new BlogServiceBusinessException(ErrorCode.BLOG_NOT_FOUND);
        if(!blogBean.getCreatedUserId().equals(blogService.fetchUserIdByBlogId(blogBean.getBlogId())))
            throw new BlogServiceBusinessException(ErrorCode.WRONG_OWNER);
        return blogService.updateBlog(
                mapper.map(blogBean, BlogBO.class));
    }

    public String deleteBlog(Long userId, Long blogId) {
        if(!blogService.isBlogPresent(blogId))
            throw new BlogServiceBusinessException(ErrorCode.BLOG_NOT_FOUND);
        if(!userId.equals(blogService.fetchUserIdByBlogId(blogId)))
            throw new BlogServiceBusinessException(ErrorCode.WRONG_OWNER);
        return blogService.deleteBlog(blogId);
    }
}
