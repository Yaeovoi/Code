package com.lantu.sys.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lantu.common.utils.JwtUtil;
import com.lantu.sys.entity.Menu;
import com.lantu.sys.entity.User;
import com.lantu.sys.entity.UserRole;
import com.lantu.sys.mapper.UserMapper;
import com.lantu.sys.mapper.UserRoleMapper;
import com.lantu.sys.service.IMenuService;
import com.lantu.sys.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2023-06-20
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

	@Autowired
	private RedisTemplate redisTemplate;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtUtil jwtUtil;

	@Resource
	private UserRoleMapper userRoleMapper;

	@Autowired
	private IMenuService menuService;

	//用户登录验证
	@Override
	public Map<String, Object> login(User user) {
		//根据用户名和密码查询
		LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
		wrapper.eq(User::getUsername, user.getUsername());
//		wrapper.eq(User::getPassword, user.getPassword());

		//获取到符合条件的用户对象 loginUser
		User loginUser = this.baseMapper.selectOne(wrapper);

		//结果不为空并且密码和传入密码匹配，则生成token，并将用户信息存入redis
		if (loginUser != null && passwordEncoder.matches(user.getPassword(),loginUser.getPassword())) {

			//暂时用UUID， 终极方案JWT
//			String key = "user:" + UUID.randomUUID();

			//存入redis
			//redisTemplate.opsForValue().set(key, loginUser, 30, TimeUnit.MINUTES);

			//将登录用户对象的密码字段设置为 null，避免返回密码到客户端
			loginUser.setPassword(null);

			//创建jwt令牌
			String token = jwtUtil.createToken(loginUser);

			//返回数据
			Map<String, Object> data = new HashMap<>();
			data.put("token", token);
			return data;
		}
		return null;
	}

	@Override
	public Map<String, Object> getUserInfo(String token) {
		//根据token获取用户信息，redis
//		Object obj = redisTemplate.opsForValue().get(token);

		User loginUser = null;

		try {
			loginUser = jwtUtil.parseToken(token, User.class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		if (loginUser != null) {
//			User loginUser = JSON.parseObject(JSON.toJSONString(obj), User.class);
			Map<String, Object> data = new HashMap<>();
			data.put("name", loginUser.getUsername());
			data.put("avatar", loginUser.getAvatar());

			//角色
			List<String> roleList = this.baseMapper.getRoleNameByUserId(loginUser.getId());
			data.put("roles", roleList);

			//权限列表
			List<Menu> menuList = menuService.getMenuListByUserId(loginUser.getId());
			data.put("menuList", menuList);
			return data;
		}
		return null;
	}

	//注销用户登录
	@Override
	public void logout(String token) {
		//redisTemplate.delete(token);
	}

	//新增用户
	@Override
	public void addUser(User user) {
		// 写入用户表
		this.baseMapper.insert(user);
		//写入用户角色表
		//获取到用户角色ID列表
		List<Integer> roleIdList = user.getRoleIdList();
		if(roleIdList != null){
			for(Integer roleId : roleIdList){
				//将每个角色ID和用户ID插入到用户角色表中，即写入用户角色表
				userRoleMapper.insert(new UserRole(null,user.getId(),roleId));
			}
		}
	}

	//根据用户ID获取用户信息，并查询用户角色列表
	@Override
	@Transactional
	public User getUserById(Integer id) {
		User user = this.baseMapper.selectById(id);

		LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<>();
		wrapper.eq(UserRole::getUserId,id);

		//根据设置的条件查询用户角色表中与用户ID相关联的记录，即获取用户的角色信息列表
		List<UserRole> userRoleList = userRoleMapper.selectList(wrapper);

		List<Integer> roleIdList = userRoleList.stream()
												//将每个用户角色对象转换为角色ID
				                               .map(userRole -> {return userRole.getRoleId();})
				                                //将转换后的角色ID收集到一个列表中
											   .collect(Collectors.toList());
		//将角色ID列表设置回用户对象，并返回用户对象
		user.setRoleIdList(roleIdList);
		return user;
	}

	//修改用户信息
	@Override
	@Transactional
	public void updateUser(User user) {
		//更新用户表
		this.baseMapper.updateById(user);
		//清除原有角色
		LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<>();
		//设置查询条件为用户ID等于传入的ID
		wrapper.eq(UserRole::getUserId,user.getId());
		//根据设置的条件删除用户角色表中与用户ID相关联的记录，即清除原有的角色信息
		userRoleMapper.delete(wrapper);
		//设置新的角色
		//获取到新的角色ID列表
		List<Integer> roleIdList = user.getRoleIdList();
		if(roleIdList != null){
			for(Integer roleId : roleIdList){
				//将每个角色ID和用户ID插入到用户角色表中，即设置新的角色信息
				userRoleMapper.insert(new UserRole(null,user.getId(),roleId));
			}
		}
	}

	@Override
	public void deleteUserById(Integer id) {
		this.baseMapper.deleteById(id);
		//清除原有角色
		LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<>();
		wrapper.eq(UserRole::getUserId,id);
		userRoleMapper.delete(wrapper);
	}
}

