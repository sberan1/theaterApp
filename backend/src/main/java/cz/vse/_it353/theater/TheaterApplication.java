package cz.vse._it353.theater;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class where application is turned on.
 */
@SpringBootApplication
public class TheaterApplication {
	/**
	 * Main method where the spring application is running.
	 * @param args args
	 */
	public static void main(String[] args) {
		SpringApplication.run(TheaterApplication.class, args);
	}

}
