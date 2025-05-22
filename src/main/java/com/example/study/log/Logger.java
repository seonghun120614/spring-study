package com.example.study.log;

import org.springframework.context.*;

import java.util.*;

public class Logger {
    public static void logging(ApplicationEvent event, String msg) {
        System.out.println("[" + new Date(event.getTimestamp()) + "] " + event.getClass().getSimpleName() +
                " : " + msg);
    }
}
