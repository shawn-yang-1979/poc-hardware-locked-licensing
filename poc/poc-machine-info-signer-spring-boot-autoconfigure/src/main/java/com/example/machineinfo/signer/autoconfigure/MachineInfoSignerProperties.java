package com.example.machineinfo.signer.autoconfigure;

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
@ConfigurationProperties(prefix = "machine-info-signer")
public class MachineInfoSignerProperties {

	private String privateKeyPath = "keys/private.key";

}
