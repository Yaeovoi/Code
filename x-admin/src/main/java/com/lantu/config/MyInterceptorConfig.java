package com.lantu.config;

import com.lantu.interceptor.JwtValidateInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//验证和解析JWT令牌
//配置Spring MVC的拦截器
//该配置类的作用是配置拦截器，并指定拦截的路径和排除的路径
@Configuration
public class MyInterceptorConfig implements WebMvcConfigurer {

	@Autowired
	private JwtValidateInterceptor jwtValidateInterceptor;

	@Override
	//进行拦截器的配置
	public void addInterceptors(InterceptorRegistry registry) {

		//传入jwtValidateInterceptor对象，将JwtValidateInterceptor注册到拦截器注册表中
		//验证JWT令牌 实现对指定请求的权限验证、日志记录等功能
		InterceptorRegistration registration = registry.addInterceptor(jwtValidateInterceptor);

		//指定需要拦截的路径模式，这里使用"/**"表示拦截所有请求
		registration.addPathPatterns("/**")

				//设置不需要拦截的路径模式列表
				.excludePathPatterns(
						"/user/login", //用户登录
						"/user/info",  //获取用户信息
						"/user/logout",  //用户登出
						"/error",  //错误处理
						"/swagger-ui/**",
						"/swagger-resources/**",
						"/v3/**"  //swagger 3.0
				);
	}
}
