package com.papertrl.springsecurity.controller;

import com.papertrl.springsecurity.config.JwtUtil;
import com.papertrl.springsecurity.dao.UserDao;
import com.papertrl.springsecurity.dto.AuthenticationRequest;
import com.papertrl.springsecurity.dto.PostDto;
import com.papertrl.springsecurity.entity.Post;
import com.papertrl.springsecurity.entity.User;
import com.papertrl.springsecurity.exception.MusicHubCheckedException;
import com.papertrl.springsecurity.repository.UserRepository;
import com.papertrl.springsecurity.service.UserService;
import com.papertrl.springsecurity.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.papertrl.springsecurity.util.CommonConstants.*;

@CrossOrigin
@RestController
@RequestMapping
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UserDao userDao;
    private final JwtUtil jwtUtil;
    @Autowired
    private UserService userService;

    @PostMapping(AUTHENTICATE)
    public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest request){

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        final UserDetails user = userService.findUserByEmail(request.getEmail());
        if (user != null){
            return ResponseEntity.ok(jwtUtil.generateToken(user));
        }
        return ResponseEntity.status(400).body("some error");
    }

    @PostMapping(SAVE_TALENTS)
    public ResponseEntity<Object> savePost(@ModelAttribute PostDto post)throws MusicHubCheckedException {
        return new ResponseEntity<>(userService.savePost(post), HttpStatus.OK) ;
    }


}
