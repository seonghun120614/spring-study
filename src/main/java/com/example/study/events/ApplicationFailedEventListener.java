package com.example.study.events;

import org.springframework.boot.context.event.*;

// ApplicationFailedEvent - 어플리케이션 시작과정에서 예외가 발생하면 published
public class ApplicationFailedEventListener
        extends AbstractLoggingListener<ApplicationFailedEvent> {
    @Override
    protected String message() {
        return "어플리케이션 시작 과정에서 예외 발생 시 published";
    }
}
