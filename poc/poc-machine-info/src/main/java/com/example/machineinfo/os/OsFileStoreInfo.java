package com.example.machineinfo.os;

import lombok.Data;

/**
 * Exclude logicalVolume, usableSpace, freeInodes since it may change.
 * 
 * @author SHAWN.SH.YANG
 *
 */
@Data
public class OsFileStoreInfo {

	private String name; // ex: Local Fixed Disk (C:), /
	private String volume; // ex: \\?\Volume{2b4df4f1-0000-0000-0000-100000000000}\, /dev/sda1
	private String mount;// ex: C:\, /
	private String description; // ex: Fixed drive, Local Disk
	private String type; // ex: NTFS, ext4
	private String uuid; //
	private long totalSpace;
	private long totalInodes;

}
