package com.project.user.controller;

import com.project.domain.config.JwtAuthenticationProvider;
import com.project.domain.domain.common.UserVo;
import com.project.user.domain.customer.CustomerDto;
import com.project.user.domain.model.Customer;
import com.project.user.exception.CustomException;
import com.project.user.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.project.user.exception.ErrorCode.NOT_FOUND_USER;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private final JwtAuthenticationProvider provider;
    private final CustomerService customerService;

    @GetMapping("/getInfo")
    public ResponseEntity<CustomerDto> getInfo(@RequestHeader(name = "X-AUTH-TOKEN") String token) {
        UserVo vo = provider.getUserVo(token);
        Customer c = customerService.findByIdAndEmail(vo.getId(), vo.getEmail()).orElseThrow(
                () -> new CustomException(NOT_FOUND_USER));

        return ResponseEntity.ok(CustomerDto.from(c));
    }


}
