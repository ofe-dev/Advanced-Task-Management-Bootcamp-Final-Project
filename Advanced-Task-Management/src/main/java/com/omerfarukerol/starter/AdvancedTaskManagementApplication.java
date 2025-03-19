package com.omerfarukerol.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = {"com.omerfarukerol"})
@EntityScan(basePackages = {"com.omerfarukerol"})
@EnableJpaRepositories(basePackages = {"com.omerfarukerol"})
@SpringBootApplication
public class AdvancedTaskManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdvancedTaskManagementApplication.class, args);
	}

}
