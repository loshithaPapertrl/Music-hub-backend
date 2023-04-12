package com.papertrl.springsecurity.service;

import com.papertrl.springsecurity.dto.PostDto;
import com.papertrl.springsecurity.dto.ProfileDetailDto;
import com.papertrl.springsecurity.dto.ReviewDto;
import com.papertrl.springsecurity.dto.UserDto;
import com.papertrl.springsecurity.entity.Comment;
import com.papertrl.springsecurity.entity.Post;
import com.papertrl.springsecurity.entity.ProfileDetail;
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

    public ResponseEntity<Object> saveProfileDetail(ProfileDetailDto profileDetailDto) throws MusicHubCheckedException;

    public ProfileDetail getProfileDetail();

    public ProfileDetail getProfileDetailForVisitor(Integer userId);

    ResponseEntity<Object> commentOnAPost(Comment comment);

    ResponseEntity<Object> getUsersCategoryVise(Integer categoryId);

    ResponseEntity<Object> getPersonalReview();

//    User loadUserByUsername(String userName);
}
