package com.example.oshi;

import lombok.Builder;
import lombok.Getter;

/**
 * Exclude available.
 * 
 * Exclude swapPagesIn, swapPagesOut, swapUsed, swapTotal
 * 
 * @author SHAWN.SH.YANG
 *
 */
@Builder
@Getter
public class GlobalMemoryInfo {
	private long total;
	private long pageSize;
}