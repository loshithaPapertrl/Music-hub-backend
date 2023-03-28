package com.papertrl.springsecurity.controller;

import com.papertrl.springsecurity.dto.UserDto;
import com.papertrl.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.papertrl.springsecurity.util.CommonConstants.*;

    @CrossOrigin
    @RestController
    public class UserController {

        @Autowired
        private UserService userService;

        @PostMapping(REGISTER)
        public ResponseEntity<Object> registerUser(@RequestBody UserDto userDto) {
            return userService.register(userDto);
        }

        @GetMapping(ALL_TALENTS)
        public ResponseEntity<Object> getTalentCategories(){
            return new ResponseEntity<>(userService.getTalentCategories(), HttpStatus.OK) ;
        }


    }
