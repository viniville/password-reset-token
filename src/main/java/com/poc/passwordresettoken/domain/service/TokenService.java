package com.poc.passwordresettoken.domain.service;

import com.poc.passwordresettoken.domain.model.PasswordTokenPublicData;
import lombok.SneakyThrows;
import org.springframework.security.core.token.KeyBasedPersistenceTokenService;
import org.springframework.security.core.token.SecureRandomFactoryBean;
import org.springframework.security.core.token.Token;
import org.springframework.security.crypto.codec.Utf8;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.time.Instant;
import java.util.Base64;
import java.util.Optional;

@Component
public class TokenService {

    @SneakyThrows
    public String generateTokenForResetPassword(String email, String userPassword) {
        return getInstanceFor(userPassword).allocateToken(email).getKey();
    }

    public Token verifyToken(String userPassword, String rawToken) {
        final var token = getInstanceFor(userPassword).verifyToken(rawToken);
        verifyTokenExpired(token);
        return token;
    }

    private void verifyTokenExpired(Token token) {
        if(Instant.ofEpochMilli(token.getKeyCreationTime())
                .plus(Duration.ofMinutes(60)).isBefore(Instant.now())) {
            throw new RuntimeException("Token expirado");
        }
    }

    public PasswordTokenPublicData readTokenPublicData(String rawToken) {
        return Optional.ofNullable(rawToken)
                .map(tkn -> Utf8.decode(Base64.getDecoder().decode(Utf8.encode(tkn))))
                .map(tkn -> StringUtils.delimitedListToStringArray(tkn, ":"))
                .map(tknParts -> new PasswordTokenPublicData(tknParts[2], Long.parseLong(tknParts[0])))
                .orElseThrow(IllegalArgumentException::new);
    }

    @SneakyThrows
    private KeyBasedPersistenceTokenService getInstanceFor(String userPassword) {
        final var service = new KeyBasedPersistenceTokenService();
        service.setServerSecret(userPassword);
        service.setServerInteger(16);
        service.setSecureRandom(new SecureRandomFactoryBean().getObject());
        return service;
    }
}
