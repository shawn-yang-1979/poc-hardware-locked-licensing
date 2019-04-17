package com.example.keypairgenerator;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * This is a poc copy from
 * https://www.mkyong.com/java/java-asymmetric-cryptography-example/
 * 
 * @author SHAWN.SH.YANG
 *
 */
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}

	@Bean
	public CommandLineRunner loadData(KeyPairGeneratorComponent comp) {
		return args -> comp.createKeys();
	}

}
