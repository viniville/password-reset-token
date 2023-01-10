package com.poc.passwordresettoken.domain.service;

import com.poc.passwordresettoken.config.exception.PasswordInvalidRequirementsException;
import com.poc.passwordresettoken.domain.model.User;
import com.poc.passwordresettoken.domain.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserService {

    private static final int PASSWORD_MIN_LENGTH = 8;
    private static final int PASSWORD_MAX_LENGTH = 255;
    private final UserRepository userRepository;

    private final MessageSourceAccessor messageSourceAccessor;

    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }

    public List<User> findAllActives() {
        return userRepository.findByDeactivationDateIsNull();
    }

    @Transactional
    public User insert(User user) {
        Assert.notNull(user, "User cannot be null");
        Assert.isNull(user.getId(), "User ID must be null");
        validatePasswordEffortRequirements(user.getPassword());
        return userRepository.save(user);
    }

    @Transactional
    public User update(User user) {
        Assert.notNull(user, "User cannot be null");
        Assert.notNull(user.getId(), "User ID cannot be null");
        return userRepository.save(user);
    }

    @Transactional
    public void deactivate(UUID id) {
        java.time.OffsetDateTime.now();
        userRepository.findById(id)
                .filter(User::isActive)
                .ifPresentOrElse(userRepository::deactivate, () -> new EntityNotFoundException("User not found"));
    }

    private void validatePasswordEffortRequirements(String password) {
        boolean upChars = false;
        boolean lowChars = false;
        boolean digits = false;
        boolean special = false;
        boolean length = password.length() >= PASSWORD_MIN_LENGTH && password.length() <= PASSWORD_MAX_LENGTH;
        if (length) {
            for (int i = 0; i < password.length(); i++) {
                final var ch = password.charAt(i);
                if (Character.isUpperCase(ch))
                    upChars = true;
                else if (Character.isLowerCase(ch))
                    lowChars = true;
                else if (Character.isDigit(ch))
                    digits = true;
                else
                    special = true;
            }
        }
        if (length && upChars && lowChars && digits && special) {
            return;
        }
        throw new PasswordInvalidRequirementsException(messageSourceAccessor.getMessage("message.exception.password-invalid-requirements"));
    }

}
