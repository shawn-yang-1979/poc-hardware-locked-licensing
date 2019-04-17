package com.example.machineinfo.os;

import lombok.Data;

/**
 * Exclude DNS server, ipv4 gateway, ipv6 gateway since they are more likely to
 * be changed by IT. Domain name and host name are possible to be changed too,
 * but the probability is not high.
 */
@Data
public class NetworkInfo {

	private String hostName;
	private String domainName;

}
