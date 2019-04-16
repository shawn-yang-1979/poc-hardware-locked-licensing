package com.example.oshi;

import java.net.SocketException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import oshi.SystemInfo;
import oshi.hardware.Baseboard;
import oshi.hardware.CentralProcessor;
import oshi.hardware.ComputerSystem;
import oshi.hardware.Firmware;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HWPartition;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.NetworkIF;
import oshi.software.os.FileSystem;
import oshi.software.os.NetworkParams;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;

/**
 * This code is copy from demo code of
 * https://github.com/oshi/oshi/blob/master/oshi-demo/src/main/java/oshi/demo/ComputerID.java
 * 
 * @author SHAWN.SH.YANG
 *
 */
@Component
public class MachineIdentifierComponent {
	public MachineIdentifierInfo getHardwareIdentifier() {
		SystemInfo systemInfo = new SystemInfo();
		// OS
		OperatingSystem os = systemInfo.getOperatingSystem();
		OperationSystemInfo osInfo = OperationSystemInfo.builder()//
				.bitness(os.getBitness())//
				.family(os.getFamily())//
				.manufacturer(os.getManufacturer())//
				.build();
		// get first OS file store with UUID
		FileSystem fs = os.getFileSystem();
		OSFileStore firstOsFileStore = null;
		for (OSFileStore ofs : fs.getFileStores()) {
			if (ofs.getUUID() != null) {
				firstOsFileStore = ofs;
				break;
			}
		}
		OsFileStoreInfo firstOsFileStoreInfo = null;
		if (firstOsFileStore != null) {
			firstOsFileStoreInfo = OsFileStoreInfo.builder()//
					.description(firstOsFileStore.getDescription())//
					.type(firstOsFileStore.getType())//
					.mount(firstOsFileStore.getMount())//
					.name(firstOsFileStore.getName())//
					.totalInodes(firstOsFileStore.getTotalInodes())//
					.totalSpace(firstOsFileStore.getTotalSpace())//
					.uuid(firstOsFileStore.getUUID())//
					.volume(firstOsFileStore.getVolume())//
					.build();
		}
		// OS networking
		NetworkParams nps = os.getNetworkParams();
		NetworkInfo networkInfo = NetworkInfo.builder()//
				.domainName(nps.getDomainName())//
				.hostName(nps.getHostName())//
				.build();

		HardwareAbstractionLayer hardware = systemInfo.getHardware();
		// CPU
		CentralProcessor centralProcessor = hardware.getProcessor();
		CentralProcessorInfo cpuInfo = CentralProcessorInfo.builder()//
				.vendor(centralProcessor.getVendor())//
				.name(centralProcessor.getName())//
				.vendorFreq(centralProcessor.getVendorFreq())//
				.processorId(centralProcessor.getProcessorID())//
				.identifier(centralProcessor.getIdentifier())//
				.stepping(centralProcessor.getStepping())//
				.model(centralProcessor.getModel())//
				.family(centralProcessor.getFamily())//
				.logicalProcessorCount(centralProcessor.getLogicalProcessorCount())//
				.physicalProcessorCount(centralProcessor.getPhysicalProcessorCount())//
				.build();
		// Memory
		GlobalMemory memory = hardware.getMemory();
		GlobalMemoryInfo memoryInfo = GlobalMemoryInfo.builder()//
				.total(memory.getTotal())//
				.pageSize(memory.getPageSize())//
				.build();
		// first physical disk and first partition
		HwDiskStoreInfo firstHwDiskStoreInfo = null;
		HwPartitionInfo firstHwPartitionInfo = null;
		HWDiskStore[] hwDiskStores = hardware.getDiskStores();
		for (HWDiskStore hwDiskStore : hwDiskStores) {
			for (HWPartition hwPartition : hwDiskStore.getPartitions()) {
				firstHwPartitionInfo = HwPartitionInfo.builder().identification(hwPartition.getIdentification())
						.major(hwPartition.getMajor()).minor(hwPartition.getMinor())
						.mountPoint(hwPartition.getMountPoint()).name(hwPartition.getName()).size(hwPartition.getSize())
						.type(hwPartition.getType()).uuid(hwPartition.getUuid()).build();
				if (firstHwPartitionInfo != null) {
					break;
				}
			}
			firstHwDiskStoreInfo = HwDiskStoreInfo.builder()//
					.model(hwDiskStore.getModel())//
					.name(hwDiskStore.getName())//
					.serial(hwDiskStore.getSerial())//
					.size(hwDiskStore.getSize())//
					.build();
			if (firstHwDiskStoreInfo != null) {
				break;
			}
		}
		// all network interface
		List<NetworkIfInfo> networkIfsInfo = new LinkedList<>();
		NetworkIF[] networkIfs = hardware.getNetworkIFs();
		for (NetworkIF nif : networkIfs) {
			boolean skip = false;
			try {
				if (nif.getNetworkInterface().isVirtual()//
						|| nif.getNetworkInterface().isLoopback()//
						|| nif.getNetworkInterface().isPointToPoint()) {
					skip = true;
				}
			} catch (SocketException e) {
				skip = true;
			}
			if (skip) {
				continue;
			}
			networkIfsInfo.add(NetworkIfInfo.builder()//
					.displayName(nif.getDisplayName())//
					.macAddr(nif.getMacaddr())//
					.mtu(nif.getMTU())//
					.name(nif.getName())//
					.build());
		}
		// computer system
		ComputerSystem computerSystem = hardware.getComputerSystem();
		ComputerSystemInfo computerSystemInfo = ComputerSystemInfo.builder()//
				.manufacturer(computerSystem.getManufacturer())//
				.model(computerSystem.getModel())//
				.build();
		Baseboard baseboard = computerSystem.getBaseboard();
		BaseboardInfo baseboardInfo = BaseboardInfo.builder()//
				.manufacturer(baseboard.getManufacturer())//
				.model(baseboard.getModel())//
				.version(baseboard.getVersion())//
				.build();
		Firmware firmware = computerSystem.getFirmware();
		FirmwareInfo firmwareInfo = FirmwareInfo.builder()//
				.description(firmware.getDescription())//
				.manufacturer(firmware.getManufacturer())//
				.name(firmware.getName())//
				.releaseDate(firmware.getReleaseDate())//
				.version(firmware.getVersion())//
				.build();
		return MachineIdentifierInfo.builder()//
				.operationSystem(osInfo)//
				.firstOsFileStore(firstOsFileStoreInfo)//
				.network(networkInfo)//
				.centralProcessor(cpuInfo)//
				.memory(memoryInfo)//
				.firstHwDiskStore(firstHwDiskStoreInfo)//
				.firstHwPartition(firstHwPartitionInfo)//
				.networkIfs(networkIfsInfo)//
				.computerSystem(computerSystemInfo)//
				.baseboard(baseboardInfo)//
				.firmware(firmwareInfo)//
				.build();
	}
}