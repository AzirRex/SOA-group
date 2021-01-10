package com.example.applyprovide.dao;


import com.example.applyprovide.entities.Comment;
import com.example.applyprovide.entities.Forum;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface ForumDao {

    @Select("select * from forum")
    public List<Forum> queryAll();

    @Insert("insert into forum (user_id,time,content,type) values(#{user_id},#{time},#{content},#{type})")
    public boolean newForum(Forum forum);

    @Insert("insert into comment (post_id,user_id,time,content,type) values(#{post_id},#{user_id},#{time},#{content},#{type})")
    public boolean addComment(Comment comment);

    @Delete("delete from forum where post_id=#{post_id}")
    boolean deleteForumById(Integer postId);

    @Delete( "delete from comment where post_id=#{post_id}")
    boolean deleteCommentByPostId(Integer postId);

    @Delete("delete from comment where comment_id=#{comment_id}")
    boolean deleteCommentById(Integer commentId);

    @Select("select * from forum where post_id=#{post_id}")
    Forum queryPostByPostId(Integer postId);

    @Select("select * from comment where post_id=#{post_id}")
    List<Comment> queryCommentByPostId(Integer postId);



}
