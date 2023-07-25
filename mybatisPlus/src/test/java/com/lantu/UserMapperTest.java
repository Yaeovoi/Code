package com.lantu;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lantu.entity.User;
import com.lantu.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class UserMapperTest {

	@Resource
	private UserMapper userMapper;

	@Test
	public void testGetAll(){
		Page<User> page = new Page<>(1,5);
		userMapper.selectPage(page, null);
		System.out.println("总数："+page.getTotal());
		List<User> records = page.getRecords();
		records.forEach(System.out::println);
	}

	//不建议使用，因为如果字段输错并不会有提示
	@Test
	public void testSelect(){
		//查询年龄>20，姓赵，降序
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.gt("age",20);
		wrapper.likeRight("name","赵");
		wrapper.orderByDesc("id");
		List<User> users = userMapper.selectList(wrapper);
		users.forEach(System.out::println);
	}

	//条件构造器
	//建议使用Lambda表达式
	@Test
	public void testSelect1(){
		//查询年龄>20，姓赵，降序
		LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
		wrapper.gt(User::getAge,20);
		wrapper.likeRight(User::getName,"赵");
		wrapper.orderByDesc(User::getId);
		List<User> users = userMapper.selectList(wrapper);
		users.forEach(System.out::println);
	}
}
