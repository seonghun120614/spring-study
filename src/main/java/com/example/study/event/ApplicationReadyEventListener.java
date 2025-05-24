package com.example.study.event;


import org.springframework.boot.context.event.*;

// ApplicationReadyEvent - 어플리케이션이 요청을 처리할 준비가 되었을 때 published
public class ApplicationReadyEventListener
        extends AbstractLoggingListener<ApplicationReadyEvent> {
    @Override
    protected String message() {
        return "어플리케이션이 요청을 처리할 준비가 되면 SpringApplication 에 의해 published";
    }
}
