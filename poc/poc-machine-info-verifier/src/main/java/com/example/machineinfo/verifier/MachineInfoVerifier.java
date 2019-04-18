package com.example.machineinfo.verifier;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.example.digitalsignature.DigitalSignature;
import com.example.machineinfo.MachineInfo;
import com.example.machineinfo.MachineSignature;
import com.example.machineinfo.plaintextmapper.MachineInfoPlainTextMapper;

public class MachineInfoVerifier {

	public MachineInfoVerifier(DigitalSignature digitalSignature, String publicKeyPath) {
		super();
		this.digitalSignature = digitalSignature;
		this.publicKeyPath = publicKeyPath;
	}

	private DigitalSignature digitalSignature;
	private String publicKeyPath;
	private MachineInfoPlainTextMapper mapper = new MachineInfoPlainTextMapper();

	/**
	 * Return a verify fail list
	 * 
	 * @param fingerPrint
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws SignatureException
	 * @throws IOException
	 */
	public Map<String, String> verify(MachineInfo rawInfo, MachineSignature fingerPrint) throws InvalidKeyException,
			NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException {
		Map<String, String> fails = new LinkedHashMap<>();
		MachineSignature plainText = mapper.map(rawInfo);
		if (!digitalSignature.verify(plainText.getOperationSystem(), fingerPrint.getOperationSystem(), publicKeyPath)) {
			fails.put(fingerPrint.getOperationSystem(), "OperationSystem");
		}
		if (!digitalSignature.verify(plainText.getFirstOsFileStore(), fingerPrint.getFirstOsFileStore(),
				publicKeyPath)) {
			fails.put(fingerPrint.getFirstOsFileStore(), "FirstOsFileStore");
		}
		if (!digitalSignature.verify(plainText.getNetwork(), fingerPrint.getNetwork(), publicKeyPath)) {
			fails.put(fingerPrint.getNetwork(), "Network");
		}
		if (!digitalSignature.verify(plainText.getCentralProcessor(), fingerPrint.getCentralProcessor(),
				publicKeyPath)) {
			fails.put(fingerPrint.getCentralProcessor(), "CentralProcessor");
		}
		if (!digitalSignature.verify(plainText.getMemory(), fingerPrint.getMemory(), publicKeyPath)) {
			fails.put(fingerPrint.getMemory(), "Memory");
		}
		if (!digitalSignature.verify(plainText.getFirstHwDiskStore(), fingerPrint.getFirstHwDiskStore(),
				publicKeyPath)) {
			fails.put(fingerPrint.getFirstHwDiskStore(), "FirstHwDiskStore");
		}
		if (!digitalSignature.verify(plainText.getFirstHwPartition(), fingerPrint.getFirstHwPartition(),
				publicKeyPath)) {
			fails.put(fingerPrint.getFirstHwPartition(), "FirstHwPartition");
		}
		if (!digitalSignature.verify(plainText.getComputerSystem(), fingerPrint.getComputerSystem(), publicKeyPath)) {
			fails.put(fingerPrint.getComputerSystem(), "ComputerSystem");
		}
		if (!digitalSignature.verify(plainText.getBaseboard(), fingerPrint.getBaseboard(), publicKeyPath)) {
			fails.put(fingerPrint.getBaseboard(), "Baseboard");
		}
		if (!digitalSignature.verify(plainText.getFirmware(), fingerPrint.getFirmware(), publicKeyPath)) {
			fails.put(fingerPrint.getFirmware(), "Firmware");
		}
		for (String netIfFingerPrint : fingerPrint.getNetworkIfs()) {
			if (!isFoundVerifiedNetworkIf(netIfFingerPrint, plainText.getNetworkIfs())) {
				fails.put(netIfFingerPrint, "NetworkIf");
			}
		}
		return fails;
	}

	private boolean isFoundVerifiedNetworkIf(String netIfFingerPrint, List<String> networkIfs)
			throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException, IOException,
			SignatureException {
		for (String netIfPlantText : networkIfs) {
			if (digitalSignature.verify(netIfPlantText, netIfFingerPrint, publicKeyPath)) {
				return true;
			}
		}
		return false;
	}
}
