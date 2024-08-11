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
    private String password; // 비밀번호

    @Column(name="user_name")
    private String userName; // 이름

    @Column(name="user_email")
    private String email; // 이메일

    @Column(name="user_role")
    private String userRole; // 권한 : USER, ADMIN
}
