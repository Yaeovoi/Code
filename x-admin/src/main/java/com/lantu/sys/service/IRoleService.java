package com.lantu.sys.service;

import com.lantu.sys.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author baomidou
 * @since 2023-06-20
 */
public interface IRoleService extends IService<Role> {

	//新增角色
	void addRole(Role role);

	//根据ID查询角色信息及权限
	Role getRoleById(Integer id);

	//修改角色信息及权限
	void updateRole(Role role);

	//根据ID删除角色信息及权限
	void deleteRoleById(Integer id);
}
