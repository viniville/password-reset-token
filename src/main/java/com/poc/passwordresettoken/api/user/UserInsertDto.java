package com.poc.passwordresettoken.api.user;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class UserInsertDto {

    @NotBlank
    @Size(min = 10, max = 255)
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    @Size(min = 10, max = 255)
    private String fullName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Past
    private LocalDate birthDate;

}
