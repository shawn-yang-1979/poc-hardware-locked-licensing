package com.example.machineinfo.verify;

import java.nio.file.Paths;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.digitalsignature.DigitalSignature;
import com.example.machineinfo.MachineInfo;
import com.example.machineinfo.MachineSignature;
import com.example.machineinfo.reader.MachineInfoReader;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * This is a poc copy from
 * https://www.mkyong.com/java/java-asymmetric-cryptography-example/
 * 
 * @author SHAWN.SH.YANG
 *
 */
@Slf4j
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}

	@Bean
	public MachineInfoReader machineInfoReader() {
		return new MachineInfoReader();
	}

	@Bean
	public CommandLineRunner loadData(DigitalSignature comp, MachineInfoReader machineInfoReader) {
		return args -> {
			MachineInfo mi = machineInfoReader.getMachineInfo();
			ObjectMapper mapper = new ObjectMapper();
			MachineSignature ms = mapper.readValue(Paths.get("data/machine-signature.json").toFile(),
					MachineSignature.class);
			String publicKeyPath = "keys/public.key";
			log.info(print("Operation System", comp.verify(mapper.writeValueAsString(mi.getOperationSystem()),
					ms.getOperationSystem(), publicKeyPath)));
			log.info(print("Baseboard",
					comp.verify(mapper.writeValueAsString(mi.getBaseboard()), ms.getBaseboard(), publicKeyPath)));
		};

	}

	private String print(String label, boolean verify) {
		return label + ": " + (verify ? "PASS" : "FAIL");
	}

}
