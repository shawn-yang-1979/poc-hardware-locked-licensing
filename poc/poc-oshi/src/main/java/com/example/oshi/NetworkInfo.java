package com.example.oshi;

import lombok.Builder;
import lombok.Getter;

/**
 * Exclude DNS server, ipv4 gateway, ipv6 gateway since they are more likely to
 * be changed by IT. Domain name and host name are possible to be changed too,
 * but the probability is not high.
 */
@Builder
@Getter
public class NetworkInfo {

	private String hostName;
	private String domainName;

}
