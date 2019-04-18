package com.example.machineinfo.signer;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;

import com.example.digitalsignature.DigitalSignature;
import com.example.machineinfo.MachineInfo;
import com.example.machineinfo.MachineSignature;
import com.example.machineinfo.plaintextmapper.MachineInfoPlainTextMapper;

public class MachineInfoSigner {
	public MachineInfoSigner(DigitalSignature digitalSignature, String privateKeyPath) {
		super();
		this.digitalSignature = digitalSignature;
		this.privateKeyPath = privateKeyPath;
	}

	private DigitalSignature digitalSignature;
	private String privateKeyPath;
	private MachineInfoPlainTextMapper mapper = new MachineInfoPlainTextMapper();

	public MachineSignature sign(MachineInfo info) throws InvalidKeyException, NoSuchAlgorithmException,
			InvalidKeySpecException, SignatureException, IOException {
		MachineSignature src = mapper.map(info);
		MachineSignature dist = new MachineSignature();
		dist.setOperationSystem(digitalSignature.sign(src.getOperationSystem(), privateKeyPath));
		dist.setFirstOsFileStore(digitalSignature.sign(src.getFirstOsFileStore(), privateKeyPath));
		dist.setNetwork(digitalSignature.sign(src.getNetwork(), privateKeyPath));
		dist.setCentralProcessor(digitalSignature.sign(src.getCentralProcessor(), privateKeyPath));
		dist.setMemory(digitalSignature.sign(src.getMemory(), privateKeyPath));
		dist.setFirstHwDiskStore(digitalSignature.sign(src.getFirstHwDiskStore(), privateKeyPath));
		dist.setFirstHwPartition(digitalSignature.sign(src.getFirstHwPartition(), privateKeyPath));
		dist.setComputerSystem(digitalSignature.sign(src.getComputerSystem(), privateKeyPath));
		dist.setBaseboard(digitalSignature.sign(src.getBaseboard(), privateKeyPath));
		dist.setFirmware(digitalSignature.sign(src.getFirmware(), privateKeyPath));
		for (String text : src.getNetworkIfs()) {
			dist.getNetworkIfs().add(digitalSignature.sign(text, privateKeyPath));
		}
		return dist;
	}
}
