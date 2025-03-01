package com.project.user.service;

import com.project.user.domain.SignUpForm;
import com.project.user.domain.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SignUpCustomerServiceTest {

    @Autowired
    private SignUpCustomerService service;

    @Test
    void signUp() {
        //given
        SignUpForm form = SignUpForm.builder()
                .name("test")
                .email("test@gmail.com")
                .password("1")
                .birth(LocalDate.now())
                .phone("01011112222")
                .build();
        /*
        Customer c = service.signUp(form);
        assertNotNull(c.getId());
        assertNotNull(c.getCreatedAt());
        */
        Assert.isTrue(service.signUp(form).getId() != null, "signup failed");

    }
}