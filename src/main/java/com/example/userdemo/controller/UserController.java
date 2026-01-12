package com.example.userdemo.controller;

import com.example.userdemo.entity.User;
import com.example.userdemo.exception.UserNotFoundException;
import com.example.userdemo.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController
{

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository)
    {
        this.userRepository=userRepository;
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
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Integer id, @RequestBody User user)
    {
        // Check if user exists
        User existingUser = userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found with id " + id)
                );

        existingUser.setUserName(user.getUserName());
        existingUser.setUserMail(user.getUserMail());
        existingUser.setUserMobileNumber(user.getUserMobileNumber());

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












