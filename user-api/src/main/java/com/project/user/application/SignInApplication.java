package com.project.user.application;

import com.project.domain.config.JwtAuthenticationProvider;
import com.project.domain.domain.common.UserType;
import com.project.user.domain.SignInForm;
import com.project.user.domain.model.Customer;
import com.project.user.domain.model.Seller;
import com.project.user.exception.CustomException;
import com.project.user.service.customer.CustomerService;
import com.project.user.service.seller.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.project.user.exception.ErrorCode.LOGIN_CHECK_FAIL;

@Service
@RequiredArgsConstructor
public class SignInApplication {

    private final SellerService sellerService;
    private final CustomerService customerService;
    private final JwtAuthenticationProvider provider;

    public String customerLoginToken(SignInForm form) {
        // 로그인 가능 여부
        Customer c = customerService.findValidCustomer(form.getEmail(), form.getPassword())
                .orElseThrow(() -> new CustomException(LOGIN_CHECK_FAIL));

        return provider.createToken(c.getEmail(), c.getId(), UserType.CUSTOMER);
    }


    public String sellerLoginToken(SignInForm form) {

        Seller s = sellerService.findValidSeller(form.getEmail(), form.getPassword())
                .orElseThrow(() -> new CustomException(LOGIN_CHECK_FAIL));

        return provider.createToken(s.getEmail(), s.getId(), UserType.SELLER);
    }
}
