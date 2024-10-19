package com.jeno.ecommerce_backend_api.service;

import com.jeno.ecommerce_backend_api.entity.User;
import com.jeno.ecommerce_backend_api.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    //Get a user by id
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    //create a new user
    public User createUser(User user) {
        log.info("ufheufheifj");
        return userRepository.save(user);
    }

    //Update an existing user
    public User updateUser(Long id, User userDetails) {
        return userRepository.findById(id)
                .map(existingUser->{
                    existingUser.setUsername(userDetails.getUsername());
                    existingUser.setEmail(userDetails.getPassword());
                    existingUser.setPassword(userDetails.getPassword());
                    return userRepository.save(existingUser);
                })
                .orElseThrow(()->new RuntimeException("Resource not Found,"+id));
    }

    //Delete a user
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
