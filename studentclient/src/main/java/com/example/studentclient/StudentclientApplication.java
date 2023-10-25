package com.example.studentclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class StudentclientApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentclientApplication.class, args);
	}

}
