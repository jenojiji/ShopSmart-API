package com.jeno.ecommerce_backend_api.dto.auth;

public class AuthenticationRequestDto {
    private String email;
    private String password;

    public AuthenticationRequestDto(String password, String email) {
        this.password = password;
        this.email = email;
    }

    public AuthenticationRequestDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
