package com.am.innovations.hibernate.jpa;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/V_1.0")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/get")
    public User get(@RequestParam(value = "userID") Long userID) throws InterruptedException, ExecutionException {
        return userRepository.findById(userID).get();
    }

    @PostMapping("/add")
    public User add(@RequestParam(value = "userName") String userName) throws InterruptedException, ExecutionException {
        return userRepository.save(new User(userName));
    }

}