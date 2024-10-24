package com.jeno.ecommerce_backend_api.dto.user;

import com.jeno.ecommerce_backend_api.entity.User;

public class UserResponseDto extends User {
    private Long id;
    private String name;
    private String email;
    private Long mobile;
    private String role;

    public UserResponseDto(Long id, String name, String email, Long mobile, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
