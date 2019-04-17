package com.example.machineinfo.reader;

import java.net.SocketException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.machineinfo.MachineInfo;
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

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class MachineInfoReader {
	public MachineInfo getMachineInfo() {
		SystemInfo systemInfo = new SystemInfo();
		// OS
		OperatingSystem os = systemInfo.getOperatingSystem();
		OperationSystemInfo osInfo = map(os);
		// get first OS file store with UUID
		Optional<OSFileStore> firstOsFileStoreOptional = Arrays.asList(os.getFileSystem().getFileStores()).stream()
				.filter(ofs -> ofs.getUUID() != null)//
				.findFirst();
		OsFileStoreInfo firstOsFileStoreInfo = null;
		if (firstOsFileStoreOptional.isPresent()) {
			firstOsFileStoreInfo = map(firstOsFileStoreOptional.get());
		}
		// OS networking
		NetworkInfo networkInfo = map(os.getNetworkParams());
		HardwareAbstractionLayer hardware = systemInfo.getHardware();
		// CPU
		CentralProcessorInfo cpuInfo = map(hardware.getProcessor());
		// Memory
		GlobalMemoryInfo memoryInfo = map(hardware.getMemory());
		// first physical disk
		HwDiskStoreInfo firstHwDiskStoreInfo = null;
		Optional<HWDiskStore> optionalHWDiskStore = Arrays.asList(hardware.getDiskStores()).stream()//
				.findFirst();
		if (optionalHWDiskStore.isPresent()) {
			firstHwDiskStoreInfo = map(optionalHWDiskStore.get());
		}
		// first partition
		HwPartitionInfo firstHwPartitionInfo = null;
		Optional<HWPartition> optionalHWPartition = Arrays.asList(hardware.getDiskStores()).stream()
				.flatMap(diskStore -> Arrays.asList(diskStore.getPartitions()).stream())//
				.findFirst();
		if (optionalHWPartition.isPresent()) {
			firstHwPartitionInfo = map(optionalHWPartition.get());
		}
		// all network interface
		List<NetworkIfInfo> networkIfsInfo = Arrays.asList(hardware.getNetworkIFs()).stream()//
				.filter(nif -> !ignore(nif))//
				.map(this::map)//
				.collect(Collectors.toList());
		ComputerSystem computerSystem = hardware.getComputerSystem();
		// computer system
		ComputerSystemInfo computerSystemInfo = map(computerSystem);
		// baseboard
		BaseboardInfo baseboardInfo = map(computerSystem.getBaseboard());
		// firmware
		FirmwareInfo firmwareInfo = map(computerSystem.getFirmware());
		MachineInfo result = new MachineInfo();
		result.setOperationSystem(osInfo);
		result.setFirstOsFileStore(firstOsFileStoreInfo);
		result.setNetwork(networkInfo);
		result.setCentralProcessor(cpuInfo);
		result.setMemory(memoryInfo);
		result.setFirstHwDiskStore(firstHwDiskStoreInfo);
		result.setFirstHwPartition(firstHwPartitionInfo);
		result.setNetworkIfs(networkIfsInfo);
		result.setComputerSystem(computerSystemInfo);
		result.setBaseboard(baseboardInfo);
		result.setFirmware(firmwareInfo);
		return result;
	}

	private boolean ignore(NetworkIF nif) {
		try {
			if (nif.getNetworkInterface().isVirtual()//
					|| nif.getNetworkInterface().isLoopback()//
					|| nif.getNetworkInterface().isPointToPoint()) {
				return true;
			}
		} catch (SocketException e) {
			log.error(e.getMessage(), e);
			return true;
		}
		return false;
	}

	private FirmwareInfo map(Firmware firmware) {
		FirmwareInfo result = new FirmwareInfo();
		result.setDescription(firmware.getDescription());
		result.setManufacturer(firmware.getManufacturer());
		result.setName(firmware.getName());
		result.setReleaseDate(firmware.getReleaseDate());
		result.setVersion(firmware.getVersion());
		return result;
	}

	private BaseboardInfo map(Baseboard baseboard) {
		BaseboardInfo result = new BaseboardInfo();
		result.setManufacturer(baseboard.getManufacturer());
		result.setModel(baseboard.getModel());
		result.setVersion(baseboard.getVersion());
		return result;
	}

	private ComputerSystemInfo map(ComputerSystem computerSystem) {
		ComputerSystemInfo result = new ComputerSystemInfo();
		result.setManufacturer(computerSystem.getManufacturer());
		result.setModel(computerSystem.getModel());
		return result;
	}

	private NetworkIfInfo map(NetworkIF nif) {
		NetworkIfInfo result = new NetworkIfInfo();
		result.setDisplayName(nif.getDisplayName());
		result.setMacAddr(nif.getMacaddr());
		result.setMtu(nif.getMTU());
		result.setName(nif.getName());
		return result;
	}

	private HwDiskStoreInfo map(HWDiskStore hwDiskStore) {
		HwDiskStoreInfo result = new HwDiskStoreInfo();
		result.setModel(hwDiskStore.getModel());
		result.setName(hwDiskStore.getName());
		result.setSerial(hwDiskStore.getSerial());
		result.setSize(hwDiskStore.getSize());
		return result;
	}

	private HwPartitionInfo map(HWPartition hwPartition) {
		HwPartitionInfo result = new HwPartitionInfo();
		result.setIdentification(hwPartition.getIdentification());
		result.setMajor(hwPartition.getMajor());
		result.setMinor(hwPartition.getMinor());
		result.setMountPoint(hwPartition.getMountPoint());
		result.setName(hwPartition.getName());
		result.setSize(hwPartition.getSize());
		result.setType(hwPartition.getType());
		result.setUuid(hwPartition.getUuid());
		return result;
	}

	private GlobalMemoryInfo map(GlobalMemory memory) {
		GlobalMemoryInfo result = new GlobalMemoryInfo();
		result.setTotal(memory.getTotal());
		result.setPageSize(memory.getPageSize());
		return result;
	}

	private CentralProcessorInfo map(CentralProcessor centralProcessor) {
		CentralProcessorInfo result = new CentralProcessorInfo();
		result.setVendor(centralProcessor.getVendor());
		result.setName(centralProcessor.getName());
		result.setVendorFreq(centralProcessor.getVendorFreq());
		result.setIdentifier(centralProcessor.getIdentifier());
		result.setStepping(centralProcessor.getStepping());
		result.setModel(centralProcessor.getModel());
		result.setFamily(centralProcessor.getFamily());
		result.setLogicalProcessorCount(centralProcessor.getLogicalProcessorCount());
		result.setPhysicalProcessorCount(centralProcessor.getPhysicalProcessorCount());
		return result;
	}

	private NetworkInfo map(NetworkParams nps) {
		NetworkInfo result = new NetworkInfo();
		result.setDomainName(nps.getDomainName());
		result.setHostName(nps.getHostName());
		return result;
	}

	private OsFileStoreInfo map(OSFileStore firstOsFileStore) {
		OsFileStoreInfo result = new OsFileStoreInfo();
		result.setDescription(firstOsFileStore.getDescription());
		result.setType(firstOsFileStore.getType());
		result.setMount(firstOsFileStore.getMount());
		result.setName(firstOsFileStore.getName());
		result.setTotalInodes(firstOsFileStore.getTotalInodes());
		result.setTotalSpace(firstOsFileStore.getTotalSpace());
		result.setUuid(firstOsFileStore.getUUID());
		result.setVolume(firstOsFileStore.getVolume());
		return result;
	}

	private OperationSystemInfo map(OperatingSystem os) {
		OperationSystemInfo result = new OperationSystemInfo();
		result.setBitness(os.getBitness());
		result.setFamily(os.getFamily());
		result.setManufacturer(os.getManufacturer());
		return result;
	}
}