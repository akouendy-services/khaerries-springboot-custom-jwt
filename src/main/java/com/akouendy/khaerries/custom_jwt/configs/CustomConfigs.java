package com.akouendy.khaerries.custom_jwt.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CustomConfigs {
    @Bean
   public RestTemplate restTemplateBean() {
        return new RestTemplate();
    }
}
