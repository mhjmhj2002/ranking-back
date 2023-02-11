package com.mhj.ranking.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableConfigurationProperties
@EntityScan(basePackages = {"com.mhj.ranking.entity"})  // scan JPA entities
public class RankingCoreApplication {
	private static ConfigurableApplicationContext applicationContext;

    public static void main(String[] args) {
    	RankingCoreApplication.applicationContext = SpringApplication.run(RankingCoreApplication.class, args);
//        SpringApplication.run(RankingCoreApplication.class, args);
    }
}