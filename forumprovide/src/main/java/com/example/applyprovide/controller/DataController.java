package com.example.applyprovide.controller;


import com.example.applyprovide.entities.Comment;
import com.example.applyprovide.entities.Forum;
import com.example.applyprovide.service.IMessage;
import com.example.applyprovide.service.implement.ForumServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class DataController {
    @Resource
    private ForumServiceImpl forumService;
    @Resource
    private IMessage iMessage;



    @GetMapping("/forum/all")
    public List<Forum> queryAll()
    {

        LocalDateTime time = LocalDateTime.now();
        String msg = time.toString()+"-"+"select-all"+"-forum" ;
        iMessage.send(msg);
        return forumService.queryAll();
    }

    @RequestMapping("/addForum/{user_id}/{content}/{type}")
    public boolean addForum(
            @PathVariable Integer user_id,
            @PathVariable String content,
            @PathVariable String type
            ){
        Forum forum=new Forum();
        forum.setContent(content);
        forum.setType(type);
        forum.setUser_id(user_id);
        forum.setTime((System.currentTimeMillis()));
        forumService.addForum(forum);
        LocalDateTime time = LocalDateTime.now();
        String msg = time.toString()+"-"+"insert-user_id="+user_id+"-forum" ;
        iMessage.send(msg);
        return true;
    }

    @RequestMapping("/addComment/{post_id}/{user_id}/{content}/{type}")
    public boolean addComment(
            @PathVariable Integer post_id,
            @PathVariable Integer user_id,
            @PathVariable String content,
            @PathVariable String type
    ){
        Comment comment = new Comment();
        comment.setPost_id(post_id);
        comment.setContent(content);
        comment.setType(type);
        comment.setUser_id(user_id);
        comment.setTime((System.currentTimeMillis()));
        forumService.addComment(comment);
        LocalDateTime time = LocalDateTime.now();
        String msg = time.toString()+"-"+"insert-post_id="+post_id+"-Comment" ;
        iMessage.send(msg);
        return true;
    }
    @RequestMapping("deleteCommentByCommentId/{comment_id}")
    public boolean deleteCommentById(@PathVariable Integer comment_id)
    {
        System.out.println("!!!");
        forumService.deleteCommentById(comment_id);
        LocalDateTime time = LocalDateTime.now();
        String msg = time.toString()+"-"+"delete-commentId="+comment_id+"-Comment" ;
        iMessage.send(msg);
        return true;
    }

    @RequestMapping("deletePostByPostId/{post_id}")
    public boolean deletePostById(@PathVariable Integer post_id)
    {
        forumService.deletePostById(post_id);
        forumService.deleteCommentByPostId(post_id);
        LocalDateTime time = LocalDateTime.now();
        String msg = time.toString()+"-"+"delete-post_id="+post_id+"-forum" ;
        iMessage.send(msg);
        return true;
    }

    @RequestMapping("queryPostByPostId/{post_id}")
    public Forum queryPostById(@PathVariable Integer post_id)
    {
        System.out.println(post_id);
        LocalDateTime time = LocalDateTime.now();
        String msg = time.toString()+"-"+"select-post_id="+post_id+"-forum" ;
        iMessage.send(msg);
        return forumService.queryPostById(post_id);
    }

    @RequestMapping("queryCommentByPostId/{post_id}")
    public List<Comment> queryCommentByPostId(@PathVariable Integer post_id)
    {
        System.out.println(post_id);
        LocalDateTime time = LocalDateTime.now();
        String msg = time.toString()+"-"+"select-postId="+post_id+"-comment" ;
        iMessage.send(msg);
        return forumService.queryCommentByPostId(post_id);
    }
}
