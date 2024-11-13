package com.jeno.ecommerce_backend_api.controller.user;


import com.jeno.ecommerce_backend_api.entity.User;
import com.jeno.ecommerce_backend_api.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    //get a user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //update user by ID
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User newUser = userService.updateUser(id, user);
        return ResponseEntity.ok(newUser);
    }

    //delete user by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    //send otp to mobile number
    @GetMapping("/verify/mobile/{id}")
    public ResponseEntity<Response> sendOtpToMobile(@PathVariable Long id) throws IOException {
        log.info("STARTED OTP SENDING");
        Response response = userService.verifyMobileWithOTP(id);
        log.info("OTP SENT SUCCESSFULLY");
        return ResponseEntity.ok(response);
    }

    //validate otp
    @PostMapping("/verify/mobile/{id}")
    public ResponseEntity<Response> validateOtp(@PathVariable Long id, @RequestParam String otp) throws IOException {
        log.info("OTP VALIDATION STARTED");
        Response response = userService.validateOtp(id, otp);
        log.info("OTP VALIDATED;STATUS CHANGED IN DB");
        return ResponseEntity.ok(response);
    }

}
