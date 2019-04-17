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
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Component
@ConfigurationProperties(prefix = "key-pair-generator")
public class KeyPairGeneratorComponent {

	private int keySize = 1024;
	/**
	 * Can be
	 * https://docs.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html#KeyPairGenerator
	 */
	private String algorithm = "RSA";

	/**
	 * Create key pair, but when the key pair exists, an exception is thrown.
	 * 
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 */
	public void createKeys() throws NoSuchAlgorithmException, IOException {
		Path privateKeyPath = Paths.get("keys/private.key");
		if (privateKeyPath.toFile().exists()) {
			throw new FileAlreadyExistsException(privateKeyPath.toString());
		}
		Path publicKeyPath = Paths.get("keys/public.key");
		if (publicKeyPath.toFile().exists()) {
			throw new FileAlreadyExistsException(publicKeyPath.toString());
		}
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance(algorithm);
		keyGen.initialize(keySize);
		KeyPair pair = keyGen.generateKeyPair();
		PrivateKey privateKey = pair.getPrivate();
		PublicKey publicKey = pair.getPublic();
		log.info("private.key format=" + privateKey.getFormat());
		log.info("public.key  format=" + publicKey.getFormat());
		FileUtils.writeByteArrayToFile(privateKeyPath.toFile(), privateKey.getEncoded());
		FileUtils.writeByteArrayToFile(publicKeyPath.toFile(), publicKey.getEncoded());
	}

}
