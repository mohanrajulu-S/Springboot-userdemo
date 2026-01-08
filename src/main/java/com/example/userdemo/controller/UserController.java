package com.example.userdemo.controller;

import com.example.userdemo.entity.User;
import com.example.userdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController
{

    @Autowired
    private  UserRepository userRepository;

    @GetMapping
    public List<User> getAllUsers()
    {
        List<User> user=userRepository.findAll();
        return userRepository.findAll();
    }

    // ✅ POST - create user
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    // ✅ PUT - update user
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);          // set id from URL
        return userRepository.save(user);
    }

    // ✅ DELETE - delete user
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Integer id) {
        userRepository.deleteById(id);
        return "User deleted successfully";
    }
}












