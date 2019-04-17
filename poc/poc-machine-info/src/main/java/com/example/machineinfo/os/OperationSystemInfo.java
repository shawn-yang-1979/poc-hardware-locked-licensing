package com.example.machineinfo.os;

import lombok.Data;

/**
 * I exclude version, ex: 10 build 14393, since the it will change after update.
 * 
 * @author SHAWN.SH.YANG
 *
 */
@Data
public class OperationSystemInfo {

	private String family;// ext: Windows
	private String manufacturer; // ex: Microsoft
	private int bitness; // ext: 64
}
