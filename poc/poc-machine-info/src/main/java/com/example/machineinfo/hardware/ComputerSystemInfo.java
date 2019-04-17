package com.example.machineinfo.hardware;

import lombok.Data;

/**
 * Exclude serialNumber, it may change if you use sudo
 * 
 * @author SHAWN.SH.YANG
 *
 */
@Data
public class ComputerSystemInfo {

	private String manufacturer;
	private String model;
}
