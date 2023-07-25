package com.lantu.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Mybatis拦截器
@Configuration
public class MpConfig {

	//使用MyBatis-Plus框架进行数据库查询时，
	// 该拦截器会拦截相应的查询请求，
	// 并在查询过程中加入分页功能，
	// 以便进行结果的分页展示
	@Bean
	public MybatisPlusInterceptor mybatisPlusInterceptor(){

		//MybatisPlusInterceptor是MyBatis-Plus框架提供的拦截器，用于拦截和处理MyBatis的执行过程
		MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();

		//PaginationInnerInterceptor是MyBatis-Plus提供的分页插件，用于实现数据库的分页查询功能
		interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));

		return interceptor;
	}
}
