package com.example.study.event;

import org.springframework.boot.context.event.*;

// ApplicationPreparedEvent - ApplicationContext 가 준비되고 빈이 로딩이 됐지만 아직 ApplicationContext 가 un-refreshed 인 상태에서 published
public class ApplicationPreparedEventListener
        extends AbstractLoggingListener<ApplicationPreparedEvent> {
    @Override
    protected String message() {
        return "ApplicationContext 준비 완료, Bean 로딩 완료, ApplicationContext 가 un-refreshed 상태";
    }
}
