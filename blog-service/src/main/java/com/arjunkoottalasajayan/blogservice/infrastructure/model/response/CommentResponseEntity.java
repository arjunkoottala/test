package com.arjunkoottalasajayan.blogservice.infrastructure.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommentResponseEntity {
    @JsonProperty("commentId")
    private Long commentId;
    @JsonProperty("comment")
    private String comment;
    @JsonProperty("commentedUserId")
    private Long commentedUserId;
    @JsonProperty("createdTime")
    private Timestamp createdTime;
    @JsonProperty("likedUserIds")
    private List<Long> likedUserIds;
    @JsonProperty("dislikedUserIds")
    private List<Long> dislikedUserIds;
}
