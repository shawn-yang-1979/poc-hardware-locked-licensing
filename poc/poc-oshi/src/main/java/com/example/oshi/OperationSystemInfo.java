package com.example.oshi;

import lombok.Builder;
import lombok.Getter;

/**
 * I exclude version, ex: 10 build 14393, since the it will change after update.
 * 
 * @author SHAWN.SH.YANG
 *
 */
@Builder
@Getter
public class OperationSystemInfo {

	private String family;// ext: Windows
	private String manufacturer; // ex: Microsoft
	private int bitness; // ext: 64
}
