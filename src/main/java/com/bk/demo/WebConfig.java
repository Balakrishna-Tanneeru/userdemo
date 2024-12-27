package com.bk.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Apply to all routes
                .allowedOrigins("http://userdemo-app-bucket.s3-website.ap-south-1.amazonaws.com", "http://localhost:3000","http://demo.d16ysgts5dzraa.amplifyapp.com/","http://ec2-13-126-141-189.ap-south-1.compute.amazonaws.com/") // Add both origins
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Allow necessary methods
                .allowedHeaders("*")  // Allow all headers
                .allowCredentials(true)  // Allow credentials (cookies, etc.)
                .maxAge(3600);  // Cache preflight response for 1 hour
    }
}

