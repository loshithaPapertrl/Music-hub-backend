package com.papertrl.springsecurity.service.impl;

import com.papertrl.springsecurity.dto.PostDto;
import com.papertrl.springsecurity.dto.ProfileDetailDto;
import com.papertrl.springsecurity.dto.ReviewDto;
import com.papertrl.springsecurity.dto.UserDto;
import com.papertrl.springsecurity.entity.*;
import com.papertrl.springsecurity.exception.MusicHubCheckedException;
import com.papertrl.springsecurity.repository.*;
import com.papertrl.springsecurity.service.UserService;
import lombok.extern.slf4j.Slf4j;
import net.codejava.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private TalentCategoryRepository talentCategoryRepository;

    private PostRepository postRepository;

    private UserTalentCategoryRepository userTalentCategoryRepository;

    private ReviewRepository reviewRepository;

    private ProfileDetailRepository profileDetailRepository;



    private CommentRepository commentRepository;

    @Override
    public ResponseEntity<Object> register(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setArtistName(userDto.getArtistName());
        user.setGender(userDto.getGender());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setEmail(userDto.getEmail());
        user.setUserName(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());

        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public List<TalentsCategory> getTalentCategories() {
        return talentCategoryRepository.findAll();
    }

    @Override
    public ResponseEntity<Object> savePost(PostDto post){
        Post postSave = new Post();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Integer userId = userRepository.findArtistIdByEmail(email);

        postSave.setUserId(userId);
        try {
            postSave.setAbout(post.getAbout());
            postSave.setPostContent(post.getPostContent().getBytes());
            postSave.setPostType(post.getPostType());
            postSave.setPostDate(new Date());
            postRepository.save(postSave);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    @Override
//    public User loadUserByUsername(String userName) {
//        User user = userRepository.findUserByUsername(userName);
//        if (user == null) {
//            throw new UsernameNotFoundException("User not found");
//        }
//        return new User(user.getUsername(), user.getPassword());
//    }

    private Integer getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Integer userId = userRepository.findArtistIdByEmail(email);

        return userId;
    }

    @Override
    public UserDetails findUserByEmail(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("No user found with the given email");
        }
        return new CustomUserDetails(user);
    }

    @Override
    public List<Integer> getCategoryViseTalentId(Integer categoryId){
        return userTalentCategoryRepository.findUseridByCategoryId(categoryId);
    }

    @Override
    public ResponseEntity<Object> saveReview(ReviewDto reviewDto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        String artistName = userRepository.findArtistNameByEmail(email);
        if (null==artistName){
            artistName=email;
        }
        Review review = new Review();
        review.setReviewedUserId(reviewDto.getReviewedUserId());
        review.setReviewText(reviewDto.getReviewText());
        review.setReviewerName(artistName);
        reviewRepository.save(review);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Object> getReviewUserVise(Integer userId) {
        List<Review> reviewByUserId = reviewRepository.findReviewByUserId(userId);
        return new ResponseEntity<>(reviewByUserId,HttpStatus.OK);
    }

    @Override
    public List<Post> getPostByUserId(int userId) {
        List<Post> posts = postRepository.findPostByUserId(userId);
        for (Post post:posts) {
            post.setComments(commentRepository.findCommentByPostId(post.getId()));
        }
        return posts;
    }

    @Override
    public ResponseEntity<Object> deletePost(int postId) {
        Optional<Post> postOptional = postRepository.findById(postId);
        if (postOptional.isPresent()) {
            postRepository.delete(postOptional.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Object> saveProfileDetail(ProfileDetailDto profileDetailDto) throws MusicHubCheckedException {

        ProfileDetail profileDetail = new ProfileDetail();
        try {
            profileDetail.setUserId(getCurrentUserId());
            profileDetail.setAbout(profileDetailDto.getAbout());
            profileDetail.setGenres(profileDetailDto.getGenres());
            profileDetail.setProfilePicture( profileDetailDto.getProfilePicture().getBytes());
            profileDetail.setMoods(profileDetailDto.getMoods());
            profileDetail.setProfession(profileDetailDto.getProfession());
            profileDetail.setSpotifyLink(profileDetailDto.getSpotifyLink());
            profileDetail.setYoutubeLink(profileDetailDto.getYoutubeLink());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        profileDetailRepository.save(profileDetail);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @Override
    public ProfileDetail getProfileDetail() {
        Integer currentUserId = getCurrentUserId();
        return profileDetailRepository.findByUserIdWithName(currentUserId);
    }

    @Override
    public ResponseEntity<Object> commentOnAPost(Comment comment) {
       commentRepository.save(comment);
        return  new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getUsersCategoryVise(Integer categoryId) {
        return new ResponseEntity<>(userRepository.getUsersCategoryVise(categoryId),HttpStatus.OK);
    }

    @Autowired
    public void setCommentRepository(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Autowired
    public void setProfileDetailRepository(ProfileDetailRepository profileDetailRepository) {
        this.profileDetailRepository = profileDetailRepository;
    }

    @Autowired
    public void setReviewRepository(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Autowired
    public void setUserTalentCategoryRepository(UserTalentCategoryRepository userTalentCategoryRepository) {
        this.userTalentCategoryRepository = userTalentCategoryRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setTalentCategoryRepository(TalentCategoryRepository talentCategoryRepository) {
        this.talentCategoryRepository = talentCategoryRepository;
    }

    @Autowired
    public void setPostRepository(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
}
