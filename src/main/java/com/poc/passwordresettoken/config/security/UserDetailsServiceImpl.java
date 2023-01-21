package com.poc.passwordresettoken.config.security;

import com.poc.passwordresettoken.config.exception.UserNotFoundException;
import com.poc.passwordresettoken.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userService.findByEmail(username)
                .orElseThrow(UserNotFoundException::new);
    }

}
