package com.poc.passwordresettoken.api.user;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResetPasswordDto {
    @NotBlank
    private String newPassword;

    @NotBlank
    private String resetToken;
}
