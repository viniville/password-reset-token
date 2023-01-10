package com.poc.passwordresettoken.api.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDto {
    @NotBlank
    @Size(min = 10, max = 255)
    private String email;

    @NotBlank
    @Size(min = 10, max = 255)
    private String fullName;

    @Past
    private LocalDate birthDate;
}
