package com.example.weather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.result.view.ViewResolver;
import org.springframework.web.reactive.result.view.script.ScriptTemplateConfigurer;
import org.springframework.web.reactive.result.view.script.ScriptTemplateViewResolver;

@SpringBootApplication
@EnableConfigurationProperties(WeatherAppProperties.class)
@EnableCaching(proxyTargetClass = true)
public class WeatherApp {

	public static void main(String[] args) {
		SpringApplication.run(WeatherApp.class, args);
	}

	@Bean
    public ScriptTemplateConfigurer configurer() {
        ScriptTemplateConfigurer configurer = new ScriptTemplateConfigurer();
        configurer.setEngineName("nashorn");
        configurer.setScripts("/META-INF/resources/webjars/mustachejs/2.2.1/mustache.min.js", "/static/js/render.js");
        configurer.setRenderFunction("render");
        return configurer;
    }

    @Bean
    public ViewResolver viewResolver() {
        ScriptTemplateViewResolver viewResolver = new ScriptTemplateViewResolver();
        viewResolver.setPrefix("/templates/");
        viewResolver.setSuffix(".html");
        return viewResolver;
    }
    
}
