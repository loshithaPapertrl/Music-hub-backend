package com.papertrl.springsecurity.repository;

import com.papertrl.springsecurity.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Integer> {


    List<Post> findByUserId(int userId);
}
