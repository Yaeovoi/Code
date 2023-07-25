package com.lantu.sys.service;

import com.lantu.sys.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author baomidou
 * @since 2023-06-20
 */
public interface IUserService extends IService<User> {

	//用户登录验证
	Map<String, Object> login(User user);

	//根据token获取用户信息，redis
	Map<String, Object> getUserInfo(String token);

	//注销用户登录
	void logout(String token);

	//新增用户
	void addUser(User user);

	//根据Id查询用户
	User getUserById(Integer id);

	//修改用户信息
	void updateUser(User user);

	//根据Id删除用户
	void deleteUserById(Integer id);
}
