package com.github.wxpay.sdk;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class MyConfig extends WXPayConfig {
	
	private byte[] certData;
	
	private IWXPayDomain wxDomain;

	public MyConfig() throws Exception {
		String certPath = "E://cer//apiclient_cert.p12";
        File file = new File(certPath);
        InputStream certStream = new FileInputStream(file);
        this.certData = new byte[(int) file.length()];
        certStream.read(this.certData);
        certStream.close();
        wxDomain = new WXPayDomain();
	}

	@Override
	public String getAppID() {
		return "wx8c025f88b3f63c44";
	}

	@Override
	String getMchID() {
		return "1508748871";
	}

	@Override
	public String getKey() {
		return "0CW3HwsvrclsF2HPGFB6VLY2YGBMhyJ9";
	}

	@Override
	InputStream getCertStream() {
		ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        return certBis;
	}

	@Override
	IWXPayDomain getWXPayDomain() {
		return wxDomain;
	}

}
