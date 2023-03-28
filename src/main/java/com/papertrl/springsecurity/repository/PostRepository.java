package com.papertrl.springsecurity.repository;

import com.papertrl.springsecurity.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Integer> {


}
