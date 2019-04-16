package com.example.oshi;

import lombok.Builder;
import lombok.Getter;

/**
 * Exclude speed, it will change to 0 when wire unpluged.
 * 
 * @return
 */
@Getter
@Builder
public class NetworkIfInfo {
	private String name;// ex: eth2
	private String displayName;// ex: Intel(R) Ethernet Connection (3) I218-LM
	private int mtu;// ex: 1500
	private String macAddr; // ex: 70:5a:0f:cf:fd:0d
}
