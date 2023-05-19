package com.fer.hr.du;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication/*(exclude = {DataSourceAutoConfiguration.class })*/
public class DuApplication {

    public static void main(String[] args) {
        SpringApplication.run(DuApplication.class, args);
    }

}
