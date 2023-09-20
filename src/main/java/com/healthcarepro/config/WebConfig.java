package com.healthcarepro.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")  // Allow URL patterns.
				.allowedOrigins("*")  // The origin you're calling from (change this to your actual frontend URL or use "*" for all origins - not recommended for production).
				.allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD")  // Allowed request methods.
				.allowedHeaders("Content-Type", "Authorization")  // Allowed request headers.
				.allowCredentials(true);  // if needed
	}
}
