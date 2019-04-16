package com.example.oshi;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class HwDiskStoreInfo {

	private String name; // ex: \\.\PHYSICALDRIVE0
	private String model; // ex: TOSHIBA MQ01ACF050 (Standard disk drives)
	private String serial; // ex: 16MHCSMET
	private long size; // ex: 500105249280

}
