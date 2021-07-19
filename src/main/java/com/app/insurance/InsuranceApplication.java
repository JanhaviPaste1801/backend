package com.app.insurance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;

@SpringBootApplication
public class InsuranceApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(InsuranceApplication.class, args);
	}
	
	@Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
 @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	       return application.sources(InsuranceApplication.class);
	    }

}
