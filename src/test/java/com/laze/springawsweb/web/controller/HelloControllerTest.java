package com.laze.springawsweb.web.controller;

import com.laze.springawsweb.config.auth.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

/**
 * RunWith
 *  테스트 진행할 때 JUnit에 내장된 실행자 외에 다른 실행자를 실행시킴
 *  SpringRunner 라는 실행자 사용
 *  Spring Boot Test 와 JUnit 사이에서 연결자 역할
 *  .class -> 클래스 리터럴 -> @RunWith 에서는 Class 객체를 인자로 받음
 *  Class 객체는 해당 클래스의 메타데이터를 담고있고, 리플렉션을 사용하여 클래스의 메서드, 필드, 생성자 등의 정보를 조회, 조작하는데 사용됨
 *
 * WebMvcTest
 *  Web(Spring MVC)
 * 해당 어노테이션 사용할 경우 @Controller, @ControllerAdvice 등 사용 가능
 * 단, @Service, @Component, @Repository 사용 불가능
 */
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HelloController.class,
excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
})
    public class HelloControllerTest {

    /**
     * Autowired
     *  Spring이 관리하는 Bean을 주입받음
     * MockMvc
     *  Web API Test 시 사용
     *  Spring MVC TEST의 시작점
     *  GET, POST 등에 대한 API 테스트를 할 수 있음
     */
    @Autowired
    private MockMvc mvc;

    /**
     * mvc.perform(get("/hello")
     *  MockMvc를 통해 /hello 주소로 HTTP GET 요청
     *  체이닝 지원 -> 여러 검증 기능을 이어서 선언할 수 있음
     * .andExpect(status().isOk())
     *  mvc.perform의 결과 검증
     *  HTTP Header의 Status 검증
     * .andExpect(content().string(hello))
     *  응답 본문의 내용 검증
     */
    @Test
    @WithMockUser(roles = "USER")
    public void hello() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }

    @Test
    @WithMockUser(roles = "USER")
    public void helloDto() throws Exception {
        //given
        String name = "hello";
        int amount = 1000;

        /**
         * param
         *  API 테스트시 사용될 요청 파라미터 설정, String 만 허용 -> 숫자, 날짜는 문자열로 변경해야함
         * jsonPath
         *  JSON 응답값을 필드별로 검증할 수 있는 메서드
         *  $기준으로 필드명 명시
         */
        mvc.perform(get("/hello/dto")
                .param("name", name)
                .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount",is(amount)));
    }
}