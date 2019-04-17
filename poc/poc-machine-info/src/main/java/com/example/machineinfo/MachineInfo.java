package com.example.machineinfo;

import java.util.LinkedList;
import java.util.List;

import com.example.machineinfo.hardware.BaseboardInfo;
import com.example.machineinfo.hardware.CentralProcessorInfo;
import com.example.machineinfo.hardware.ComputerSystemInfo;
import com.example.machineinfo.hardware.FirmwareInfo;
import com.example.machineinfo.hardware.GlobalMemoryInfo;
import com.example.machineinfo.hardware.HwDiskStoreInfo;
import com.example.machineinfo.hardware.HwPartitionInfo;
import com.example.machineinfo.hardware.NetworkIfInfo;
import com.example.machineinfo.os.NetworkInfo;
import com.example.machineinfo.os.OperationSystemInfo;
import com.example.machineinfo.os.OsFileStoreInfo;

import lombok.Data;

@Data
public class MachineInfo {

	private OperationSystemInfo operationSystem;
	private OsFileStoreInfo firstOsFileStore;
	private NetworkInfo network;
	private CentralProcessorInfo centralProcessor;
	private GlobalMemoryInfo memory;
	private HwDiskStoreInfo firstHwDiskStore;
	private HwPartitionInfo firstHwPartition;
	private List<NetworkIfInfo> networkIfs = new LinkedList<>();
	private ComputerSystemInfo computerSystem;
	private BaseboardInfo baseboard;
	private FirmwareInfo firmware;

}
