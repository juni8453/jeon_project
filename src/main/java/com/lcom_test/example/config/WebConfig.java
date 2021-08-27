package com.lcom_test.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {	
		// CORS 설정 클래스
	
	   @Override
	    public void addCorsMappings(CorsRegistry registry) {
	        registry.addMapping("/**")
	        // 경로의 모든 하위 경로(디렉토리) 매핑
	            .allowedOrigins("http://localhost:8080") 
	            // vue 서버의 도메인
	            .allowedMethods("*")
	            // GET, POST 등 허용할 HTTP method 지정 
	            // * > 모든 메서드 허용
	        	.exposedHeaders("jwt-token");
	        	// Token을 Response header를 통해 사용자에게 전달
	    }
}