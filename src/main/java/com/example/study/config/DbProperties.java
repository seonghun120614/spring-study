package com.example.study.config;

import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.boot.context.properties.*;

@AllArgsConstructor
@Getter
@ToString
@ConfigurationProperties(prefix = "spring.datasource")
public class DbProperties {
    @NotBlank
    private final String url;
    @NotBlank
    private final String username;
    @NotBlank
    private final String password;
    @NotBlank
    private final String driverClassName;
}
