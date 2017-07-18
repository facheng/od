package com.od.oh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
public class OHApplication {

    public static void main(String[] args) {
        SpringApplication.run(OHApplication.class, args);
    }
}
