package com.example.ECC_Summer_Backend.controller;

import com.example.ECC_Summer_Backend.dto.LoginDto;
import com.example.ECC_Summer_Backend.dto.UserDto;
import com.example.ECC_Summer_Backend.entity.User;
import com.example.ECC_Summer_Backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

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
            if(!userService.isEmailValid(userDto.getUserEmail())){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("유효한 이메일 주소가 아닙니다.");
            }

            // 비밀번호 유효성 검사 -> 비밀번호 길이 제한
            if(!userService.isPasswordValid(userDto.getUserPw())){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("비밀번호가 너무 깁니다.");
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
    public ResponseEntity<String> loginUser(@RequestBody LoginDto loginDto){
        System.out.println("Login API called with LoginDto:" + loginDto);

        try{
            if(userService.loginUser(loginDto)){
                return ResponseEntity.ok("로그인 성공");
            }else{
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("회원정보가 일치하지 않습니다.");
            }
        }catch (Exception e){
            System.out.println("Error occurred during user login: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류가 발생했습니다.");
        }
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        System.out.println("Exception handled: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류가 발생했습니다.");
    }
}
