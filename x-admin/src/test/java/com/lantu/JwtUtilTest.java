package com.lantu;

import com.lantu.common.utils.JwtUtil;
import com.lantu.sys.entity.User;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JwtUtilTest {
	@Autowired
	private JwtUtil jwtUtil;

	//创建一个JWT令牌token
	@Test
	public void testCreateJwt() {
		User user = new User();
		user.setUsername("zhangsan");
		user.setPhone("12399988877");
		String token = jwtUtil.createToken(user);
		System.out.println(token);
	}

	//解析令牌token获取里面的信息
	@Test
	public void testParseJwt() {
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI3MzEyMzcxOS1hZGM3LTQ0NTAtODRmNy0xYTE0MTU5YmEyODUiLCJzdWIiOiJ7XCJwaG9uZVwiOlwiMTIzOTk5ODg4NzdcIixcInVzZXJuYW1lXCI6XCJ6aGFuZ3NhblwifSIsImlzcyI6InN5c3RlbSIsImlhdCI6MTY4Nzg3NDk2MywiZXhwIjoxNjg3ODc2NzYzfQ.GimsvsAeP24NM8vo2gF6ahCzvNbQ1TSEXUXtwc89HLE";
		Claims claims = jwtUtil.parseToken(token);
		System.out.println(claims);
	}

	//将解析的信息封装成User对象
	@Test
	public void testParseJwt2() {
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI3MzEyMzcxOS1hZGM3LTQ0NTAtODRmNy0xYTE0MTU5YmEyODUiLCJzdWIiOiJ7XCJwaG9uZVwiOlwiMTIzOTk5ODg4NzdcIixcInVzZXJuYW1lXCI6XCJ6aGFuZ3NhblwifSIsImlzcyI6InN5c3RlbSIsImlhdCI6MTY4Nzg3NDk2MywiZXhwIjoxNjg3ODc2NzYzfQ.GimsvsAeP24NM8vo2gF6ahCzvNbQ1TSEXUXtwc89HLE";
		User user = jwtUtil.parseToken(token, User.class);
		System.out.println(user);
	}
}
