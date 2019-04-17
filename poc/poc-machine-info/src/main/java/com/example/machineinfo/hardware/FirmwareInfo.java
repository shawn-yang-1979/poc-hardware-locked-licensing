package com.example.machineinfo.hardware;

import lombok.Data;

@Data
public class FirmwareInfo {
	private String manufacturer;
	private String name;
	private String description;
	private String version;
	private String releaseDate;

}
