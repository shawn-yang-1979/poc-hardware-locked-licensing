package com.example.machineinfo.hardware;

import lombok.Data;

/**
 * Exclude available.
 * 
 * Exclude swapPagesIn, swapPagesOut, swapUsed, swapTotal
 * 
 * @author SHAWN.SH.YANG
 *
 */
@Data
public class GlobalMemoryInfo {
	private long total;
	private long pageSize;
}