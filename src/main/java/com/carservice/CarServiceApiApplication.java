package com.carservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@org.springframework.boot.autoconfigure.domain.EntityScan("com.carservice.entity") 
@EnableJpaRepositories("com.carservice.dao") 
public class CarServiceApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(CarServiceApiApplication.class, args);
    }
}