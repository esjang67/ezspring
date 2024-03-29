package com.test.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@ComponentScan(basePackages = {"com.test.controller"})
public class ServletConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// 정적파일 잡아줌? /resources/의 요청을 받으면 /resources/로 작업해라 임.
		registry.addResourceHandler("/resources/**/").addResourceLocations("/resources/");
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		
		// 화면출력 파일 경로 셋팅
		InternalResourceViewResolver bean = new InternalResourceViewResolver();
		bean.setViewClass(JstlView.class);
		bean.setPrefix("/WEB-INF/views/");
		bean.setSuffix(".jsp");
		
		registry.viewResolver(bean);
		
	}
	
	@Bean
	public CommonsMultipartResolver getResolver() throws IOException {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		
		resolver.setDefaultEncoding("utf-8");
		resolver.setMaxUploadSize(10 * 1024 * 1024);
		resolver.setMaxUploadSizePerFile(2 * 1024 * 1024);
		resolver.setUploadTempDir(new FileSystemResource("d:\\upload\\tmp"));
		resolver.setMaxInMemorySize(10 * 1024 * 1024);
		
		return resolver;
	}
	
	
}
