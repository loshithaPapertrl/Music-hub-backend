package com.papertrl.springsecurity.service;

import com.papertrl.springsecurity.dto.*;
import com.papertrl.springsecurity.entity.*;
import com.papertrl.springsecurity.exception.MusicHubCheckedException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {

    public ResponseEntity<Object> register(UserDto userDto);

    public List<TalentsCategory> getTalentCategories();

    public ResponseEntity<Object> savePost(PostDto post)throws MusicHubCheckedException;

    UserDetails findUserByEmail(String email) throws UsernameNotFoundException;

    public ActiveUserDto getRole(String email);

    public ResponseEntity<Object> getAllUsersToAdminTable();

    public ResponseEntity<Object>  activeAndInactiveUser(User user);

    List<Integer> getCategoryViseTalentId(Integer categoryId);

    public ResponseEntity<Object> saveReview(ReviewDto review);

    public ResponseEntity<Object> getReviewUserVise(Integer userId);

    public List<Post> getPostByUserId(int postId);

    public ResponseEntity<Object> deletePost(int postId);

    public ResponseEntity<Object> saveProfileDetail(ProfileDetailDto profileDetailDto) throws MusicHubCheckedException;

    public ResponseEntity<Object> updateProfileDetail(ProfileDetailDto profileDetail);

    public ProfileDetail getProfileDetail();

    public ProfileDetail getProfileDetailForVisitor(Integer userId);

    ResponseEntity<Object> commentOnAPost(Comment comment);

    ResponseEntity<Object> getUsersCategoryVise(Integer categoryId);

    ResponseEntity<Object> getPersonalReview();

    public List<Post> getPersonalPost();

    List<Post> getAudio();

    String saveLocally(MultipartFile file);

    Double getRatingByVisitor(Integer userId);

    Double  getRating();

    ResponseEntity<Object> addNote(AdminNote note);

    List<AdminNote> getAllNotes();

    ResponseEntity<Object> deleteNote(Integer noteId);

//    User loadUserByUsername(String userName);
}
