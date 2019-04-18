package com.example.machineinfo.verify;

import java.io.File;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * This example copy from:
 * https://www.mkyong.com/java/java-digital-signatures-example/
 * 
 * @author SHAWN.SH.YANG
 *
 */
@Data
@Component
public class DigitalVerifyComponent {

	/**
	 * Can be
	 * https://docs.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html#KeyFactory
	 */
	private String keyFactoryAlgorithm = "RSA";

	/**
	 * Can be
	 * https://docs.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html#Signature
	 */
	private String signatureAlgorithm = "SHA1withRSA";

	public boolean verify(String data, String signature, String publicKeyFile) throws Exception {
		Signature sig = Signature.getInstance(signatureAlgorithm);
		sig.initVerify(getPublicKey(publicKeyFile));
		sig.update(data.getBytes());
		return sig.verify(Base64.getDecoder().decode(signature));
	}

	// Method to retrieve the Public Key from a file
	public PublicKey getPublicKey(String filename) throws Exception {
		byte[] keyBytes = Files.readAllBytes(new File(filename).toPath());
		X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
		KeyFactory kf = KeyFactory.getInstance(keyFactoryAlgorithm);
		return kf.generatePublic(spec);
	}

}
