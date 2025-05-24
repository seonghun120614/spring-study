package com.example.study.config;


import lombok.*;
import org.springframework.boot.context.properties.*;
import org.springframework.boot.context.properties.bind.*;

import java.util.*;

@ConfigurationProperties(prefix="app.sbip.ct") // 안에 ConstructorBinding 이 포함되어 있음
@Getter
@ToString
public class AppProperties {
    /*
     타입 안전성 확보
     */
    private final String name;
    private final String ip;
    private final int port;
    private final Security security;

    // field, DefaultValue 를 통해 타입 안전성 확보
    public AppProperties(String name, String ip, @DefaultValue("8080") int port, Security security) {
        this.name = name;
        this.ip = ip;
        this.port = port;
        this.security = security;
    }

    @Getter
    @AllArgsConstructor
    @ToString
    public static class Security {
        private boolean enabled;
        private final String token;
        private final List<String> roles;

        public boolean isEnabled() {
            return enabled;
        }
    }
}
