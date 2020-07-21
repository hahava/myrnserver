package me.hahajava.rnserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {
	org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
public class RnServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(RnServerApplication.class, args);
	}
}
