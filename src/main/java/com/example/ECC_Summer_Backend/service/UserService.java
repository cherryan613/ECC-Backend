package com.example.ECC_Summer_Backend.service;

import com.example.ECC_Summer_Backend.dto.LoginDto;
import com.example.ECC_Summer_Backend.dto.UserDto;
import com.example.ECC_Summer_Backend.entity.User;
import com.example.ECC_Summer_Backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    // 아이디 유효성 검사
    public boolean isUserIdValid(String userId){
        return StringUtils.hasText(userId) && Pattern.matches("^[a-zA-Z0-9]*$", userId);
    }

    // 이메일 유효성 검사
    public boolean isEmailValid(String email){
        return StringUtils.hasText(email) && Pattern.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", email);
    }

    // 비밀번호 길이 검사
    public boolean isPasswordValid(String password){
        return password != null && password.length() >= 8 && password.length()<=20;
    }

    // 아이디 중복 확인
    public boolean isUserIdExists(String userId){
        System.out.println("Checking if user ID exists: " + userId);
        boolean exists = userRepository.existsByUserId(userId);
        System.out.println("User ID exists: " + exists);
        return exists;
    }

    // 사용자 등록
    public User registerUser(UserDto userDto){
        System.out.println("Registering user: " + userDto.getUserId());
        // 비밀번호와 비밀번호 확인이 일치하는지 확인
        if(!userDto.getPassword().equals(userDto.getPasswordConfirm())){
            throw new IllegalArgumentException("Passwords do not match.");
        }
        User newUser = new User();
        newUser.setUserId(userDto.getUserId());
        newUser.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        newUser.setUserName(userDto.getUserName());
        newUser.setEmail(userDto.getEmail());
        newUser.setUserRole("USER"); // 기본적으로 USER 권한 부여
        // 데이터베이스에 사용자 정보 저장
        User savedUser = userRepository.save(newUser);
        System.out.println("User registered: " + savedUser.getUserId());
        return savedUser;
    }

    /* 로그인 메서드 */
    public boolean loginUser(LoginDto loginDto){
        Optional<User> userOptional=userRepository.findByUserId(loginDto.getUserId());
        if(userOptional.isPresent()){
            User user = userOptional.get();
            return bCryptPasswordEncoder.matches(loginDto.getPassword(), user.getPassword());
        }else{
            return false;
        }
    }
}