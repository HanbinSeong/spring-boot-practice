package com.example.study_with_gpt.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public final class UserDto {

    // 요청 dto
    public record CreateRequest(
            @NotBlank String name,
            @Email String email
    ) {}

    // 응답 dto
    public record Response(
            Long id,
            String name,
            String email
    ) {}
}
