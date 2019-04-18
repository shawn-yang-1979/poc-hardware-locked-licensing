package com.example.machineinfo.verifier.autoconfigure;

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
@ConfigurationProperties(prefix = "machine-info-verifier")
public class MachineInfoVerifierProperties {

	private String publicKeyPath = "keys/public.key";

}
