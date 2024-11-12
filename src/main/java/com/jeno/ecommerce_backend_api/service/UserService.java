package com.jeno.ecommerce_backend_api.service;

import com.jeno.ecommerce_backend_api.dto.user.BaseUserDto;
import com.jeno.ecommerce_backend_api.entity.User;
import com.jeno.ecommerce_backend_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    //Get all users
    public Page<User> getAllUsers(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return userRepository.findAll(pageable);
    }

    //Get a user by id
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    //create a new user
    public void createUser(BaseUserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setMobile(userDto.getMobile());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());
        userRepository.save(user);
    }

    //Update an existing user
    public User updateUser(Long id, User userDetails) {
        return userRepository.findById(id).map(existingUser -> {
            existingUser.setEmail(userDetails.getEmail());
            return userRepository.save(existingUser);
        }).orElseThrow(() -> new RuntimeException("Resource not Found," + id));
    }

    //Delete a user
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
