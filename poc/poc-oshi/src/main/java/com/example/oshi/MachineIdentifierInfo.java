package com.example.oshi;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MachineIdentifierInfo {

	private OperationSystemInfo operationSystem;
	private OsFileStoreInfo firstOsFileStore;
	private NetworkInfo network;
	private CentralProcessorInfo centralProcessor;
	private GlobalMemoryInfo memory;
	private HwDiskStoreInfo firstHwDiskStore;
	private HwPartitionInfo firstHwPartition;
	private List<NetworkIfInfo> networkIfs;
	private ComputerSystemInfo computerSystem;
	private BaseboardInfo baseboard;
	private FirmwareInfo firmware;

}
