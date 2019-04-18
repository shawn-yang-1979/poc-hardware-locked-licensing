package com.example.machineinfo.sign;

import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import com.example.machineinfo.MachineInfo;
import com.example.machineinfo.MachineSignature;
import com.example.machineinfo.signer.MachineInfoSigner;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}

	@Component
	public class Runner implements CommandLineRunner {

		@Autowired
		private MachineInfoSigner machineInfoSigner;

		@Override
		public void run(String... args) throws Exception {
			ObjectMapper mapper = new ObjectMapper();
			MachineInfo machineInfo = mapper.readValue(Paths.get("data/machine-info.json").toFile(), MachineInfo.class);
			MachineSignature signature = machineInfoSigner.sign(machineInfo);
			mapper//
					.writerWithDefaultPrettyPrinter()//
					.writeValue(Paths.get("data/machine-signature.json").toFile(), signature);
		}

	}

}
