package com.papertrl.springsecurity.service;

import com.papertrl.springsecurity.dto.PostDto;
import com.papertrl.springsecurity.dto.UserDto;
import com.papertrl.springsecurity.entity.Post;
import com.papertrl.springsecurity.entity.TalentsCategory;
import com.papertrl.springsecurity.entity.User;
import com.papertrl.springsecurity.exception.MusicHubCheckedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {

    public ResponseEntity<Object> register(UserDto userDto);

    public List<TalentsCategory> getTalentCategories();

    public ResponseEntity<Object> savePost(PostDto post)throws MusicHubCheckedException;

//    User loadUserByUsername(String userName);
}
