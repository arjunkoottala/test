package com.arjunkoottalasajayan.blogservice.infrastructure.repository;

import com.arjunkoottalasajayan.blogservice.domain.model.request.BlogBO;
import com.arjunkoottalasajayan.blogservice.domain.model.response.BlogResponseBO;
import com.arjunkoottalasajayan.blogservice.domain.port.spi.BlogRepositoryClient;
import com.arjunkoottalasajayan.blogservice.infrastructure.model.request.BlogEntity;
import com.arjunkoottalasajayan.blogservice.infrastructure.model.request.BlogReactionEntity;
import com.arjunkoottalasajayan.blogservice.infrastructure.model.response.BlogResponseEntity;
import com.arjunkoottalasajayan.blogservice.infrastructure.port.BlogReactionRepository;
import com.arjunkoottalasajayan.blogservice.infrastructure.port.BlogRepository;
import com.arjunkoottalasajayan.blogservice.rest.model.request.BlogBean;
import lombok.extern.slf4j.Slf4j;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class BlogRepositoryClientImpl implements BlogRepositoryClient {

    @Autowired
    BlogRepository blogRepository;

    @Autowired
    BlogReactionRepository blogReactionRepository;

    @Autowired
    Mapper mapper;

    @Override
    public BlogBO saveBlog(BlogBO blogBO) {
        return mapper.map(
                blogRepository.save(mapper.map(blogBO, BlogEntity.class)), BlogBO.class);
    }

    @Override
    public Integer fetchReactionKey(Long blogId, Long userId) {
        return fetchReactionKey(blogId, userId);
    }

    @Override
    public List<BlogResponseEntity> fetchBlogByTopicId(Integer topicId) {
        List<BlogEntity> blogEntityList = blogRepository.fetchBlogByTopicId(topicId);
        List<BlogResponseEntity> blogResponseEntityList = new ArrayList<>();
        blogEntityList.stream().forEach(blogEntity ->
                blogResponseEntityList.add(mapper.map(blogEntity, BlogResponseEntity.class)));
        return blogResponseEntityList;
    }

    @Override
    public void saveReaction(Long blogId, Long userId, Integer reactionKey) {
        log.info("Inside saveBlogReaction of BlogRepositoryClientImpl");
        BlogReactionEntity blogReactionEntity = new BlogReactionEntity();
        reactionKey = reactionKey == null ? 0 : reactionKey;
        blogReactionEntity.setBlogId(blogId);
        blogReactionEntity.setReactedUserId(userId);
        blogReactionEntity.setReactionKey(reactionKey);
        blogReactionRepository.save(blogReactionEntity);
        log.info("Successfully saved reaction with blogId : {} , userId : {} with reactionKey : {}",
                blogId, userId, reactionKey);
    }

    @Override
    public boolean isBlogPresent(Long blogId) {
        return (blogRepository.findById(blogId)!=null);
    }

    @Override
    public void updateBlogReaction(BlogReactionEntity blogReactionEntity) {
        blogReactionRepository.updateReaction(blogReactionEntity.getReactedUserId(),
                blogReactionEntity.getBlogId(),
                blogReactionEntity.getReactedUserId());
    }

    @Override
    public void updateBlog(BlogEntity blogEntity) {
        blogRepository.updateBlog(blogEntity.getBlogHeading(),blogEntity.getBlogContent(), blogEntity.getBlogId());
    }


    @Override
    public Long fetchUserIdByBlogId(Long blogId) {
        return blogRepository.fetchUserIdByBlogId(blogId);
    }

    @Override
    public void deleteBlog(Long blogId) {
        blogRepository.deleteById(blogId);
    }
}
