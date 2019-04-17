package com.example.machineinfo.hardware;

import lombok.Data;

@Data
public class HwDiskStoreInfo {

	private String name; // ex: \\.\PHYSICALDRIVE0
	private String model; // ex: TOSHIBA MQ01ACF050 (Standard disk drives)
	private String serial; // ex: 16MHCSMET
	private long size; // ex: 500105249280

}
