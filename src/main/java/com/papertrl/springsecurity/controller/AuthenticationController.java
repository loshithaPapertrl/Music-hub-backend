package com.papertrl.springsecurity.controller;

import com.papertrl.springsecurity.config.JwtUtil;
import com.papertrl.springsecurity.dao.UserDao;
import com.papertrl.springsecurity.dto.*;
import com.papertrl.springsecurity.entity.*;
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
    public ResponseEntity<Object> authenticate(@RequestBody AuthenticationRequest request){

        TokenDto tokenDto = new TokenDto();
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        final UserDetails user = userService.findUserByEmail(request.getEmail());
        ActiveUserDto activeUser = userService.getRole(request.getEmail());
        if (user != null){
            tokenDto.setToken(jwtUtil.generateToken(user));
            tokenDto.setRole(activeUser.getRole());
            tokenDto.setIsActive(activeUser.getIsActive());
            return ResponseEntity.ok(tokenDto);
        }
        return ResponseEntity.status(400).body("some error");
    }

    @PostMapping(SAVE_TALENTS)
    public ResponseEntity<Object> savePost(@ModelAttribute PostDto post)throws MusicHubCheckedException {
        return new ResponseEntity<>(userService.savePost(post), HttpStatus.OK) ;
    }
    @PostMapping(POST_REVIEW)
    public ResponseEntity<Object> postReview(@RequestBody ReviewDto review)throws MusicHubCheckedException {
        return new ResponseEntity<>(userService.saveReview(review), HttpStatus.OK) ;
    }

    @GetMapping(GET_POST_BY_USER_ID)
    public ResponseEntity<Object> getPostById(@RequestParam int userId) {
        return new ResponseEntity<>(userService.getPostByUserId(userId),HttpStatus.OK);
    }

    @GetMapping(GET_PERSONAL_POST)
    public ResponseEntity<Object> getPersonalPost() {
        return new ResponseEntity<>(userService.getPersonalPost(),HttpStatus.OK);
    }

    @DeleteMapping(DELETE_POST)
    public ResponseEntity<Object> deletePostById(@RequestParam int postId) {
        return new ResponseEntity<>(userService.deletePost(postId),HttpStatus.OK);
    }

    @PostMapping(ADD_PROFILE_DETAILS)
    public ResponseEntity<Object> saveProfileDetail(@ModelAttribute ProfileDetailDto profileDetail) throws MusicHubCheckedException {
        return new  ResponseEntity<>(userService.saveProfileDetail(profileDetail),HttpStatus.OK);
    }

    @PutMapping(UPDATE_PROFILE_DETAILS)
    public ResponseEntity<Object> updateProfileDetail(@ModelAttribute ProfileDetailDto profileDetail) throws MusicHubCheckedException {
        return new  ResponseEntity<>(userService.updateProfileDetail(profileDetail),HttpStatus.OK);
    }

    @GetMapping(GET_PROFILE_DETAILS)
    public ResponseEntity<Object> getProfileDetailByOwner() throws MusicHubCheckedException {
        return new  ResponseEntity<>(userService.getProfileDetail(),HttpStatus.OK);
    }

    @GetMapping(GET_PROFILE_DETAILS_BY_VISITOR)
    public ResponseEntity<Object> getProfileDetailForVisitor(@RequestParam Integer userId) throws MusicHubCheckedException {
        return new  ResponseEntity<>(userService.getProfileDetailForVisitor(userId),HttpStatus.OK);
    }

    @PostMapping(COMMENT_ON_A_POST)
    public ResponseEntity<Object> commentOnAPost(@RequestBody Comment comment) throws MusicHubCheckedException {
        return new  ResponseEntity<>(userService.commentOnAPost(comment),HttpStatus.OK);
    }

    @GetMapping(GET_AUDIOS_AS_POST)
    public ResponseEntity<Object> getAudio() {
        return new ResponseEntity(userService.getAudio(),HttpStatus.OK);
    }

    @GetMapping(GET_RATINGS_OWN)
    public ResponseEntity<Object> getRatingByVisitor(){
        return new ResponseEntity<>(userService.getRating() ,HttpStatus.OK) ;
    }

    @GetMapping(GET_ALL_USERS)
    public ResponseEntity<Object> getAllUsers(){
        return userService.getAllUsersToAdminTable();
    }

    @PostMapping(ADD_NOTE)

    public ResponseEntity<Object>addNote(@RequestBody AdminNote note){
        return new ResponseEntity<>(userService.addNote(note),HttpStatus.OK) ;
    }

    @GetMapping(GET_ALL_NOTES)
    public ResponseEntity<Object>getAllNote(){
        return new ResponseEntity<>(userService.getAllNotes(),HttpStatus.OK) ;
    }


    @DeleteMapping(DELETE_NOTE)
    public ResponseEntity<Object> deleteNote(@RequestParam Integer noteId){
        return new ResponseEntity<>(userService.deleteNote(noteId),HttpStatus.OK);
    }

    @PutMapping(UPDATE_USER_STATUS)
    public ResponseEntity<Object> updateUserStatus(@RequestBody User user){
        return new ResponseEntity<>(userService.activeAndInactiveUser(user), HttpStatus.OK);
    }

//    @PostMapping(POST_LOCAL)
//    public ResponseEntity<Object> uploadMp3(@ModelAttribute("file") MultipartFile file) {
//        return new  ResponseEntity<>(userService.saveLocally(file),HttpStatus.OK);
//
//    }

}
