package com.personal.shop_smart.dto.user;

import com.personal.shop_smart.entity.User;
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
