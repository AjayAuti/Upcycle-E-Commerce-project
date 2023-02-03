package com.upcycle;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class UpcycleBackendApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(UpcycleBackendApplication.class, args);
	}
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		Path photoUploadDir = Paths.get("./product-images");
		String photoUploadPath = photoUploadDir.toFile().getAbsolutePath();
		
		registry.addResourceHandler("/product-images/**").addResourceLocations("file:/"+photoUploadPath+"/");
		
	}

}
