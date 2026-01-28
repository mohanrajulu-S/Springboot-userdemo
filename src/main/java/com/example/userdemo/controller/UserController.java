package com.example.userdemo.controller;

import com.example.userdemo.entity.User;
import com.example.userdemo.exception.UserNotFoundException;
import com.example.userdemo.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController
{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder)
    {
        this.userRepository=userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public List<User> getAllUsers()
    {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id)
    {
        return userRepository.findById(id)
         .orElseThrow(() -> new UserNotFoundException("User not found with id :" + id));

    }

    // ✅ POST - create user
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }


    // ✅ PUT - update user
    @PutMapping("/update/{id}")
    public User updateUser(@PathVariable Integer id, @RequestBody User user)
    {
        // Check if user exists
        User existingUser = userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found with id " + id)
                );


//        existingUser.setUserName(user.getUserName());
//        existingUser.setUserMail(user.getUserMail());
//        existingUser.setUserMobileNumber(user.getUserMobileNumber());
        existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
        existingUser.setRole(user.getRole());


        return userRepository.save(existingUser);
    }


    // ✅ DELETE - delete user
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Integer id)
    {
        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found with id :" + id)
                );

        userRepository.delete(user);
        return "User deleted successfully";
    }


 }












