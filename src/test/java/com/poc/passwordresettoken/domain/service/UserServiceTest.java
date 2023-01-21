package com.poc.passwordresettoken.domain.service;

import com.poc.passwordresettoken.domain.model.User;
import com.poc.passwordresettoken.domain.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    void findById_should_return() {
        when(userRepository.findById(any())).thenReturn(Optional.of(User.builder()
                .email("email@email.com")
                .fullName("nome de teste")
                .birthDate(LocalDate.of(1990, 1, 1))
                .password("asdfASD&123")
                .build()));

        Optional<User> byId = userService.findById(UUID.randomUUID());
        assertFalse(byId.isEmpty());
    }

    @Test
    void findByEmail_should_return() {
        when(userRepository.findByEmail(any())).thenReturn(Optional.of(User.builder()
                .email("email@email.com")
                .fullName("nome de teste")
                .birthDate(LocalDate.of(1990, 1, 1))
                .password("asdfASD&123")
                .build()));

        Optional<User> byId = userService.findByEmail("email@email.com");
        assertFalse(byId.isEmpty());
    }

    @Test
    void findAllActives() throws InterruptedException {
//        Instant start = Instant.now();
//        Thread.sleep(10000);
//        assertTrue( start
//                    .plus(Duration.ofSeconds(30000)).isBefore(Instant.now()));
    }

    @Test
    void insert_should_insert_success() {
        final var userReturn = User.builder()
                .id(UUID.randomUUID())
                .email("email@email.com")
                .fullName("nome de teste")
                .birthDate(LocalDate.of(1990, 1, 1))
                .password("asdfASD&123")
                .build();

        final var userSend = new User();
        BeanUtils.copyProperties(userReturn, userSend, "id");
        when(userRepository.save(any())).thenReturn(userReturn);

        final var result = userService.insert(userSend);

        assertNotNull(result);
        assertNotNull(result.getId());
    }

    @Test
    void update() {
    }

    @Test
    void deactivate() {
    }
}