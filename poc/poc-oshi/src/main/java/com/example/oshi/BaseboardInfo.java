package com.example.oshi;

import lombok.Builder;
import lombok.Getter;

/**
 * Exclude serialNumber, it may change if you use sudo
 * 
 * @author SHAWN.SH.YANG
 *
 */
@Builder
@Getter
public class BaseboardInfo {

	private String manufacturer;
	private String model;
	private String version;

}
