package com.jeno.ecommerce_backend_api.dto.user;

public class BaseUserDto {
    private String name;
    private String email;
    private String password;
    private Long mobile;
    private String role;

    public BaseUserDto(String name, String email, String password, Long mobile, String role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.mobile = mobile;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Long getMobile() {
        return mobile;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
