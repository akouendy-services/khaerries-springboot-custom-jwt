package com.akouendy.khaerries.custom_jwt.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@ConfigurationProperties(prefix = "firebase")
@Data()
@Configuration("firebaseConfig")
public class FirebaseConfig {
    private String apiKey;
}
