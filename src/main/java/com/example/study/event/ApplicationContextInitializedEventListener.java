package com.example.study.event;

import org.springframework.boot.context.event.*;

// ApplicationContextInitializedEvent - ApplicationContext 가 준비되고, ApplicationContextInitializers 가 실행 시 published
public class ApplicationContextInitializedEventListener
        extends AbstractLoggingListener<ApplicationContextInitializedEvent> {
    @Override
    protected String message() {
        return "ApplicationContext 가 준비되고, Application Context Initializers 가 실행될 시 published";
    }
}
