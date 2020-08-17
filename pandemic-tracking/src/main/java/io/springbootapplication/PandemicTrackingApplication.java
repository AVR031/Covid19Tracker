package io.springbootapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PandemicTrackingApplication {

	public static void main(String[] args) {
		SpringApplication.run(PandemicTrackingApplication.class, args);
	}
}