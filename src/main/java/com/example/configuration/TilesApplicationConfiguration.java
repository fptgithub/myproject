package com.example.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

@Configuration
public class TilesApplicationConfiguration {

	@Bean
	public TilesConfigurer tilesConfig() {
		TilesConfigurer tc = new TilesConfigurer();
		tc.setDefinitions("/tiles.xml");
		tc.setCheckRefresh(true);
		return tc;
	}
	
	@Bean
	public ViewResolver getViewResolver() {
		UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
		viewResolver.setViewClass(TilesView.class);
		return viewResolver;
	}
}
