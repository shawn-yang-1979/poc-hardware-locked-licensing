package com.example.machineinfo.reader.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.machineinfo.reader.MachineInfoReader;

@Configuration
@ConditionalOnClass(MachineInfoReader.class)
@ConditionalOnMissingBean(MachineInfoReader.class)
public class MachineInfoReaderAutoConfiguration {

	@Bean
	public MachineInfoReader machineInfoReader() {
		return new MachineInfoReader();
	}
}
