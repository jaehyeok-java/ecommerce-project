package com.project.user.controller;


import com.project.user.service.EmailSendService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final EmailSendService emailSendService;

    @GetMapping
    public String sendTestEmail() {
        return emailSendService.sendEmail();
    }

}
