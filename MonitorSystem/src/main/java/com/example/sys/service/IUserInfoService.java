package com.example.sys.service;

import com.example.sys.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CXK
 * @since 2023-07-23
 */
public interface IUserInfoService extends IService<UserInfo> {

	//根据ID查询用户
	UserInfo getUserById(Integer id);

	//新增用户
	void addUser(UserInfo user);

	//根据ID删除用户
	void deleteUserById(Integer id);

	//更新用户信息
	void updateUser(UserInfo user);

	//用户登录
	Map<String, Object> login(UserInfo user);

	//根据token获取用户信息，redis
	Map<String, Object> getUserInfo(String token);

	//注销登录
	void logout(String token, UserInfo user);
}
