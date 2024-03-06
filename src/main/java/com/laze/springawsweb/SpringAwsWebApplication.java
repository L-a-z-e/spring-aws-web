package com.laze.springawsweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

//@EnableJpaAuditing ( 테스트 오류 -> WebMvcTest에서 EnableJpaAuditing 스캔 -> @Entity 클래스가 없음 -> 에러
@SpringBootApplication
@EnableWebSecurity
public class SpringAwsWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAwsWebApplication.class, args);
    }

}
