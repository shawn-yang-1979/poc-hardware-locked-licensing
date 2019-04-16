package com.example.oshi;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class HwPartitionInfo {

	private String identification; // ex: /dev/sda1
	private String name; // ex: sda1
	private String type; // ex: ext4
	private String uuid; // ex: 73e9659d-2fd9-46ca-a341-5a8637c416ee
	private long size; // ex: 10736352768
	private int major; // ex: 8
	private int minor; // ex: 1
	private String mountPoint; // ex: /

}
