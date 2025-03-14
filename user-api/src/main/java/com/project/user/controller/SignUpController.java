package com.project.user.controller;

import com.project.user.application.SignUpApplication;
import com.project.user.domain.SignUpForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("signup")
public class SignUpController {

    private final SignUpApplication signUpApplication;

    @PostMapping
    public ResponseEntity<String> customerSignUp(@RequestBody SignUpForm form) {
        return ResponseEntity.ok(signUpApplication.customerSignUp(form));
    }

    @GetMapping("/verify/customer")
    public ResponseEntity<String> verifyCustomer(
            @RequestParam String email,
            @RequestParam String code) {

        signUpApplication.customerVerify(email, code);
        return ResponseEntity.ok("인증이 완료되었습니다.");
    }

    @PutMapping("/verify/customer")
    public ResponseEntity<String> verifyCustomerput(
            @RequestParam String email,
            @RequestParam String code) {

        signUpApplication.customerVerify(email, code);
        return ResponseEntity.ok("인증이 완료되었습니다.");
    }
}
