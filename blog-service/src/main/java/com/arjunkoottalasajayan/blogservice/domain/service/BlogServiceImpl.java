package com.arjunkoottalasajayan.blogservice.domain.service;

import com.arjunkoottalasajayan.blogservice.domain.model.request.BlogBO;
import com.arjunkoottalasajayan.blogservice.domain.model.response.BlogResponseBO;
import com.arjunkoottalasajayan.blogservice.domain.port.api.BlogService;
import com.arjunkoottalasajayan.blogservice.domain.port.spi.BlogRepositoryClient;
import com.arjunkoottalasajayan.blogservice.domain.util.ReactionResponses;
import com.arjunkoottalasajayan.blogservice.infrastructure.model.request.BlogEntity;
import com.arjunkoottalasajayan.blogservice.infrastructure.model.request.BlogReactionEntity;
import com.arjunkoottalasajayan.blogservice.infrastructure.model.response.BlogResponseEntity;
import com.arjunkoottalasajayan.blogservice.infrastructure.port.CommentServiceData;
import com.arjunkoottalasajayan.blogservice.rest.error.BlogServiceTechnicalException;
import com.arjunkoottalasajayan.blogservice.rest.error.ErrorCode;
import com.arjunkoottalasajayan.blogservice.rest.model.request.BlogBean;
import lombok.extern.slf4j.Slf4j;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static com.arjunkoottalasajayan.blogservice.domain.util.ReactionResponses.*;
import static com.arjunkoottalasajayan.blogservice.domain.util.ResponseMessage.DELETED_SUCCESSFULLY;
import static com.arjunkoottalasajayan.blogservice.domain.util.ResponseMessage.UPDATED_SUCCESSFULLY;

@Service
@Slf4j
public class BlogServiceImpl implements BlogService {

    @Autowired
    BlogRepositoryClient blogRepositoryCLient;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    Mapper mapper;

    @Autowired
    CommentServiceData commentServiceData;

    @Value("${url.commentService.fetchByBlogId}")
    private String fetchByBlogIdUrl;

    @Override
    public BlogResponseBO saveBlog(BlogBean blogBean) {
        try {


            BlogBO blogBo = blogRepositoryCLient.saveBlog(
                    mapper.map(blogBean, BlogBO.class));

            blogRepositoryCLient.saveReaction(blogBo.getBlogId(), blogBo.getCreatedUserId(), null);


            return mapper.map(blogBo, BlogResponseBO.class);

        } catch (Exception e) {

            log.error("Error occured at saveBlog of BlogServiceImpl");
            throw new BlogServiceTechnicalException(ErrorCode.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @Override
    public String saveBlogReaction(BlogReactionEntity blogReactionEntity) {
        try {

            log.info("inside saveBlogReaction method of BlogServiceImpl {}", blogReactionEntity);

            Integer reactionKey = blogRepositoryCLient.fetchReactionKey(blogReactionEntity.getBlogId(), blogReactionEntity.getReactedUserId());
            if (reactionKey == null)
                blogRepositoryCLient.saveReaction(blogReactionEntity.getBlogId(),
                        blogReactionEntity.getReactedUserId(),blogReactionEntity.getReactionKey());
            else if(reactionKey != blogReactionEntity.getReactionKey())
                blogRepositoryCLient.updateBlogReaction(blogReactionEntity);
            return checkReaction(blogReactionEntity.getReactionKey());

        } catch (Exception ex) {
            log.error("Exception at saveBlogReaction method of BlogServiceImpl", ex);
            throw new BlogServiceTechnicalException(ErrorCode.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
    }

    @Override
    public List<BlogResponseBO> fetchBlogByTopicId(Integer topicId) {
        log.info("Inside fetchBlogByTopicId method of BlogServiceImpl");
        List<BlogResponseEntity> blogResponseEntityList = blogRepositoryCLient.fetchBlogByTopicId(topicId);
        List<BlogResponseBO> blogResponseBOList = new ArrayList<>();
        blogResponseEntityList.stream().forEach(blogResponseEntity -> {
            blogResponseEntity.setComments(commentServiceData.fetchComments(blogResponseEntity.getBlogId()));
            blogResponseBOList.add(mapper.map(blogResponseEntity, BlogResponseBO.class));
        });
        return blogResponseBOList;
    }

    @Override
    public boolean isBlogPresent(Long blogId) {
        return blogRepositoryCLient.isBlogPresent(blogId);
    }

    @Override
    public String updateBlog(BlogBO blogBO) {
        try {
            log.info("inside updateBlog method of BlogServiceImpl class");
            blogRepositoryCLient.updateBlog(mapper.map(blogBO, BlogEntity.class));
            log.info("Successfully updated blog with blogid = {}", blogBO.getBlogId());
            return UPDATED_SUCCESSFULLY.toString();
        }catch (Exception ex){
            log.info("Error occured while updating blog");
            throw new BlogServiceTechnicalException(ErrorCode.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
    }

    @Override
    public Long fetchUserIdByBlogId(Long blogId) {
        return blogRepositoryCLient.fetchUserIdByBlogId(blogId);
    }

    @Override
    public String deleteBlog(Long blogId) {try {
        log.info("inside deleteBlog method of BlogServiceImpl class");
        blogRepositoryCLient.deleteBlog(blogId);
        log.info("Successfully deleted blog with blogid = {}", blogId);
        return DELETED_SUCCESSFULLY.toString();
    }catch (Exception ex){
        log.info("Error occured while updating blog");
        throw new BlogServiceTechnicalException(ErrorCode.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
    }
    }

    private String checkReaction(int key){
        switch(key){
            case 1 : return LIKED.toString();
            case -1 : return DISLIKE.toString();
            default: return NOREACTION.toString();
        }
    }
}
