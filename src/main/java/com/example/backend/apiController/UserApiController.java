package com.example.backend.apiController;

import com.example.backend.dto.UserDto;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    @GetMapping("/api/users/{user_code}")
    public ResponseEntity<List<UserDto>> getUserByUserCode(@PathVariable Long user_code) {
        List<UserDto> dtos = userService.userByUserCode(user_code);
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }
}
