package com.example.study_with_gpt.controller;

import com.example.study_with_gpt.dto.UserDto;
import com.example.study_with_gpt.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserDto.Response get(@PathVariable Long id) {
//        return new UserDto.Response(id, "ada", "ada@example.com");
        return userService.get(id);

    }

    @PostMapping
    public UserDto.Response create(@Valid @RequestBody UserDto.CreateRequest createRequest) {
//        return new UserDto.Response(10L, createRequest.name(), createRequest.email());
        return userService.create(createRequest);
    }
}
