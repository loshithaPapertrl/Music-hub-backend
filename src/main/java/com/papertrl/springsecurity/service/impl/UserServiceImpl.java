package com.papertrl.springsecurity.service.impl;

import com.papertrl.springsecurity.dto.PostDto;
import com.papertrl.springsecurity.dto.UserDto;
import com.papertrl.springsecurity.entity.Post;
import com.papertrl.springsecurity.entity.TalentsCategory;
import com.papertrl.springsecurity.entity.User;
import com.papertrl.springsecurity.repository.PostRepository;
import com.papertrl.springsecurity.repository.TalentCategoryRepository;
import com.papertrl.springsecurity.repository.UserRepository;
import com.papertrl.springsecurity.repository.UserTalentCategoryRepository;
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

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private TalentCategoryRepository talentCategoryRepository;

    private PostRepository postRepository;

    private UserTalentCategoryRepository userTalentCategoryRepository;

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

        userRepository.save(user);
        return new ResponseEntity<>("User registered successfully!", HttpStatus.OK);
    }

    @Override
    public List<TalentsCategory> getTalentCategories() {
        return talentCategoryRepository.findAll();
    }

    @Override
    public ResponseEntity<Object> savePost(PostDto post){
        Post postSave = new Post();
        postSave.setUserId(getCurrentUserId());
        try {
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
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            Integer userId = ((User) authentication.getPrincipal()).getId();
            return userId;
        }
        return null;
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
