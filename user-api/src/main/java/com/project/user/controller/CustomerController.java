package com.project.user.controller;

import com.project.domain.config.JwtAuthenticationProvider;
import com.project.domain.domain.common.UserVo;
import com.project.user.domain.customer.ChangeBalanceForm;
import com.project.user.domain.customer.CustomerDto;
import com.project.user.domain.model.Customer;
import com.project.user.exception.CustomException;

import com.project.user.service.CustomerBalanceService;
import com.project.user.service.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.project.user.exception.ErrorCode.NOT_FOUND_USER;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private final JwtAuthenticationProvider provider;
    private final CustomerService customerService;
    private final CustomerBalanceService customerBalanceService;

    @GetMapping("/getInfo")
    public ResponseEntity<CustomerDto> getInfo(@RequestHeader(name = "X-AUTH-TOKEN") String token) {
        UserVo vo = provider.getUserVo(token);
        Customer c = customerService.findByIdAndEmail(vo.getId(), vo.getEmail()).orElseThrow(
                () -> new CustomException(NOT_FOUND_USER));

        return ResponseEntity.ok(CustomerDto.from(c));
    }

    @PostMapping("/balance")
    public ResponseEntity<Integer> changeBalance(@RequestHeader(name = "X-AUTH-TOKEN") String token,
                                                 @RequestBody ChangeBalanceForm form) {
        UserVo vo = provider.getUserVo(token);
        return ResponseEntity.ok(customerBalanceService.changeBalance(vo.getId(),form).getCurrentMoney());
    }

}
