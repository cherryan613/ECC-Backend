package com.example.ECC_Summer_Backend.config;

import com.example.ECC_Summer_Backend.entity.User;
import com.example.ECC_Summer_Backend.reopository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class AdminConfig {

    @Bean
    CommandLineRunner createAdmin(UserRepository userRepository){
        return args-> {
            if(!userRepository.existsByUserId("admin")){
                User admin= new User();
                admin.setUserId("admin");
                admin.setUserPw(new BCryptPasswordEncoder().encode("adminpassword"));
                admin.setUserName("Admin");
                admin.setUserEmail("admin@example.com");
                admin.setUserRole("ADMIN");
                userRepository.save(admin);
            }
        };
    }
}
