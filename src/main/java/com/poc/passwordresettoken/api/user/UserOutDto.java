package com.poc.passwordresettoken.api.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserOutDto {
    private UUID id;
    private String email;
    private String fullName;
    private LocalDate birthDate;
}
