package com.papertrl.springsecurity.service;

import com.papertrl.springsecurity.dto.PostDto;
import com.papertrl.springsecurity.dto.ReviewDto;
import com.papertrl.springsecurity.dto.UserDto;
import com.papertrl.springsecurity.entity.Post;
import com.papertrl.springsecurity.entity.ProfileDetail;
import com.papertrl.springsecurity.entity.Review;
import com.papertrl.springsecurity.entity.TalentsCategory;
import com.papertrl.springsecurity.exception.MusicHubCheckedException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService {

    public ResponseEntity<Object> register(UserDto userDto);

    public List<TalentsCategory> getTalentCategories();

    public ResponseEntity<Object> savePost(PostDto post)throws MusicHubCheckedException;

    UserDetails findUserByEmail(String email) throws UsernameNotFoundException;

    List<Integer> getCategoryViseTalentId(Integer categoryId);

    public ResponseEntity<Object> saveReview(ReviewDto review);

    public ResponseEntity<Object> getReviewUserVise(Integer userId);

    public List<Post> getPostByUserId(int postId);

    public ResponseEntity<Object> deletePost(int postId);

    ProfileDetail saveProfileDetail(ProfileDetail profileDetail);

//    User loadUserByUsername(String userName);
}
