package com.papertrl.springsecurity.service;

import com.papertrl.springsecurity.dto.PostDto;
import com.papertrl.springsecurity.dto.UserDto;
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

//    User loadUserByUsername(String userName);
}
