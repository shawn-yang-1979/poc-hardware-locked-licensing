package com.example.oshi;

import java.io.FileWriter;
import java.io.IOException;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}

	@Bean
	public CommandLineRunner loadData(ComputerIdentifierComponent comp) {
		return args -> {
			ComputerIdentifier cid = comp.getComputerIdentifier();
			ObjectMapper mapper = new ObjectMapper();
			try (FileWriter file = new FileWriter(ComputerIdentifier.class.getSimpleName())) {
				file.write(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(cid));
				file.flush();
			} catch (IOException e) {
				log.error(e.getMessage(), e);
			}
		};
	}

}
