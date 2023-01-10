package com.poc.passwordresettoken.config.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiErrorMessage {
    private String message;
    @Builder.Default
    private OffsetDateTime timestamp = OffsetDateTime.now();
    private List<String> errors;

}
