package com.personal.shop_smart.dto.user;

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
