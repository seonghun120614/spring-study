package com.example.study.event;

import org.springframework.boot.web.context.*;

// WebServerInitializedEvent - 웹서버가 준비되면 published
public class WebServerInitializedEventListener
        extends AbstractLoggingListener<WebServerInitializedEvent> {
    @Override
    protected String message() {
        return "웹 서버가 준비 완료";
    }
}
