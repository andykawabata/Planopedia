package com.team.planopedia.controllers;


import com.team.planopedia.dao.User;
import com.team.planopedia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/test")
    @ResponseBody
    public String CreateNewUser(){
        User user = new User();
        user.setUserName("");
        user.setgoogleEmail("");
        userRepository.save(user);
        return "save: "+user;

    }






}
