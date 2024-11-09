package com.utp.seguridadperu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SeguridadperuApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeguridadperuApplication.class, args);
	}

}
