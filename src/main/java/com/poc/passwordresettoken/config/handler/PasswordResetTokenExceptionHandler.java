package com.poc.passwordresettoken.config.handler;

import com.poc.passwordresettoken.config.exception.PasswordInvalidRequirementsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class PasswordResetTokenExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request, HttpServletRequest httpSrvRequest) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(PasswordInvalidRequirementsException.class)
    public ResponseEntity<Object> handlePasswordInvalidRequirimentsException(PasswordInvalidRequirementsException ex, WebRequest request, HttpServletRequest httpSrvRequest) {
        return ResponseEntity.badRequest().body(ApiErrorMessage.builder().message(ex.getMessage()).build());
    }

//    @ExceptionHandler({ ConstraintViolationException.class })
//    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
//        List<String> errors = new ArrayList<>();
//        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
//            errors.add(violation.getRootBeanClass().getName() + " " +
//                    violation.getPropertyPath() + ": " + violation.getMessage());
//        }
//        ResponseEntity.ba
//    }


}
