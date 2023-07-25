package com.lantu.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lantu.common.vo.Result;
import com.lantu.sys.entity.Role;
import com.lantu.sys.entity.User;
import com.lantu.sys.service.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @author baomidou
 * @since 2023-06-20
 */
@Api(tags = {"角色接口列表"})
@RestController
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private IRoleService roleService;

	@ApiOperation("查询所有角色")
	@GetMapping("/list")
	public Result<Map<String,Object>> getRoleList(@RequestParam(value="roleName",required = false) String roleName,
	                                              @RequestParam(value="pageNo") Long pageNo,//当前页码
	                                              @RequestParam(value="pageSize") Long pageSize){//每页记录数

		LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();

		//如果roleName不为空，则生成对应的查询条件
		//查询条件为Role表的roleName字段与传入的roleName进行相等匹配
		wrapper.eq(StringUtils.hasLength(roleName),Role::getRoleName,roleName);

		//按照roleId字段进行降序排序
		wrapper.orderByDesc(Role::getRoleId);

		Page<Role> page = new Page<>(pageNo, pageSize);
		//执行分页查询操作，将查询结果根据wrapper条件进行过滤
		roleService.page(page,wrapper);

		Map<String,Object> data = new HashMap<>();
		//将总记录数page.getTotal()和查询结果page.getRecords()
		//分别放入data对象中的"total"和"rows"键值对中
		data.put("total",page.getTotal());
		data.put("rows",page.getRecords());

		return Result.success(data);
	}

	@ApiOperation("新增角色")
	@PostMapping
	public Result<?> addRole(@RequestBody Role role){//接收前端传来的角色信息role
		roleService.addRole(role);
		return Result.success("新增角色成功！");
	}

	@ApiOperation("修改角色信息及权限")
	@PutMapping
	public Result<?> updateRole(@RequestBody Role role){
		roleService.updateRole(role);
		return Result.success("修改角色成功！");
	}

	@ApiOperation("根据ID查询角色信息及权限")
	@GetMapping("/{id}")
	public Result<Role> getRoleById(@PathVariable("id") Integer id){
		Role role = roleService.getRoleById(id);
		return Result.success(role);
	}

	@ApiOperation("根据ID删除角色信息及权限")
	@DeleteMapping("/{id}")
	public Result<Role> deleteRoleById(@PathVariable("id") Integer id){
		roleService.deleteRoleById(id);
		return Result.success("删除角色成功！");
	}

	@ApiOperation("查询全部角色")
	@GetMapping("/all")
	public Result<List<Role>> getAllRole(){
		List<Role> roleList = roleService.list();
		return Result.success(roleList);
	}
}
