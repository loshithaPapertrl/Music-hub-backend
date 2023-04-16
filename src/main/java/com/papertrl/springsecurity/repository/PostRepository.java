package com.papertrl.springsecurity.repository;

import com.papertrl.springsecurity.dto.PostDto;
import com.papertrl.springsecurity.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Integer> {


    List<Post> findByUserId(int userId);


    @Query(value = " SELECT new com.papertrl.springsecurity.entity.Post(p.id,p.userId, u.userName,p.path, p.about, p.postContent,pd.profilePicture,p.postDate, p.postType) " +
            "FROM Post p " +
            "LEFT JOIN User u ON u.id= p.userId " +
            "INNER JOIN ProfileDetail pd ON pd.userId = u.id " +
            "WHERE p.userId=:userId ")
    public List<Post> findPostByUserId(int userId);


    @Query(value = " SELECT new com.papertrl.springsecurity.entity.Post(p.id,p.userId, u.userName,p.path, p.about, p.postContent,pd.profilePicture,p.postDate, p.postType) " +
            "FROM Post p " +
            "LEFT JOIN User u ON u.id= p.userId " +
            "INNER JOIN ProfileDetail pd ON pd.userId = u.id")
    public List<Post> findAllPost();

    @Query(value = " SELECT new com.papertrl.springsecurity.entity.Post(p.id,p.userId, u.userName,p.path, p.about, p.postContent,pd.profilePicture,p.postDate, p.postType) " +
            "FROM Post p " +
            "LEFT JOIN User u ON u.id= p.userId " +
            "INNER JOIN ProfileDetail pd ON pd.userId = u.id " +
            "WHERE p.userId=:userId AND p.postType= 'AUDIO' ")
    public List<Post> findAudioPostByUserId(int userId);

    @Query(value = "SELECT COUNT(p) FROM Post p")
    Integer postCount();
}
