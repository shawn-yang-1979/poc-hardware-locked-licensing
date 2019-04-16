package com.example.oshi;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FirmwareInfo {
	private String manufacturer;
	private String name;
	private String description;
	private String version;
	private String releaseDate;

}
