package com.example.oshi;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ComputerIdentifier {

	private String vendor;
	private String processorSerialNumber;
	private String processorIdentifier;
	private int processors;

}
