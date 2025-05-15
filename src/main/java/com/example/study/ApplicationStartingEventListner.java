package com.example.study;

import org.springframework.boot.context.event.*;
import org.springframework.context.*;

import java.util.*;

public class ApplicationStartingEventListner implements ApplicationListener<ApplicationStartingEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartingEvent event) {
        System.out.println("Application Starting Event logged at " + new Date(event.getTimestamp()));
    }
}
