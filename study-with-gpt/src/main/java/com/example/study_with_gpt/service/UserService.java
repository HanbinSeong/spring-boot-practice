package com.example.study_with_gpt.service;

import com.example.study_with_gpt.domain.User;
import com.example.study_with_gpt.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {
    private final Map<Long, User> store = new ConcurrentHashMap<>();
    private final AtomicLong seq = new AtomicLong(0);

    public UserDto.Response get(Long id) {
        var u = store.get(id);
        if(u == null) throw  new IllegalArgumentException("User not found: " + id);
        return new UserDto.Response(u.getId(), u.getName(), u.getEmail());
    }

    public UserDto.Response create(UserDto.CreateRequest createRequest) {
        var id = seq.incrementAndGet();
        var u = new User(id, createRequest.name(), createRequest.email());
        store.put(id, u);
        return new UserDto.Response(u.getId(), u.getName(), u.getEmail());
    }
}
