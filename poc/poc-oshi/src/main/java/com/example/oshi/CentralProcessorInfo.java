package com.example.oshi;

import lombok.Builder;
import lombok.Getter;

/**
 * I exclude cpu64bit, it seems not correct on windows Intel64 CPU.
 * 
 * @author SHAWN.SH.YANG
 *
 */
@Builder
@Getter
public class CentralProcessorInfo {

	private String vendor;
	private String name;
	private long vendorFreq;
	private String processorId;
	private String identifier;
	private String stepping;
	private String model;
	private String family;
	private int logicalProcessorCount;
	private int physicalProcessorCount;

}
