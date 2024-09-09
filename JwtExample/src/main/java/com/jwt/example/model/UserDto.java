package com.jwt.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class UserDto {


    private Long userId;


    private String name;

//    private String password;

    private String email;

//    private String role;


}
