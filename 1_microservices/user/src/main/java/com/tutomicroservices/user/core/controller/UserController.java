package com.tutomicroservices.user.core.controller;

import com.tutomicroservices.user.core.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String getAllUsers() {
        log.info(">>> Start endpoint getAllUsers");
        String result = userService.getAllUsers();
        log.info("<<< End endpoint getAllUsers");
        return result;
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") int userId) {
        log.info(">>> Start endpoint getUserById -> userId:{}", userId);
        String result = userService.getUserById(userId);
        log.info("<<< End endpoint getAllUsers");
        return result;
    }
}
