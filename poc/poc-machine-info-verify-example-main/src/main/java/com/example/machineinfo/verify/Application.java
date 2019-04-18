package com.example.machineinfo.verify;

import java.nio.file.Paths;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.example.digitalsignature.DigitalSignature;
import com.example.machineinfo.MachineInfo;
import com.example.machineinfo.MachineSignature;
import com.example.machineinfo.reader.MachineInfoReader;
import com.example.machineinfo.verifier.MachineInfoVerifier;
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
	public MachineInfoVerifier machineInfoVerifier(DigitalSignature digitalSignature) {
		return new MachineInfoVerifier(digitalSignature, "keys/public.key");
	}

	@Component
	public class Runner implements CommandLineRunner {

		@Autowired
		private MachineInfoVerifier machineInfoVerifier;
		@Autowired
		private MachineInfoReader machineInfoReader;
		private ObjectMapper mapper = new ObjectMapper();

		@Override
		public void run(String... args) throws Exception {
			MachineInfo mi = machineInfoReader.getMachineInfo();
			MachineSignature ms = mapper.readValue(Paths.get("data/machine-signature.json").toFile(),
					MachineSignature.class);
			Map<String, String> fails = machineInfoVerifier.verify(mi, ms);
			log.info("fails\n" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(fails));
		}
	}

}
