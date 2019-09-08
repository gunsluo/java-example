package com.example.yamldemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class YamlDemoComponent {

    @Value("${test.enable}")
    private String enable;

    @Bean
    public void init() {
        System.out.println("enable:" + enable);
    }
}
