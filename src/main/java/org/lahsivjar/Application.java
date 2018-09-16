package org.lahsivjar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		Runtime.getRuntime().addShutdownHook(new Thread() {

		});
		SpringApplication.run(Application.class, args);
	}
}
