package com.jwt.example.controller;

import com.jwt.example.config.UserDetailsServiceImpl;
import com.jwt.example.model.JwtRequest;
import com.jwt.example.model.JwtResponse;
import com.jwt.example.model.User;
import com.jwt.example.security.JwtHelper;
import com.jwt.example.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private JwtHelper helper;

    private Logger logger = LoggerFactory.getLogger(AuthController.class);



    @PostMapping("/register")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        User user1 = user;
        user1.setPassword(passwordEncoder.encode(user.getPassword()));
        User user2 = userService.saveUser(user1);
        return ResponseEntity.ok(user2);
//        URI location = URI.create("/users/" + user1.getUserId());
////        return ResponseEntity.created(location).body(user1);
//        // or
//        return new ResponseEntity<>(user1, HttpStatus.CREATED);
    }


    @PostMapping("/token")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

        System.out.println("00000000000000000000000000000000000000000000000");
        System.out.println(request.getEmail() + "  ::  " + request.getPassword());
        this.doAuthenticate(request.getEmail(), request.getPassword());


        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = this.helper.generateToken(userDetails);

        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .username(userDetails.getUsername()).build();


        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }


}
