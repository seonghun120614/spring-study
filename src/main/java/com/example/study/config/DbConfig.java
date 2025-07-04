package com.example.study.config;


import org.springframework.boot.context.properties.*;
import org.springframework.context.annotation.*;

@Configuration
@EnableConfigurationProperties(DbProperties.class)
public class DbConfig { }
