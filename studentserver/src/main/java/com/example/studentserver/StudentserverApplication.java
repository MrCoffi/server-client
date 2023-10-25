package com.example.studentserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class StudentserverApplication {
    @Autowired
    private StudentServer server;

    public static void main(String[] args) {
        SpringApplication.run(StudentserverApplication.class, args);
    }


    @EventListener(ApplicationReadyEvent.class)
    public void setServer() {
        server.start();
    }
}
