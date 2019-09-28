package com.jcainelli.redirect.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.jcainelli.redirect.util.dev.InitializeDataBaseDesenvUtil;

@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private InitializeDataBaseDesenvUtil initializeDataBaseDesenv;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    @Bean
    public boolean instantiateDatabase() throws ParseException {

        if (!"create".equals(strategy)) {
            initializeDataBaseDesenv.instantiateDatabase();
        }
        return true;
    }

}
