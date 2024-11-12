package com.jeno.ecommerce_backend_api.controller.auth;

import com.jeno.ecommerce_backend_api.dto.auth.AuthenticationRequestDto;
import com.jeno.ecommerce_backend_api.dto.user.BaseUserDto;
import com.jeno.ecommerce_backend_api.dto.user.UserResponseDto;
import com.jeno.ecommerce_backend_api.entity.User;
import com.jeno.ecommerce_backend_api.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    //Register
    @PostMapping("/register")
    public ResponseEntity<String> createUser(@RequestBody BaseUserDto user) {
        user.setEmail(user.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_" + user.getRole().toUpperCase());
        userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered succesfully");
    }

    //Login
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody AuthenticationRequestDto authenticationRequestDto, HttpSession session) {
        UsernamePasswordAuthenticationToken token = UsernamePasswordAuthenticationToken.unauthenticated(
                authenticationRequestDto.getEmail(), authenticationRequestDto.getPassword());
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

        User user = (User) authentication.getPrincipal();
        UserResponseDto responseDto = new UserResponseDto(user.getId(), user.getName(), user.getEmail(), user.getMobile(), user.getRole());
        return ResponseEntity.ok(user);
    }

    //Logout
    @PostMapping("/logout")
    public ResponseEntity<String> logoutUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.ok("User logged out successfully");
    }
}
