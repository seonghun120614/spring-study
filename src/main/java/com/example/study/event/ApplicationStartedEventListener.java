package com.example.study.event;

import org.springframework.boot.context.event.*;

// ApplicationStartedEvent - ApplicationContext 가 refreshed 되고 나서 ApplicationRunner 와 CommandLineRunner 가 호출 되기 전 published
public class ApplicationStartedEventListener
        extends AbstractLoggingListener<ApplicationStartedEvent> {
    @Override
    protected String message() {
        return "ApplicationContext 가 refreshed, ApplicationRunner 와 CommandLineRunner 가 호출 되기 전 published";
    }
}
