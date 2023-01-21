package com.poc.passwordresettoken.api.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ForgotPasswordDto {

    @NotBlank
    @Size(min = 10, max = 255)
    private String email;
}
