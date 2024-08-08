package com.example.ECC_Summer_Backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name="users")
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer userCode;

    @Column(name="user_id")
    private String userId; // 아이디

    @Column(name="user_pw")
    private String userPw; // 비밀번호

    @Column(name="user_name")
    private String userName; // 이름

    @Column(name="user_email")
    private String userEmail; // 이메일
}
