package com.lantu.common.utils;

import com.alibaba.fastjson2.JSON;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

//生成和解析JWT令牌的功能
//createToken方法可以根据传入的数据生成一个带有有效期的JWT令牌
//parseToken方法可以解析传入的令牌并获取其中的主体内容或将主体内容解析为指定的Java对象类型
@Component
public class JwtUtil {
	// 有效期
	private static final long JWT_EXPIRE = 30*60*1000L;  //半小时
	// 令牌密钥 用于签名和验证令牌
	private static final String JWT_KEY = "123456";

	//生成JWT令牌
	public  String createToken(Object data){

		// 当前时间
		long currentTime = System.currentTimeMillis();
		// 过期时间
		long expTime = currentTime+JWT_EXPIRE;

		// 构建jwt
		JwtBuilder builder = Jwts.builder()

				//使用UUID.randomUUID()生成一个随机唯一的ID
				.setId(UUID.randomUUID()+"")

				//将传入的data对象转换为JSON字符串并设置为主题
				.setSubject(JSON.toJSONString(data))

				//设置令牌的签发者，这里设置为"system"
				.setIssuer("system")

				//设置令牌的签发时间，使用当前时间
				.setIssuedAt(new Date(currentTime))

				//指定令牌的签名算法和密钥，
				//这里使用HS256算法和通过encodeSecret方法编码后的JWT_KEY密钥
				.signWith(SignatureAlgorithm.HS256, encodeSecret(JWT_KEY))

				//设置令牌的过期时间，根据签发时间和有效期计算得出
				.setExpiration(new Date(expTime));

		//调用builder的compact方法生成最终的JWT令牌
		return builder.compact();
	}

	//对密钥进行编码
	private SecretKey encodeSecret(String key){
		//通过Base64编码将原始密钥key转换为字节数组
		byte[] encode = Base64.getEncoder().encode(key.getBytes());
		SecretKeySpec aes = new SecretKeySpec(encode, 0, encode.length, "AES");
		return  aes;
	}

	//解析JWT令牌并返回Claims对象
	public  Claims parseToken(String token){
		Claims body = Jwts.parser()
				//使用setSigningKey方法设置签名密钥为通过encodeSecret方法编码后的JWT_KEY密钥
				.setSigningKey(encodeSecret(JWT_KEY))
				//对令牌进行签名验证、解析有效期等操作
				.parseClaimsJws(token)
				//获取令牌的主体部分，即Claims对象
				.getBody();
		return body;
	}

	//解析JWT令牌并将主体内容解析为指定的Java对象类型
	public <T> T parseToken(String token,Class<T> clazz){
		Claims body = Jwts.parser()
				.setSigningKey(encodeSecret(JWT_KEY))
				.parseClaimsJws(token)
				.getBody();
		return JSON.parseObject(body.getSubject(),clazz);
	}

}

