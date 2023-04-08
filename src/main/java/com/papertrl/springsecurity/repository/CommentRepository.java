package com.papertrl.springsecurity.repository;

import com.papertrl.springsecurity.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CommentRepository extends JpaRepository<Comment,Integer> {


    List<Comment>findCommentByPostId(Integer postId);
}
