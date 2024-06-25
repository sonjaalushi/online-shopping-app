package com.sda.online_shopping_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class OnlineShoppingAppApplication{

	public static void main(String[] args) {
		SpringApplication.run(OnlineShoppingAppApplication.class, args);
	}


}