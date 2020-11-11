package com.koen.quize;

import com.koen.quize.config.CorsFilter;
import com.koen.quize.config.WebConfig;
import com.koen.quize.config.WebSecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
@Order(1)
@SpringBootApplication
public class QuizeApplication implements WebApplicationInitializer {
	private final static String DISPATCHER = "dispatcher";


	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext webApplicationContext = new AnnotationConfigWebApplicationContext();
		webApplicationContext.register(WebConfig.class);
		webApplicationContext.register(WebSecurityConfig.class);
		webApplicationContext.register(CorsFilter.class);
		servletContext.addListener(new ContextLoaderListener(webApplicationContext));
		ServletRegistration.Dynamic servletRegistration = servletContext.addServlet(DISPATCHER, new DispatcherServlet(webApplicationContext));
		servletRegistration.addMapping("/");
		servletRegistration.setLoadOnStartup(1);
	}
	public static void main(String[] args) {
		SpringApplication.run(QuizeApplication.class, args);
	}
}
