package com.example.oshi;

import lombok.Builder;
import lombok.Getter;

/**
 * Exclude serialNumber, it may change if you use sudo
 * 
 * @author SHAWN.SH.YANG
 *
 */
@Getter
@Builder
public class ComputerSystemInfo {

	private String manufacturer;
	private String model;
}
