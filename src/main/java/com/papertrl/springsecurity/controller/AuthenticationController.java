package com.papertrl.springsecurity.controller;

import com.papertrl.springsecurity.service.config.JwtUtil;
import com.papertrl.springsecurity.dao.UserDao;
import com.papertrl.springsecurity.dto.AuthenticationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UserDao userDao;
    private final JwtUtil jwtUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest request){

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        final UserDetails user = userDao.findUserByEmail(request.getEmail());
        if (user != null){
            return ResponseEntity.ok(jwtUtil.generateToken(user));
        }
        return ResponseEntity.status(400).body("some error");
    }
}
