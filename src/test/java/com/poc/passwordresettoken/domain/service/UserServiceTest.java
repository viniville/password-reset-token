package com.poc.passwordresettoken.domain.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    @Test
    public void should_password() {
        assertTrue("123asd".matches(".*[!#$%&()*+,-./\\\\:;<=>?@\\[\\]^_{|}~]+.*"));
    }

}