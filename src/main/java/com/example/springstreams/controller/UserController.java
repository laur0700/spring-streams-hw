package com.example.springstreams.controller;

import com.example.springstreams.model.User;
import com.example.springstreams.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.stream.Stream;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("get-all")
    public ArrayList<User> getAllUsers(){
        return userService.getAll();
    }

    @PostMapping("add")
    public void addNewUser(){
        userService.create();
    }

    @PostMapping("stream")
    public void makeStreamDoStuff(){
        userService.printFilteredStream();
        userService.printDoubledStream();
        userService.printLastElementOfStream();
        userService.printAges();
        userService.printDistinctStream();
        userService.printLastExercise();
        System.out.println("\n-------------------------------------\n");
    }
}
