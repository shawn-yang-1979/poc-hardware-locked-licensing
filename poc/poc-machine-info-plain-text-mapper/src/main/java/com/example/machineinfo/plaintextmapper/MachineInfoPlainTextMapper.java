package com.example.machineinfo.plaintextmapper;

import com.example.machineinfo.MachineInfo;
import com.example.machineinfo.MachineSignature;
import com.example.machineinfo.hardware.NetworkIfInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MachineInfoPlainTextMapper {

	private ObjectMapper mapper = new ObjectMapper();

	public MachineSignature map(MachineInfo src) throws JsonProcessingException {
		MachineSignature dist = new MachineSignature();
		dist.setOperationSystem(mapper.writeValueAsString(src.getOperationSystem()));
		dist.setFirstOsFileStore(mapper.writeValueAsString(src.getFirstOsFileStore()));
		dist.setNetwork(mapper.writeValueAsString(src.getNetwork()));
		dist.setCentralProcessor(mapper.writeValueAsString(src.getCentralProcessor()));
		dist.setMemory(mapper.writeValueAsString(src.getMemory()));
		dist.setFirstHwDiskStore(mapper.writeValueAsString(src.getFirstHwDiskStore()));
		dist.setFirstHwPartition(mapper.writeValueAsString(src.getFirstHwPartition()));
		dist.setComputerSystem(mapper.writeValueAsString(src.getComputerSystem()));
		dist.setBaseboard(mapper.writeValueAsString(src.getBaseboard()));
		dist.setFirmware(mapper.writeValueAsString(src.getFirmware()));
		for (NetworkIfInfo networkIf : src.getNetworkIfs()) {
			dist.getNetworkIfs().add(mapper.writeValueAsString(networkIf));
		}
		return dist;
	}

}
