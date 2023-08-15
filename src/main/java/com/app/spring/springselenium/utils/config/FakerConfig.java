package com.app.spring.springselenium.utils.config;

import com.app.spring.springselenium.utils.annotation.LazyConfiguration;
import com.github.javafaker.Faker;
import org.springframework.context.annotation.Bean;

@LazyConfiguration
public class FakerConfig {

    @Bean
    public Faker getFaker(){
        return new Faker();
    }

}
