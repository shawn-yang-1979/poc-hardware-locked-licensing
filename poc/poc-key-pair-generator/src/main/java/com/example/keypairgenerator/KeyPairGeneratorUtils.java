package com.example.keypairgenerator;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import org.apache.commons.io.FileUtils;

public class KeyPairGeneratorUtils {

	/**
	 * Algorithm can be
	 * https://docs.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html#KeyPairGenerator
	 */
	public KeyPairGeneratorUtils(int keySize, String algorithm, String privateKeyPath, String publicKeyPath) {
		super();
		this.keySize = keySize;
		this.algorithm = algorithm;
		this.privateKeyPath = privateKeyPath;
		this.publicKeyPath = publicKeyPath;
	}

	private int keySize;
	private String algorithm;
	private String privateKeyPath;
	private String publicKeyPath;

	/**
	 * Create key pair, but when the key pair exists, an exception is thrown.
	 * 
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 */
	public void generateKeyPair() throws NoSuchAlgorithmException, IOException {
		Path priKeyPath = Paths.get(privateKeyPath);
		if (priKeyPath.toFile().exists()) {
			throw new FileAlreadyExistsException(privateKeyPath);
		}
		Path pubKeyPath = Paths.get(publicKeyPath);
		if (pubKeyPath.toFile().exists()) {
			throw new FileAlreadyExistsException(publicKeyPath);
		}
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance(algorithm);
		keyGen.initialize(keySize);
		KeyPair pair = keyGen.generateKeyPair();
		PrivateKey privateKey = pair.getPrivate();
		PublicKey publicKey = pair.getPublic();
		FileUtils.writeByteArrayToFile(priKeyPath.toFile(), privateKey.getEncoded());
		FileUtils.writeByteArrayToFile(pubKeyPath.toFile(), publicKey.getEncoded());
	}

}
