package com.example.backend.service;

import com.example.backend.dto.UserDto;
import com.example.backend.entity.Scrap;
import com.example.backend.entity.User;
import com.example.backend.repository.ScrapRepository;
import com.example.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;
    private ScrapRepository scrapRepository;

    public List<UserDto> userByUserCode(Long user_code) {
        List<User> users = userRepository.UserByUserCode(user_code);
        return users.stream()
                .map(UserDto::createUserDto)
                .collect(Collectors.toList());
    }

    public List<Scrap> getScraps(){
        return scrapRepository.findAll();
    }

}
