package com.jeno.ecommerce_backend_api.dto.user;

import com.jeno.ecommerce_backend_api.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto extends User {
    private Long id;
    private String name;
    private String email;
    private Long mobile;
    private String role;
}
