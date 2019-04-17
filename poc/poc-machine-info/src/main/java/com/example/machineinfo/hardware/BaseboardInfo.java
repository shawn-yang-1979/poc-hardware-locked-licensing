package com.example.machineinfo.hardware;

import lombok.Data;

/**
 * Exclude serialNumber, it may change if you use sudo
 * 
 * @author SHAWN.SH.YANG
 *
 */
@Data
public class BaseboardInfo {

	private String manufacturer;
	private String model;
	private String version;

}
