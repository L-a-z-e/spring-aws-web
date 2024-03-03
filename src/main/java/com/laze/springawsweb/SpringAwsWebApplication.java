package com.laze.springawsweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableJpaAuditing
@SpringBootApplication
@EnableWebSecurity
public class SpringAwsWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAwsWebApplication.class, args);
    }

}
