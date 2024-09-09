package com.jwt.example.controller;

import com.jwt.example.model.User;
import com.jwt.example.model.UserDto;
import com.jwt.example.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/home")
public class HomeController {


    @Autowired
    private UserServiceImpl userService;


//    @PostMapping
//    public ResponseEntity<User> saveUser(@RequestBody User user){
//        User user1 = userService.saveUser(user);
//
//        return ResponseEntity.ok(user1);
//
//    }



    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserDto userById = userService.getUserById(id);
        return ResponseEntity.ok(userById);
    }



    @GetMapping("/user/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> getUser(@PathVariable Long id){

        User user = userService.getUser(id);
        return  ResponseEntity.ok(user);
    }




//    @GetMapping("/{id}")
//    @PreAuthorize("hasRole('Admin')")
//    public ResponseEntity<User> getUserById(@PathVariable Long id){
//        User userById = userService.getUserById(id);
//        userById.getName();
//        userById.getEmail();
//        userById.getRole();
//        userById.getUsername();
////        User userById = userService.getUserById(id);
//
//
//        return  ResponseEntity.ok(userById);
//    }



//    @GetMapping("/user")
//    public List<User> getUser(){
//
//        System.out.println("getting user");
//
//        return  this.userService.getUser();
//    }



    @GetMapping("/current")
    @PreAuthorize("hasRole('USER')")
    public String getLoggedUser(Principal principal){
        return  principal.getName();
    }

}
