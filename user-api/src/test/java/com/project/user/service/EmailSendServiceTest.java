package com.project.user.service;

import com.project.user.UserApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = UserApplication.class)
class EmailSendServiceTest {

    @Autowired
    private EmailSendService emailSendService;

    @Test
    void emailTest() {

        String response = emailSendService.sendEmail();
        System.out.println("response: " + response);

    }
}