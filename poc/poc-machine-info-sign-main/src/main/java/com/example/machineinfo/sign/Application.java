package com.example.machineinfo.sign;

import java.nio.file.Paths;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.digitalsignature.EnableDigitalSignature;
import com.example.digitalsignature.sign.SignComponent;
import com.example.machineinfo.MachineInfo;
import com.example.machineinfo.MachineSignature;
import com.example.machineinfo.hardware.NetworkIfInfo;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
@EnableDigitalSignature
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}

	@Bean
	public CommandLineRunner loadData(SignComponent comp) {
		return args -> {
			ObjectMapper mapper = new ObjectMapper();
			MachineInfo machineInfo = mapper.readValue(Paths.get("data/machine-info.json").toFile(), MachineInfo.class);

			MachineSignature beforeSign = new MachineSignature();
			beforeSign.setOperationSystem(mapper.writeValueAsString(machineInfo.getOperationSystem()));
			beforeSign.setFirstOsFileStore(mapper.writeValueAsString(machineInfo.getFirstOsFileStore()));
			beforeSign.setNetwork(mapper.writeValueAsString(machineInfo.getNetwork()));
			beforeSign.setCentralProcessor(mapper.writeValueAsString(machineInfo.getCentralProcessor()));
			beforeSign.setMemory(mapper.writeValueAsString(machineInfo.getMemory()));
			beforeSign.setFirstHwDiskStore(mapper.writeValueAsString(machineInfo.getFirstHwDiskStore()));
			beforeSign.setFirstHwPartition(mapper.writeValueAsString(machineInfo.getFirstHwPartition()));
			beforeSign.setComputerSystem(mapper.writeValueAsString(machineInfo.getComputerSystem()));
			beforeSign.setBaseboard(mapper.writeValueAsString(machineInfo.getBaseboard()));
			beforeSign.setFirmware(mapper.writeValueAsString(machineInfo.getFirmware()));
			for (NetworkIfInfo networkIf : machineInfo.getNetworkIfs()) {
				beforeSign.getNetworkIfs().add(mapper.writeValueAsString(networkIf));
			}

			String privateKeyPath = "keys/private.key";
			MachineSignature afterSign = new MachineSignature();
			afterSign.setOperationSystem(comp.sign(beforeSign.getOperationSystem(), privateKeyPath));
			afterSign.setFirstOsFileStore(comp.sign(beforeSign.getFirstOsFileStore(), privateKeyPath));
			afterSign.setNetwork(comp.sign(beforeSign.getNetwork(), privateKeyPath));
			afterSign.setCentralProcessor(comp.sign(beforeSign.getCentralProcessor(), privateKeyPath));
			afterSign.setMemory(comp.sign(beforeSign.getMemory(), privateKeyPath));
			afterSign.setFirstHwDiskStore(comp.sign(beforeSign.getFirstHwDiskStore(), privateKeyPath));
			afterSign.setFirstHwPartition(comp.sign(beforeSign.getFirstHwPartition(), privateKeyPath));
			afterSign.setComputerSystem(comp.sign(beforeSign.getComputerSystem(), privateKeyPath));
			afterSign.setBaseboard(comp.sign(beforeSign.getBaseboard(), privateKeyPath));
			afterSign.setFirmware(comp.sign(beforeSign.getFirmware(), privateKeyPath));
			for (String text : beforeSign.getNetworkIfs()) {
				afterSign.getNetworkIfs().add(comp.sign(text, privateKeyPath));
			}

			mapper//
					.writerWithDefaultPrettyPrinter()//
					.writeValue(Paths.get("data/machine-signature.json").toFile(), afterSign);
		};
	}

}
