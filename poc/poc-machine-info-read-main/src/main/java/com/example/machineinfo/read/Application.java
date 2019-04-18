package com.example.machineinfo.read;

import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.machineinfo.MachineInfo;
import com.example.machineinfo.reader.MachineInfoReader;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}

	@Bean
	public CommandLineRunner runner(MachineInfoReader machineInfoReader) {
		return args -> {
			MachineInfo mi = machineInfoReader.getMachineInfo();
			ObjectMapper mapper = new ObjectMapper();
			FileUtils.write(Paths.get("data/machine-info.json").toFile(),
					mapper.writerWithDefaultPrettyPrinter().writeValueAsString(mi), StandardCharsets.UTF_8);

		};
	}

}
