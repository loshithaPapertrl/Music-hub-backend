package com.papertrl.springsecurity.service.impl;

import com.papertrl.springsecurity.dto.*;
import com.papertrl.springsecurity.entity.*;
import com.papertrl.springsecurity.exception.MusicHubCheckedException;
import com.papertrl.springsecurity.repository.*;
import com.papertrl.springsecurity.service.UserService;
import lombok.extern.slf4j.Slf4j;
import net.codejava.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.time.LocalDate;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Value("${upload.directory}")
    private String uploadDirectory;

    private UserRepository userRepository;

    private TalentCategoryRepository talentCategoryRepository;

    private PostRepository postRepository;

    private UserTalentCategoryRepository userTalentCategoryRepository;

    private ReviewRepository reviewRepository;

    private ProfileDetailRepository profileDetailRepository;

    private AdminNoteRepository adminNoteRepository;



    private CommentRepository commentRepository;

    /**
     * this method use for ragistration
     * @param userDto
     * @return
     */
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
        user.setIsActive(true);

        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * this method use for get talent categories
     * @return
     */
    @Override
    public List<TalentsCategory> getTalentCategories() {
        return talentCategoryRepository.findAll();
    }

    /**
     * this method use for save a post
     * @param post
     * @return
     */
    @Override
    public ResponseEntity<Object> savePost(PostDto post){
        Post postSave = new Post();
        if (post.getPostType().equals("AUDIO")){
            String path = saveLocally(post.getPostContent());
            postSave.setPath("assets\\mp3\\"+path);
        }
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

    /**
     * this method use for save audio locally
     * @param file
     * @return
     */

    @Override
    public String saveLocally(MultipartFile file) {
        try {
            // Get the file and save it
            byte[] bytes = file.getBytes();
            Path path = Paths.get(uploadDirectory+ LocalDate.now() + file.getOriginalFilename());
            Files.write(path, bytes);
            return LocalDate.now() + file.getOriginalFilename();
        } catch (IOException e) {
            return "Failed to upload file";
        }
    }

    /**
     * this method use for see rating as a no logged user
     * @param userId
     * @return
     */

    @Override
    public Double getRatingByVisitor(Integer userId) {
        Double sumOfAllMarks = Double.valueOf(reviewRepository.getSumOfAllMarks(userId));
        Double reviewCount = Double.valueOf(reviewRepository.getReviewCount(userId));
        Double rating = ((sumOfAllMarks / reviewCount) / 5) * 10;
        return rating;
    }

    /**
     * this method use for get rating as a user
     * @return
     */
    @Override
    public Double getRating() {
        Integer userId = getCurrentUserId();
        Double sumOfAllMarks = Double.valueOf(reviewRepository.getSumOfAllMarks(userId));
        Double reviewCount = Double.valueOf(reviewRepository.getReviewCount(userId));
        Double rating = ((sumOfAllMarks / reviewCount) / 5) * 10;
        return rating;
    }

    /**
     * this method use for add notes for admin role
     * @param note
     * @return
     */

    @Override
    public ResponseEntity<Object> addNote(AdminNote note) {
        AdminNote adminNote = new AdminNote();
        adminNote.setDate(new Date());
        adminNote.setNote(note.getNote());
        return new ResponseEntity<>(adminNoteRepository.save(adminNote),HttpStatus.OK);
    }

    /**
     * this method use for get all notes
     * @return
     */
    @Override
    public List<AdminNote> getAllNotes() {
        return adminNoteRepository.findAll();
    }

    @Override
    public ResponseEntity<Object> deleteNote(Integer noteId) {
        adminNoteRepository.deleteById(noteId);
        return ResponseEntity.ok().build();
    }


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
    public ActiveUserDto getRole(String email){
        ActiveUserDto activeUserDto = new ActiveUserDto();
        User user = userRepository.findByEmail(email);
        activeUserDto.setRole(user.getRole());
        activeUserDto.setIsActive(user.getIsActive());

        return activeUserDto;
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
        review.setMarks(reviewDto.getMarks());
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
    public ResponseEntity<Object> getPersonalReview(){
        Integer userId = getCurrentUserId();
        List<Review> reviewByUserId = reviewRepository.findReviewByUserId(userId);
        return new ResponseEntity<>(reviewByUserId,HttpStatus.OK);
    }

    /**
     * this method use for get post by user id
     * @param userId
     * @return
     */
    @Override
    public List<Post> getPostByUserId(int userId) {
        List<Post> posts = postRepository.findPostByUserId(userId);
        for (Post post:posts) {
            post.setComments(commentRepository.findCommentByPostId(post.getId()));
        }
        return posts;
    }

    @Override
    public List<Post> getPersonalPost() {
        Integer userId = getCurrentUserId();
        List<Post> posts = postRepository.findPostByUserId(userId);
        for (Post post:posts) {
            post.setComments(commentRepository.findCommentByPostId(post.getId()));
        }
        return posts;
    }

    @Override
    public List<Post> getAudio() {
        Integer userId = getCurrentUserId();
        List<Post> posts = postRepository.findAudioPostByUserId(userId);
        for (Post post:posts) {
            post.setPostContentAsString(Base64.getEncoder().encodeToString(post.getPostContent()));
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
            profileDetail.setTalentCategory(profileDetailDto.getTalentCategory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        profileDetailRepository.save(profileDetail);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * this method use for save profile details
     * @param profileDetailDto
     * @return
     */
    @Override
    public ResponseEntity<Object> updateProfileDetail(ProfileDetailDto profileDetailDto) {

        Integer userId = getCurrentUserId();
        ProfileDetail existingProfileDetail = profileDetailRepository.findByUserId(userId);
        try {
            existingProfileDetail.setAbout(null==profileDetailDto.getAbout()?existingProfileDetail.getAbout():profileDetailDto.getAbout());
            existingProfileDetail.setGenres(null==profileDetailDto.getGenres()? existingProfileDetail.getGenres() : profileDetailDto.getGenres());
            existingProfileDetail.setMoods(null==profileDetailDto.getMoods()?existingProfileDetail.getMoods():profileDetailDto.getMoods());
            existingProfileDetail.setProfession(null==profileDetailDto.getProfession()?existingProfileDetail.getProfession():profileDetailDto.getProfession());
            existingProfileDetail.setSpotifyLink(null==profileDetailDto.getSpotifyLink()?existingProfileDetail.getSpotifyLink():profileDetailDto.getSpotifyLink());
            existingProfileDetail.setYoutubeLink(null==profileDetailDto.getYoutubeLink()?existingProfileDetail.getYoutubeLink():profileDetailDto.getYoutubeLink());
            existingProfileDetail.setTalentCategory(null==profileDetailDto.getTalentCategory()?existingProfileDetail.getTalentCategory():profileDetailDto.getTalentCategory());
            if (profileDetailDto.getProfilePicture() != null) {
                existingProfileDetail.setProfilePicture(profileDetailDto.getProfilePicture().getBytes());
            }
            profileDetailRepository.save(existingProfileDetail);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @Override
    public ProfileDetail getProfileDetail() {
        Integer currentUserId = getCurrentUserId();
        return profileDetailRepository.findByUserIdWithName(currentUserId);
    }

    @Override
    public ProfileDetail getProfileDetailForVisitor(Integer userId) {
        return profileDetailRepository.findByUserIdWithName(userId);
    }



    /**
     * this method use for
     * @param comment
     * @return
     */
    @Override
    public ResponseEntity<Object> commentOnAPost(Comment comment) {
        Comment com = new Comment();
        Integer currentUserId = getCurrentUserId();
        String artistName = userRepository.findArtistNameById(currentUserId);
        com.setPostId(comment.getPostId());
        com.setComment(comment.getComment());
        com.setCommentedBy(artistName);
        commentRepository.save(com);
        return  new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * this method use for get users category vise
     * @param categoryId
     * @return
     */

    @Override
    public ResponseEntity<Object> getUsersCategoryVise(Integer categoryId) {
        return new ResponseEntity<>(userRepository.getUsersCategoryVise(categoryId),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getAllUsersToAdminTable(){
        return new ResponseEntity<>(userRepository.getUsersToAdminTable(),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> activeAndInactiveUser(User user){
        Integer id = user.getId();
        User existingUser = userRepository.getUserById(id);
        existingUser.setIsActive(user.getIsActive());
        userRepository.save(existingUser);
        return  new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public List<Post> getAllPost() {
        List<Post> posts = postRepository.findAllPost();
        for (Post post:posts) {
            post.setComments(commentRepository.findCommentByPostId(post.getId()));
        }
        return posts;
    }

    @Override
    public CountDto allCounts(){
        CountDto countDto = new CountDto();
        countDto.setCommentCount(commentRepository.commentCount());
        countDto.setPostCount( postRepository.postCount());
        countDto.setUserCount(userRepository.userCount());
        return countDto;
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

    @Autowired
    public void setAdminNoteRepository(AdminNoteRepository adminNoteRepository) {
        this.adminNoteRepository = adminNoteRepository;
    }
}
