package com.jwt.example.service;

import com.jwt.example.model.Project;
import com.jwt.example.model.User;
import com.jwt.example.model.UserDto;
import com.jwt.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private  ProjectService projectService;


    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

//    @Override
//    public User getUserById(Long id) {
//        return null;
//    }

        @Override
    public UserDto getUserById(Long id) {

        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setName(user.getName());
//        userDto.setRole(user.getRole());
        userDto.setEmail(user.getEmail());

        return userDto;
    }

    @Override
    public User updateUser(Long id, User user) {
        User user2 = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        user2.setName(user.getName());
        user2.setEmail(user.getEmail());
        user2.setRole(user.getRole());
        user2.setPassword(user.getPassword());

        return user2;
    }

    @Override
    public void deleteUser(Long id) {
        User user2 = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.deleteById(id);

    }

    @Override
    public User getUser(Long id) {
        Optional<User> user = userRepository.findById(id);


        user.stream().map(use ->  {

            Project project = new Project();

            ResponseEntity<List<Project>> projectsByUserId =
                    projectService.getProjectsByUserId(user.get().getUserId());

            use.setProjects(projectsByUserId.getBody());

        return user;

        } ).collect(Collectors.toList());


        return user.get();
    }
}
