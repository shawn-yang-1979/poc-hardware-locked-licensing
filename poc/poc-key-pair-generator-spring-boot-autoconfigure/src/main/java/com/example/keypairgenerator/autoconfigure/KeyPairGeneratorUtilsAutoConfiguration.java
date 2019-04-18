package com.example.keypairgenerator.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.keypairgenerator.KeyPairGeneratorUtils;

@Configuration
@ConditionalOnClass(KeyPairGeneratorUtils.class)
@ConditionalOnMissingBean(KeyPairGeneratorUtils.class)
@EnableConfigurationProperties(KeyPairGeneratorUtilsProperties.class)
public class KeyPairGeneratorUtilsAutoConfiguration {

	@Bean
	public KeyPairGeneratorUtils keyPairGeneratorUtils(
			KeyPairGeneratorUtilsProperties keyPairGeneratorUtilsProperties) {
		return new KeyPairGeneratorUtils(//
				keyPairGeneratorUtilsProperties.getKeySize(), //
				keyPairGeneratorUtilsProperties.getAlgorithm(), //
				keyPairGeneratorUtilsProperties.getPrivateKeyPath(), //
				keyPairGeneratorUtilsProperties.getPublicKeyPath());
	}
}
