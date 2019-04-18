package com.example.digitalsignature.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.digitalsignature.DigitalSignature;

@Configuration
@ConditionalOnClass(DigitalSignature.class)
@ConditionalOnMissingBean(DigitalSignature.class)
@EnableConfigurationProperties(DigitalSignatureProperties.class)
public class DigitalSignatureAutoConfiguration {

	@Bean
	public DigitalSignature digitalSignature(DigitalSignatureProperties digitalSignatureProperties) {
		return new DigitalSignature(digitalSignatureProperties.getKeyFactoryAlgorithm(),
				digitalSignatureProperties.getSignatureAlgorithm());
	}
}
