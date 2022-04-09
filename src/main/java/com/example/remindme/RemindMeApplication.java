package com.example.remindme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

//EnableScheduling pour permettre a l'app de faire des actions tout les X sec
@SpringBootApplication
@EnableScheduling
public class RemindMeApplication {

	public static void main(String[] args) {
		SpringApplication.run(RemindMeApplication.class, args);
	}

}
