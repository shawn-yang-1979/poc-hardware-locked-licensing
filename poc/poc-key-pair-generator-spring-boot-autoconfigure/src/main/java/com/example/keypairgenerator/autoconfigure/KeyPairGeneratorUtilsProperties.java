package com.example.keypairgenerator.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;
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
@ConfigurationProperties(prefix = "key-pair-generator")
public class KeyPairGeneratorUtilsProperties {

	private int keySize = 1024;
	/**
	 * Can be
	 * https://docs.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html#KeyPairGenerator
	 */
	private String algorithm = "RSA";
	private String privateKeyPath = "keys/private.key";
	private String publicKeyPath = "keys/public.key";

}
