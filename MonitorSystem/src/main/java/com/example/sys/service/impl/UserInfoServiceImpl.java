package com.example.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.common.utils.JwtUtil;
import com.example.sys.entity.LoginLog;
import com.example.sys.entity.UserInfo;
import com.example.sys.mapper.UserInfoMapper;
import com.example.sys.service.ILoginLogService;
import com.example.sys.service.IUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author CXK
 * @since 2023-07-23
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ILoginLogService loginLogService;

	@Autowired
	private RedisTemplate redisTemplate;

	//根据用户ID查询
	@Override
	public UserInfo getUserById(Integer id) {
		UserInfo user = this.baseMapper.selectById(id);
		return user;
	}

	//新增用户
	@Override
	public void addUser(UserInfo user) {
		user.setCreateTime(LocalDateTime.now());
		this.baseMapper.insert(user);
	}

	//根据ID删除用户
	@Override
	public void deleteUserById(Integer id) {
		this.baseMapper.deleteById(id);
	}

	//更新用户信息
	@Override
	public void updateUser(UserInfo user) {
		this.baseMapper.updateById(user);
	}

	//用户登录
	@Override
	public Map<String, Object> login(UserInfo user) {
		//根据用户名和密码查询
		LambdaQueryWrapper<UserInfo> wrapper = new LambdaQueryWrapper<>();
		wrapper.eq(UserInfo::getUserName, user.getUserName());
        //wrapper.eq(User::getPassword, user.getPassword());

		//获取到符合条件的用户对象 loginUser
		UserInfo loginUser = this.baseMapper.selectOne(wrapper);

		//结果不为空并且密码和传入密码匹配，则生成token，并将用户信息存入redis
		if (loginUser != null && passwordEncoder.matches(user.getPassword(),loginUser.getPassword())) {

			//将登录用户对象的密码字段设置为 null，避免返回密码到客户端
			loginUser.setPassword(null);

			//创建jwt令牌
			String token = jwtUtil.createToken(loginUser);

			//返回数据
			Map<String, Object> data = new HashMap<>();
			data.put("token", token);

			//新增登录记录
			LocalDateTime loginDate = LocalDateTime.now();
			LoginLog loginLog = new LoginLog();
			loginLog.setUserId(loginUser.getId());
			loginLog.setLoginTime(loginDate);

			loginLogService.addLoginLog(loginLog);

			return data;
		}
		return null;
	}

	//根据token获取用户信息，redis
	@Override
	public Map<String, Object> getUserInfo(String token) {
		//根据token获取用户信息，redis
		UserInfo loginUser = null;
		try {
			loginUser = jwtUtil.parseToken(token, UserInfo.class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		if (loginUser != null) {
			Map<String, Object> data = new HashMap<>();
			data.put("name", loginUser.getUserName());
			return data;
		}
		return null;
	}

	//注销登录
	@Override
	public void logout(String token, UserInfo user) {
		//释放token
//		redisTemplate.delete(token);

		user.setPassword(null);
		LocalDateTime logoutDate = LocalDateTime.now();
		LoginLog loginLog = new LoginLog();
		loginLog.setUserId(user.getId());
		loginLog.setLogoutTime(logoutDate);

		loginLogService.addLogout(loginLog);
	}

}
