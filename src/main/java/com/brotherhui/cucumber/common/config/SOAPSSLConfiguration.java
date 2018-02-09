package com.brotherhui.cucumber.common.config;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class SOAPSSLConfiguration {



    @Value("${ssltest.trust-store-password}")
    private String trustStorePassword;
    
    @Value("${ssltest.trust-store}")
    private Resource trustStore;
    
	@Value("${ssltest.key-store-password}")
	private String keyStorePassword;
	
	@Value("${ssltest.key-password}")
	private String keyPassword;
	
	@Value("${ssltest.key-store}")
	private Resource keyStore; 
	
	@Value("${ssltest.host}")
	private String host;
	
	@PostConstruct
	private void init(){
		try {
			System.setProperty("javax.net.ssl.trustStore", trustStore.getURL().getFile());
			System.setProperty("javax.net.ssl.trustStorePassword", trustStorePassword);
			System.setProperty("javax.net.ssl.keyStore", keyStore.getURL().getPath());
			System.setProperty("javax.net.ssl.keyStorePassword", keyStorePassword);
			System.setProperty("javax.net.ssl.keyPassword", keyPassword);
			javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
					new javax.net.ssl.HostnameVerifier() {

						public boolean verify(String hostname,
								javax.net.ssl.SSLSession sslSession) {
							if (hostname.equals(host)) {
								return true;
							}
							return false;
						}
					});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}