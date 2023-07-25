package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

//解决跨域问题
@Configuration
public class MyCorsConfig {

	//配置跨域资源共享（CORS）过滤器
	//解决浏览器的同源策略限制，实现跨域资源的安全访问
	@Bean
	public CorsFilter corsFilter(){
		//CorsConfiguration是Spring Framework提供的用于配置跨域资源共享（CORS）的类
		CorsConfiguration configuration = new CorsConfiguration();

		//addAllowedOrigin方法添加一个允许的源地址，这里设置为"http://localhost:8888"
		configuration.addAllowedOrigin("http://localhost:8888");

		//setAllowCredentials方法设置是否允许发送Cookie信息，设为true表示允许
		configuration.setAllowCredentials(true);

		//addAllowedMethod方法设置允许的HTTP请求方法，这里使用通配符"*"允许所有方法
		configuration.addAllowedMethod("*");

		//addAllowedHeader方法设置允许的请求头，这里使用通配符"*"允许所有请求头
		configuration.addAllowedHeader("*");

		//UrlBasedCorsConfigurationSource是Spring Framework提供的基于URL路径的CORS配置源
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();

		//传入路径模式"/**"和之前创建的CorsConfiguration对象configuration，以注册这个CORS配置到指定的路径上
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**",configuration);

		return new CorsFilter(urlBasedCorsConfigurationSource);
	}
}
