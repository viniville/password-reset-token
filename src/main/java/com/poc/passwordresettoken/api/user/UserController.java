package com.poc.passwordresettoken.api.user;

import com.poc.passwordresettoken.domain.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @GetMapping
    public List<UserOutDto> findAllActives() {
        return userService.findAllActives()
                .stream().map(userMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public UserOutDto findById(@PathVariable String id) {
        return userService.findById(UUID.fromString(id))
                .map(userMapper::toDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public UserOutDto insert(@Valid @RequestBody UserInsertDto userInsert) {
        return userMapper.toDto(userService.insert(userMapper.toEntity(userInsert)));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public UserOutDto update(@PathVariable String id, @Valid @RequestBody UserUpdateDto userUpdateDto) {
        return userService.findById(UUID.fromString(id))
                .map(user -> userMapper.mergeToUpdate(user, userUpdateDto))
                .map(user -> userMapper.toDto(userService.update(user)))
                .orElseThrow(EntityNotFoundException::new);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deactivate(@PathVariable String id) {
        userService.findById(UUID.fromString(id))
                .ifPresentOrElse(user -> userService.deactivate(user.getId()), () -> {
                    throw new EntityNotFoundException();
                });
    }

    @PostMapping("/forgotPassword")
    @ResponseStatus(HttpStatus.OK)
    public void forgotPassword(@RequestBody ForgotPasswordDto forgotPassword) {
        userService.forgotPassword(forgotPassword.getEmail());
    }

    @PostMapping("/resetPassword")
    public void resetPassword(@RequestBody ResetPasswordDto resetPasswordDto) {
        userService.resetPassword(resetPasswordDto.getNewPassword(), resetPasswordDto.getResetToken());
    }

}
