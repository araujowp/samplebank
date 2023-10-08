package com.araujowp.samplebank.infra;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

	@Bean 
	public RestTemplate restTenplate() {
		return new RestTemplate(clientHttpRequestFactory());
	}
	
    @Bean
    public ClientHttpRequestFactory clientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        
        int timeoutMilliseconds = 2000;
        
        factory.setConnectTimeout(timeoutMilliseconds);
        factory.setReadTimeout(timeoutMilliseconds);
        
        return factory;
    }
	
}
