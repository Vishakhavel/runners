package com.vishakhavel.runnerz;

import com.vishakhavel.runnerz.run.Location;
import com.vishakhavel.runnerz.run.Run;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;


@SpringBootApplication
public class RunnerzApplication {
	private static final Logger log = LoggerFactory.getLogger(RunnerzApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(RunnerzApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(){
		return args -> {
			Run run = new Run(1, "run1", LocalDateTime.now(), LocalDateTime.now(), 3, Location.OUTDOOR);
			log.info("Run -> " + run);
		};
	}
}
