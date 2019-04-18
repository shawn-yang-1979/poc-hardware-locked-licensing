package com.example.machineinfo.signer.autoconfigure;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.digitalsignature.DigitalSignature;
import com.example.machineinfo.signer.MachineInfoSigner;

@Configuration
@AutoConfigureAfter(DigitalSignature.class)
@ConditionalOnClass(MachineInfoSigner.class)
@ConditionalOnMissingBean(MachineInfoSigner.class)
@EnableConfigurationProperties(MachineInfoSignerProperties.class)
public class MachineInfoSignerAutoConfiguration {

	@Bean
	public MachineInfoSigner machineInfoSigner(//
			DigitalSignature digitalSignature, //
			MachineInfoSignerProperties machineInfoSignerProperties) {
		return new MachineInfoSigner(digitalSignature, machineInfoSignerProperties.getPrivateKeyPath());
	}
}
