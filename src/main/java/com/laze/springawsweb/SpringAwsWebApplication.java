package com.laze.springawsweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
@EnableJpaAuditing
@SpringBootApplication
public class SpringAwsWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAwsWebApplication.class, args);
    }

}
