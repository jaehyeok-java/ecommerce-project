package com.project.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SignUpForm {
    private String email;
    private String password;
    private String name;
    private LocalDate birth;
    private String phone;
}
