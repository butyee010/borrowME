package com.borrow.blockchain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication(scanBasePackages = {"com.borrow.blockchain"})
@PropertySources({
@PropertySource(value = {"classpath:application.properties"} , ignoreResourceNotFound = true) ,
@PropertySource(value = {"file:${env}"} , ignoreResourceNotFound = true)  //if same key, this will 'win'
})
public class BorrowMeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BorrowMeApplication.class, args);
	}
}
