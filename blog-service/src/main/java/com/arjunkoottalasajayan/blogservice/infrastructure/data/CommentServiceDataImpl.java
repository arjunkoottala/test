package com.arjunkoottalasajayan.blogservice.infrastructure.data;

import com.arjunkoottalasajayan.blogservice.infrastructure.model.response.CommentResponseEntity;
import com.arjunkoottalasajayan.blogservice.infrastructure.port.CommentServiceData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@Slf4j
public class CommentServiceDataImpl implements CommentServiceData {

    @Autowired
    RestTemplate restTemplate;

    @Value("${url.commentService.fetchByBlogId}")
    private String fetchByBlogIdUrl;

    @Override
    public List<CommentResponseEntity> fetchComments(Long blogId) {
        try {
            String url = fetchByBlogIdUrl + blogId + "?level=1";
            ResponseEntity<List<CommentResponseEntity>> responseEntity =
                    restTemplate.exchange(
                            url,
                            HttpMethod.GET,
                            null,
                            new ParameterizedTypeReference<List<CommentResponseEntity>>() {
                            }
                    );
            List<CommentResponseEntity> users = responseEntity.getBody();
            return users;
        } catch (Exception e) {
            log.info("exception ", e);
            return null;
        }
    }

}
