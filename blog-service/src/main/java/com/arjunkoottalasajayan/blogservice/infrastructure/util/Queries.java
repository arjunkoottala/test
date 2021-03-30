package com.arjunkoottalasajayan.blogservice.infrastructure.util;

public class Queries {

    public final static String fetchUserIdQuery = "Select created_user_id from blog_entity where blog_id = ?1";
    public final static String updateBlogDetails = "Update blog_entity set blog_heading = ?1, blog_content = ?2 , updated_Time = CURRENT_TIMESTAMP() where blog_id = ?3";
    public final static String fetchReactionKeyQuery = "Select max(reaction_Key) from blog_reaction_entity where blog_id = ?1 and reacted_User_Id = ?2 ";
    public final static String updateReactionQuery = "Update blog_reaction_entity set  reaction_Key = ?1 where blog_id = ?2 and reacted_User_Id = ?3";
    public final static String fetchByTopicIdQuery = "Select * from blog_entity where blog_topic_id = ?1";
}
