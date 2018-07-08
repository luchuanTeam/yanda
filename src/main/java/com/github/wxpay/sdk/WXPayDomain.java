package com.github.wxpay.sdk;

public class WXPayDomain implements IWXPayDomain {

	@Override
	public void report(String domain, long elapsedTimeMillis, Exception ex) {

	}

	@Override
	public DomainInfo getDomain(WXPayConfig config) {
		DomainInfo domainInfo = new DomainInfo(WXPayConstants.DOMAIN_API, true);
		return domainInfo;
	}

}
