package com.example.study.event;

import org.springframework.boot.context.event.*;

// ApplicationEnvironmentPreparedEvent - Environment 가 준비될 시 published
public class ApplicationEnvironmentPreparedEventListener
        extends AbstractLoggingListener<ApplicationEnvironmentPreparedEvent> {
    @Override
    protected String message() {
        return "Environment 가 준비되었습니다.";
    }
}
