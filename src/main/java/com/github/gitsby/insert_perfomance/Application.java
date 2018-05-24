package com.github.gitsby.insert_perfomance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;

@SpringBootApplication
public class Application {

    @Qualifier("dataSource")
    @Autowired
    private DataSource dataSource;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}