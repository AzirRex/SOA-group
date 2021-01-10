package com.example.applyprovide.service;


import com.example.applyprovide.entities.Comment;
import com.example.applyprovide.entities.Forum;

import java.util.List;


public interface ForumService {
    public List<Forum> queryAll();
    public boolean addForum(Forum forum);
    public boolean addComment(Comment comment);
    boolean deletePostById(Integer post_id);
    boolean deleteCommentById(Integer comment_id);
    Forum queryPostById(Integer post_id);
    List<Comment> queryCommentByPostId(Integer post_id);
    boolean deleteCommentByPostId(Integer post_id);

}
