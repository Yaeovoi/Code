package com.example.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

// 配置RedisTemplate，使用Redis进行数据存储
// 配置RedisTemplate，
// 用于在Spring应用程序中与Redis进行数据存储。
// 通过设置序列化器、ObjectMapper的属性和特性，
// 可以实现将Java对象序列化为JSON字符串并存储到Redis中，
// 以及从Redis中取出并反序列化为Java对象的功能。
// 这样可以方便地进行对象的存储和读取操作
@Configuration
public class MyRedisConfig {

	//RedisConnectionFactory是Spring提供的连接工厂，用于创建Redis连接
	@Autowired
	private RedisConnectionFactory factory;

	//RedisTemplate是Spring Framework提供的用于与Redis交互的模板类
	@Bean
	public RedisTemplate redisTemplate(){
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(factory);
		redisTemplate.setKeySerializer(new StringRedisSerializer());

		Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);
		redisTemplate.setValueSerializer(serializer);

		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		om.setTimeZone(TimeZone.getDefault());
		om.configure(MapperFeature.USE_ANNOTATIONS, false);
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		om.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		om.activateDefaultTyping(LaissezFaireSubTypeValidator.instance ,ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
		om.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		serializer.setObjectMapper(om);

		return redisTemplate;
	}
}

