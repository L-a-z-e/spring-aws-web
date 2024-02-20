package com.laze.springawsweb.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter // 선언된 모든 필드의 get Method 생성
@RequiredArgsConstructor // final 이 포함된 필드가 포함된 생성자 생성, final이 없으면 생성자에 포함되지 않음
public class HelloResponseDto {
    private final String name;
    private final int amount;
}
