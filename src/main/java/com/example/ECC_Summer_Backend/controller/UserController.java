package com.example.ECC_Summer_Backend.controller;

import com.example.ECC_Summer_Backend.dto.LoginDto;
import com.example.ECC_Summer_Backend.dto.UserDto;
import com.example.ECC_Summer_Backend.entity.User;
import com.example.ECC_Summer_Backend.jwt.JwtUtil;
import com.example.ECC_Summer_Backend.repository.UserRepository;
import com.example.ECC_Summer_Backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDto userDto){
        System.out.println("RegisterUser API called with UserDto: " + userDto);
        try{
            // 사용자 ID 중복 검사
            if(userService.isUserIdExists(userDto.getUserId())){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("이미 존재하는 아이디입니다.");
            }

            // 사용자 ID 유효성 검사 -> 한글 입력 여부
            if (!userService.isUserIdValid(userDto.getUserId())){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("사용할 수 없는 아이디입니다.");
            }

            // 이메일 유효성 검사 -> 올바른 이메일 형식인지 확인
            if(!userService.isEmailValid(userDto.getEmail())){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("유효한 이메일 주소가 아닙니다.");
            }

            // 비밀번호 유효성 검사 -> 비밀번호 길이 제한
            if(!userService.isPasswordValid(userDto.getPassword())){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("비밀번호가 너무 깁니다.");
            }

            // 비밀번호와 비밀번호 확인이 일치하는지 확인
            if(!userDto.getPassword().equals(userDto.getPasswordConfirm())){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("비밀번호가 일치하지 않습니다.");
            }
            // 유효성 검사를 통과하면 사용자 등록을 시도한다.
            User registeredUser = userService.registerUser(userDto);

            // 사용자 등록이 성공한 경우
            if(registeredUser != null){
                return ResponseEntity.ok("정상적으로 회원가입 되었습니다.");
            } else {
                // 사용자 등록이 실패한 경우
                return ResponseEntity.status(HttpStatus.CONFLICT).body("만료된 키입니다.");
            }
        } catch(Exception e){
            // 예외가 발생한 경우
            System.out.println("Error occurred during user registration: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류가 발생했습니다.");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody LoginDto loginDto){
        System.out.println("Login API called with LoginDto:" + loginDto);

        try{
            Optional<User> userOptional = userRepository.findByUserId(loginDto.getUserId());
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                if (bCryptPasswordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
                    String token = jwtUtil.generateToken(user.getUserId(), user.getUserRole());

                    Map<String, String> response = new HashMap<>();
                    response.put("token", token);
                    response.put("role", user.getUserRole());

                    return ResponseEntity.ok(response);
                } else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid credentials"));
                }
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "User not found"));
            }
        }catch (Exception e){
            System.out.println("Error occurred during user login: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Server error"));
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        System.out.println("Exception handled: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류가 발생했습니다.");
    }
}