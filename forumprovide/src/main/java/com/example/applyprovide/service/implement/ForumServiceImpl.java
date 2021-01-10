package com.example.applyprovide.service.implement;


import com.example.applyprovide.dao.ForumDao;
import com.example.applyprovide.entities.Comment;
import com.example.applyprovide.entities.Forum;
import com.example.applyprovide.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ForumServiceImpl implements ForumService {

    @Resource
    ForumDao forumMapper;


    @Override
    public List<Forum> queryAll() {
        return forumMapper.queryAll();
    }

    @Override
    public boolean addForum(Forum forum) {
        forumMapper.newForum(forum);
        return true;
    }

    @Override
    public boolean addComment(Comment comment) {
        forumMapper.addComment(comment);
        return true;
    }

    @Override
    public boolean deletePostById(Integer post_id) {
        forumMapper.deleteForumById(post_id);
        return false;
    }

    @Override
    public boolean deleteCommentById(Integer comment_id) {
        forumMapper.deleteCommentById(comment_id);
        return false;
    }

    @Override
    public Forum queryPostById(Integer post_id) {
        return forumMapper.queryPostByPostId(post_id);
    }

    @Override
    public List<Comment> queryCommentByPostId(Integer post_id) {
        return forumMapper.queryCommentByPostId(post_id);
    }

    @Override
    public boolean deleteCommentByPostId(Integer post_id) {
        return  forumMapper.deleteCommentByPostId(post_id);
    }


}
