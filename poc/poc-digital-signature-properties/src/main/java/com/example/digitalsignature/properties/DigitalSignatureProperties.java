package com.example.digitalsignature.properties;

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
@ConfigurationProperties(prefix = "digital-signature")
public class DigitalSignatureProperties {

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

}
