package com.Munivel.SpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// h2 database is an temporary only when you restart your applicaion it is gone
// so crear an file in the resouce it will lood at by the h2
// to store data permanentely
@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		      SpringApplication.run(Application.class, args);
	}
}