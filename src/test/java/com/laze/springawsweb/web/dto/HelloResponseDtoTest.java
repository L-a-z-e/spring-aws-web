package com.laze.springawsweb.web.dto;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class HelloResponseDtoTest {

    @Test
    public void lombok_test() {
        //given
        String name = "laze";
        int amount = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name,amount);

        //then
        // assertThat - assertj 테스트 검증 라이브러리의 검증 메서드
        //              메서드 체이닝 지원
        // isEqualTo  - assertj의 동등 비교 메서드
        // JUnit > assertThat <> assertj > assertThat
        // CoreMatchers 라이브러리 필요유무 ( 전자 o , 후자 x )
        // 자동완성기능
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
    }

}