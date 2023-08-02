package com.sohosqared.omdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
public class OmdbApplication {

    public static void main(String[] args) {
        SpringApplication.run(OmdbApplication.class, args);
    }

}
