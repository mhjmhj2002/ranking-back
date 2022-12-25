package com.mhj.ranking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan({"com.mhj.ranking"})
public class RankingApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(RankingApplication.class, args);
    }
}