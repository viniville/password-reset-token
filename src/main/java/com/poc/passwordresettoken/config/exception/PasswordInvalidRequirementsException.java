package com.poc.passwordresettoken.config.exception;

public class PasswordInvalidRequirementsException extends RuntimeException {

    public PasswordInvalidRequirementsException() {
        super("Password invalid requirements");
    }

    public PasswordInvalidRequirementsException(String message) {
        super(message);
    }

    public PasswordInvalidRequirementsException(String message, Throwable cause) {
        super(message, cause);
    }
}
