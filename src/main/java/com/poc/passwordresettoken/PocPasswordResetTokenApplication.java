package com.poc.passwordresettoken;

import com.poc.passwordresettoken.domain.model.User;
import com.poc.passwordresettoken.domain.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import java.time.LocalDate;

@SpringBootApplication
public class PocPasswordResetTokenApplication {


    @Value("${createAdminUser:false}")
    private boolean createAdminUser;
    @Value("${passwordForAdmin:asdfASDF$123}")
    private String passwordForAdmin;

    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(PocPasswordResetTokenApplication.class, args);
    }

    @PostConstruct
    @ConditionalOnProperty("${createAdminUser:false}")
    public void init() {
        if (createAdminUser)
            userService.insert(User.builder()
                    .email("admin@email.com")
                    .password(passwordForAdmin)
                    .birthDate(LocalDate.of(1988, 2, 7))
                    .fullName("Administrator of system")
                    .build());
    }

}
