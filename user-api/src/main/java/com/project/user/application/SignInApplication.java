package com.project.user.application;

import com.project.domain.config.JwtAuthenticationProvider;
import com.project.domain.domain.common.UserType;
import com.project.user.domain.SignInForm;
import com.project.user.domain.model.Customer;
import com.project.user.exception.CustomException;
import com.project.user.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.project.user.exception.ErrorCode.LOGIN_CHECK_FAIL;

@Service
@RequiredArgsConstructor
public class SignInApplication {

    private final CustomerService customerService;
    private final JwtAuthenticationProvider provider;

    public String customerLoginToken(SignInForm form) {

        // 1. 로그인 가능 여부
        Customer c = customerService.findValidCustomer(form.getEmail(), form.getPassword())
                .orElseThrow(() -> new CustomException(LOGIN_CHECK_FAIL));

        // 2. 토큰 발행


        // 3. 토큰을 response
        return provider.createToken(c.getEmail(), c.getId(), UserType.CUSTOMER);
    }
}
