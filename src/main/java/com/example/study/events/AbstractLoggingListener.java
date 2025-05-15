package com.example.study.events;

import com.example.study.log.*;
import org.springframework.context.*;

public abstract class AbstractLoggingListener<T extends ApplicationEvent>
        implements ApplicationListener<T> {
    protected abstract String message();
    public void onApplicationEvent(T event) {
        Logger.logging(event, message());
    }
}
