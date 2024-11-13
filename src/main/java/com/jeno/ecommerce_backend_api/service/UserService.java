package com.jeno.ecommerce_backend_api.service;

import com.jeno.ecommerce_backend_api.dto.user.BaseUserDto;
import com.jeno.ecommerce_backend_api.entity.User;
import com.jeno.ecommerce_backend_api.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Value("${TWO_FACTOR_API_KEY}")
    private String TWO_FACTOR_API_KEY;

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

    // send otp to mobile number
    public Response verifyMobileWithOTP(Long id) throws IOException {
        User user = userRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("User Not Found with ID :" + id));
        Long mobileNumber = user.getMobile();
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        String url = "https://2factor.in/API/V1/" + TWO_FACTOR_API_KEY + "/SMS/+91" +
                mobileNumber + "/AUTOGEN2/OTP1";
        Request request = new Request.Builder()
                .url(url)
                .method("GET", null)
                .build();
        System.out.println("-------send otp service--------");
        return client.newCall(request).execute();
    }

    //validate otp
    //fix needed : validation not working properly
    public Response validateOtp(Long id, String otp) throws IOException {
        User user = userRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("User Not Found with ID :" + id));
        Long mobileNumber = user.getMobile();
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        String url = "https://2factor.in/API/V1/" +
                TWO_FACTOR_API_KEY + "/SMS/VERIFY3/+91" + mobileNumber + "/" + otp;
        Request request = new Request.Builder()
                .url(url)
                .method("GET", null)
                .build();
        System.out.println("------validate otp service---------");
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            user.setIsMobileValidated(true);
            userRepository.save(user);
            return response;
        } else {
            return response;
        }
    }
}
