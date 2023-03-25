package com.papertrl.springsecurity.service.impl;

import com.papertrl.springsecurity.dto.TalentCategoryDto;
import com.papertrl.springsecurity.dto.UserDto;
import com.papertrl.springsecurity.entity.TalentsCategory;
import com.papertrl.springsecurity.entity.User;
import com.papertrl.springsecurity.exception.MusicHubCheckedException;
import com.papertrl.springsecurity.repository.TalentCategoryRepository;
import com.papertrl.springsecurity.repository.UserRepository;
import com.papertrl.springsecurity.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TalentCategoryRepository talentCategoryRepository;

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
}
