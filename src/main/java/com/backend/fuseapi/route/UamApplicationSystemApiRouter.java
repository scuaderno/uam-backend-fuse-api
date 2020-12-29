package com.backend.fuseapi.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http4.HttpComponent;
import org.apache.camel.util.jsse.KeyManagersParameters;
import org.apache.camel.util.jsse.KeyStoreParameters;
import org.apache.camel.util.jsse.SSLContextParameters;
import org.apache.camel.util.jsse.TrustManagersParameters;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UamApplicationSystemApiRouter extends RouteBuilder {

	
	@Value("${mulesoft.uam-app}")
    private String muleUamApp;

	@Value("${mulesoft.proxy}")
	private String mulesoftProxy;
	
	@Value("${appProxy.ip}")
	private String proxyServerIp;

	@Value("${appProxy.port}")
	private String proxyServerPort;

	@Value("${ssl.keystore.path}")
	private String keystorePath;

	@Value("${ssl.keystore.password}")
	private String keystorePass;
	
	@Override
	public void configure() throws Exception {
		HttpComponent httpComponent = getContext().getComponent("https4", HttpComponent.class);
		httpComponent.setSslContextParameters(muleSslContextParameters());
		
		// Call Mule cloudhub REST API to getAllApplication details
		from("direct:uam-app-mock").routeId("direct-uam-app-mock")
		.setHeader("Accept", constant("application/json"))
		.setHeader("Host", constant(mulesoftProxy))
		.to("https4:" + muleUamApp
//				+ "?proxyAuthHost=" + proxyServerIp
//				+ "&proxyAuthPort=" + proxyServerPort
				+ "?bridgeEndpoint=true"
				+ "&throwExceptionOnFailure=false"
				+ "&connectTimeout=30000"
//				+ "&sslContextParameters=#mySslContextParameters"
				+ "&sslTruststoreLocation=classpath:truststores.dev.p12" 
				+ "&sslTruststorePassword=" + keystorePass
				+ "&securityProtocol=SSL" 
		)
		.convertBodyTo(String.class)
		.log("${body}")
		//.process(customerProcessor)
		.marshal().json()
		.end();
		
	}
	
	private SSLContextParameters muleSslContextParameters() {

		KeyStoreParameters store = new KeyStoreParameters();
		store.setResource(keystorePath);
		store.setPassword(keystorePass);

		KeyManagersParameters key = new KeyManagersParameters();
		key.setKeyPassword(keystorePass);
		key.setKeyStore(store);

		TrustManagersParameters trust = new TrustManagersParameters();
		trust.setKeyStore(store);

		SSLContextParameters parameters = new SSLContextParameters();
		parameters.setTrustManagers(trust);
		parameters.setKeyManagers(key);

		return parameters;
	}
}
