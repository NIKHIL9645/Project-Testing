package com.jwt.example.service;

import com.jwt.example.model.User;
import com.jwt.example.model.UserDto;

public interface UserService {


    User saveUser(User user);

    UserDto getUserById(Long id);

    User updateUser(Long id , User user);

    void deleteUser(Long id);



    User getUser(Long id);







//
//    @Autowired
//    private UserRepository userRepository;
//
//


//
//    private List<User> store = new ArrayList<>();
//
//
//    public UserService() {
//
//        store.add(new User(UUID.randomUUID().toString(),"Durgesh tiwari","dt@gmail.com"));
//        store.add(new User(UUID.randomUUID().toString(),"harsh tiwari","ht@gmail.com"));
//        store.add(new User(UUID.randomUUID().toString(),"gautam tiwari","gt@gmail.com"));
//
//
//    }
//
//    public List<User> getUser(){
//        return this.store;
//    }
}
