package com.example.study.event;

import org.springframework.context.event.*;

// ContextRefreshedEvent - ApplicationContext 가 refreshed 후에 published
public class ContextRefreshedEventListener
        extends AbstractLoggingListener<ContextRefreshedEvent> {
    @Override
    protected String message() {
        return "ApplicationContext 가 refreshed 후에 published";
    }
}
