package com.example.oshi;

import org.springframework.stereotype.Component;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.ComputerSystem;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;

/**
 * This code is copy from demo code of
 * https://github.com/oshi/oshi/blob/master/oshi-demo/src/main/java/oshi/demo/ComputerID.java
 * 
 * @author SHAWN.SH.YANG
 *
 */
@Component
public class ComputerIdentifierComponent {
	public ComputerIdentifier getComputerIdentifier() {
		SystemInfo systemInfo = new SystemInfo();
		OperatingSystem operatingSystem = systemInfo.getOperatingSystem();
		HardwareAbstractionLayer hardwareAbstractionLayer = systemInfo.getHardware();
		CentralProcessor centralProcessor = hardwareAbstractionLayer.getProcessor();
		ComputerSystem computerSystem = hardwareAbstractionLayer.getComputerSystem();
		String vendor = operatingSystem.getManufacturer();
		String processorSerialNumber = computerSystem.getSerialNumber();
		String processorIdentifier = centralProcessor.getIdentifier();
		int processors = centralProcessor.getLogicalProcessorCount();
		return ComputerIdentifier.builder()//
				.vendor(vendor)//
				.processorSerialNumber(processorSerialNumber)//
				.processorIdentifier(processorIdentifier)//
				.processors(processors)//
				.build();
	}
}