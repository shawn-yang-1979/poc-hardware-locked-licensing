package com.example.machineinfo.hardware;

import lombok.Data;

/**
 * Exclude cpu64bit, it seems not correct on windows Intel64 CPU.
 * 
 * Exclude processorId, it may different with or without sudo.
 * 
 * @author SHAWN.SH.YANG
 *
 */
@Data
public class CentralProcessorInfo {

	private String vendor;
	private String name;
	private long vendorFreq;
	private String identifier;
	private String stepping;
	private String model;
	private String family;
	private int logicalProcessorCount;
	private int physicalProcessorCount;

}
