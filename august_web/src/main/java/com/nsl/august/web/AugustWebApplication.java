package com.nsl.august.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan(basePackages={"com.nsl.august.web", "com.nsl.auguest.core"})
@EnableTransactionManagement
public class AugustWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(AugustWebApplication.class, args);
	}

}
