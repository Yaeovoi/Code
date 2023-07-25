package com.example.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.vo.Result;
import com.example.sys.entity.UserInfo;
import com.example.sys.service.IUserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author CXK
 * @since 2023-07-23
 */
@Api(tags = {"用户管理接口"})
@RestController
@RequestMapping("/userInfo")
public class UserInfoController {

	@Autowired
	private IUserInfoService userInfoService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@ApiOperation("用户登录")
	@PostMapping("/login")
	public Result<Map<String,Object>> login(@RequestBody UserInfo user){
		Map<String,Object> data = userInfoService.login(user);
		if(data != null){
			return Result.success(data);
		}
		return Result.fail(20002,"用户名或密码错误");
	}

	@ApiOperation("根据token获取用户信息")
	@GetMapping("/info")
	public Result<Map<String,Object>> getUserInfo(@RequestParam("token") String token){
		//根据token获取用户信息，redis
		Map<String,Object> data = userInfoService.getUserInfo(token);
		if(data != null){
			return Result.success(data);
		}
		return Result.fail(20003,"登录信息无效，请重新登录");
	}

	@ApiOperation("注销")
	@PostMapping("/logout")
	//用于从请求头中获取 “X-Token” 的值，并将其赋给方法的参数 token
	public Result<?> logout(@RequestHeader("X-Token") String token, @RequestBody UserInfo user){
		userInfoService.logout(token, user);
		return Result.success();
	}

	@ApiOperation("查询全部用户")
	@GetMapping("/all")
	public Result<List<UserInfo>> getAllUser(){
		List<UserInfo> list = userInfoService.list();
		return Result.success(list,"查询成功");
	}

	@ApiOperation("根据ID查询用户")
	@GetMapping("/{id}")
	public Result<UserInfo> getUserById(@PathVariable("id") Integer id){
		UserInfo user = userInfoService.getUserById(id);
		return Result.success(user);
	}

	@ApiOperation("条件分页查询")
	@GetMapping("/list")
	public Result<Map<String,Object>> getUserList(@RequestParam(value="username",required = false) String username,
	                                              @RequestParam(value="phone",required = false) String phone,
	                                              @RequestParam(value="pageNo") Long pageNo,
	                                              @RequestParam(value="pageSize") Long pageSize){

		LambdaQueryWrapper<UserInfo> wrapper = new LambdaQueryWrapper<>();

		//根据传入的用户名参数，判断是否为空 并且判断用户名是否相等
		wrapper.eq(StringUtils.hasLength(username),UserInfo::getUserName,username);

		//根据传入的电话参数，判断是否为空 并且判断电话是否相等
		wrapper.eq(StringUtils.hasLength(phone),UserInfo::getUserPhone,phone);

		//按用户ID进行降序排序
		wrapper.orderByDesc(UserInfo::getId);

		//进行分页查询用户列表
		Page<UserInfo> page = new Page<>(pageNo, pageSize);
		userInfoService.page(page,wrapper);

		////将总记录数page.getTotal()和查询结果page.getRecords()
		//分别放入data对象中的"total"和"rows"键值对中
		Map<String,Object> data = new HashMap<>();
		data.put("total",page.getTotal());
		data.put("rows",page.getRecords());

		return Result.success(data);
	}

	@ApiOperation("新增用户")
	@PostMapping
	public Result<?> addUser(@RequestBody UserInfo user){
		//调用密码编码器 passwordEncoder 对象的 encode 方法对用户的密码进行加密，
		//然后将加密后的密码设置回用户对象中
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userInfoService.addUser(user);
		return Result.success("新增用户成功！");
	}

	@ApiOperation("根据ID删除用户")
	@DeleteMapping("/{id}")
	public Result<UserInfo> deleteUserById(@PathVariable("id") Integer id){
		userInfoService.deleteUserById(id);
		return Result.success("删除成功！");
	}

	@ApiOperation("修改用户信息")
	@PutMapping
	public Result<?> updateUser(@RequestBody UserInfo user){
		user.setPassword(null);
		userInfoService.updateUser(user);
		return Result.success("修改成功！");
	}
}
