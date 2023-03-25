package com.papertrl.springsecurity.service;

import com.papertrl.springsecurity.dto.UserDto;
import com.papertrl.springsecurity.entity.TalentsCategory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    public ResponseEntity<Object> register(UserDto userDto);

    public List<TalentsCategory> getTalentCategories();

}
