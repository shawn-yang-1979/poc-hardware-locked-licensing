package com.example.machineinfo;

import java.util.LinkedList;
import java.util.List;

import lombok.Data;

@Data
public class MachineSignature {

	private String operationSystem;
	private String firstOsFileStore;
	private String network;
	private String centralProcessor;
	private String memory;
	private String firstHwDiskStore;
	private String firstHwPartition;
	private List<String> networkIfs = new LinkedList<>();
	private String computerSystem;
	private String baseboard;
	private String firmware;

}
