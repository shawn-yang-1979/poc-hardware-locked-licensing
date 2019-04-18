package com.example.machineinfo.verifier.autoconfigure;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.digitalsignature.DigitalSignature;
import com.example.machineinfo.verifier.MachineInfoVerifier;

@Configuration
@AutoConfigureAfter(DigitalSignature.class)
@ConditionalOnClass(MachineInfoVerifier.class)
@ConditionalOnMissingBean(MachineInfoVerifier.class)
@EnableConfigurationProperties(MachineInfoVerifierProperties.class)
public class MachineInfoVerifierAutoConfiguration {

	@Bean
	public MachineInfoVerifier machineInfoVerifier(//
			DigitalSignature digitalSignature, //
			MachineInfoVerifierProperties machineInfoSignerProperties) {
		return new MachineInfoVerifier(digitalSignature, machineInfoSignerProperties.getPublicKeyPath());
	}
}
