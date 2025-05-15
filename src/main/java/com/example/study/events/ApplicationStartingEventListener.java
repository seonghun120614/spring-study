package com.example.study.events;

import org.springframework.boot.context.event.*;

// ApplicationStartingEvent - run() 을 통한 실행시 published
public class ApplicationStartingEventListener
        extends AbstractLoggingListener<ApplicationStartingEvent> {
    @Override
    protected String message() {
        return "Application 이 run() 이 완료되었습니다.";
    }
}
