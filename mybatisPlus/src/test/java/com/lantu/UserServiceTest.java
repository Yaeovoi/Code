package com.lantu;

import com.lantu.user.entity.User;
import com.lantu.user.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserServiceTest {

	@Autowired
	private IUserService userService;

	@Test
	public void testQuery(){
		List<User> list = userService.list();
		list.forEach(System.out::println);
	}
}
