package com.jeno.ecommerce_backend_api.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseUserDto {
    private String name;
    private String email;
    private String password;
    private Long mobile;
    private String role;
}
