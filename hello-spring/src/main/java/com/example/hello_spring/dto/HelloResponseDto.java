package com.example.hello_spring.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter // 2. 이 클래스의 모든 필드에 대한 'getter' 메소드를 자동으로 만들어줍니다.
@RequiredArgsConstructor // 3. 'final'이 붙은 필드를 포함하는 생성자를 자동으로 만들어줍니다.
public class HelloResponseDto {

    // 1. '필드(field)' 설정
    // final 키워드는 이 값이 한번 설정되면 변경될 수 없다는 의미입니다.
    private final String name;
    private final String message;
}
