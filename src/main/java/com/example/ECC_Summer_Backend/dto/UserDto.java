package com.example.ECC_Summer_Backend.dto;

import lombok.Data;

@Data
public class UserDto {
    private String userId;
    private String password;
    private String passwordConfirm;
    private String userName;
    private String email;
}
