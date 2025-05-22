package com.example.study.events;

import com.example.study.log.*;
import org.springframework.context.*;

public abstract class AbstractLoggingListener<T extends ApplicationEvent>
        implements ApplicationListener<T> {
    protected abstract String message();

    // 이벤트가 publish 되면 실행됨.
    public void onApplicationEvent(T event) {
        Logger.logging(event, message());
    }
}
