package com.project.user.service;

import com.project.user.client.MailgunClient;
import com.project.user.client.mailgun.SendMailForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSendService {
    private final MailgunClient mailgunClient;

    public String sendEmail() {

        SendMailForm form = SendMailForm.builder()
                .from("Mailgun Sandbox <postmaster@sandbox4cbc225f64694fff9e4669348b36c4bd.mailgun.org>")
                .to("KimJaeHyeok <jaehyeok.ethan@gmail.com>")
                .subject("Test Mail")
                .text("this is a test mail")
                .build();

        return mailgunClient.sendEmail(form).getBody();
    }

}
