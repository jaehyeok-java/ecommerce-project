package com.project.user.client.mailgun;

import lombok.*;

@AllArgsConstructor
@Getter
@Builder
@NoArgsConstructor
@Data
public class SendMailForm {
    private String from;
    private String to;
    private String subject;
    private String text;
}
