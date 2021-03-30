package com.arjunkoottalasajayan.comment.infrastructure.utils;

public class Queries {

    public final static String getCommentByUserId = "Select * from comment_entity where commented_User_Id = ?1 ";
    public final static String getCommentsBySourceId = "Select * from comment_entity where comment_To_Id = ?1 and level = ?2 ";
    public final static String getLikedUserIdListQuery = "Select user_Id  from comment_reaction_entity where reaction_Key = 1 and comment_Id = ?1 ";
    public final static String getDislikedUserIdListQuery = "Select user_Id from comment_reaction_entity where reaction_Key = -1 and comment_Id = ?1 ";
    public final static String ReactToCommentQuery = "Update comment_reaction_entity set  reaction_Key = ?1 where comment_id = ?2 and user_id = ?3";
    public final static String fetchReactionKeyQuery = "Select max(reaction_Key) from comment_reaction_entity where comment_id = ?1 and user_id = ?2 ";
}
