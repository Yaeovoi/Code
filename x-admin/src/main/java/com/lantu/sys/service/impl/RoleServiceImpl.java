package com.lantu.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lantu.sys.entity.Role;
import com.lantu.sys.entity.RoleMenu;
import com.lantu.sys.mapper.RoleMapper;
import com.lantu.sys.mapper.RoleMenuMapper;
import com.lantu.sys.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import kotlin.jvm.internal.Lambda;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2023-06-20
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

	@Resource
	private RoleMenuMapper roleMenuMapper;

	//新增角色
	@Override
	@Transactional
	public void addRole(Role role) {
		//写入角色表
		this.baseMapper.insert(role);
		//写入角色菜单关系表
		if( null != role.getMenuIdList() ) {//判断角色对应的菜单是否为空
			for (Integer menuId : role.getMenuIdList()) {
				//将角色与菜单之间的关系写入角色菜单关系表中
				roleMenuMapper.insert(new RoleMenu(null, role.getRoleId(), menuId));
			}
		}
	}

	//根据ID查询角色信息及权限
	@Override
	public Role getRoleById(Integer id) {

		//根据传入的角色ID获取对应的角色信息
		Role role = this.baseMapper.selectById(id);

		//根据传入的角色ID获取与角色相关联的权限ID列表
		List<Integer> menuIdList = roleMenuMapper.getMenuIdListByRoleId(id);

		//将获取到的权限ID列表设置到 role 对象中的 menuIdList 属性上
		role.setMenuIdList(menuIdList);
		return role;
	}

	//修改角色信息及权限
	@Override
	@Transactional
	public void updateRole(Role role) {
		//修改角色表
		this.baseMapper.updateById(role);
		//删除原有权限
		LambdaQueryWrapper<RoleMenu> wrapper = new LambdaQueryWrapper<>();
		//设置查询条件为角色ID等于传入的 role 对象的角色ID
		wrapper.eq(RoleMenu::getRoleId,role.getRoleId());
		roleMenuMapper.delete(wrapper);
		//新增权限
		if( null != role.getMenuIdList() ) {
			for (Integer menuId : role.getMenuIdList()) {
				roleMenuMapper.insert(new RoleMenu(null, role.getRoleId(), menuId));
			}
		}
	}

	//根据ID查询角色信息及权限
	@Override
	public void deleteRoleById(Integer id) {
		//删除对应的角色记录
		this.baseMapper.deleteById(id);

		//删除权限
		//设置查询条件为角色ID等于传入的id
		LambdaQueryWrapper<RoleMenu> wrapper = new LambdaQueryWrapper<>();
		wrapper.eq(RoleMenu::getRoleId,id);

		//根据设置的条件 wrapper 删除角色菜单关系表中与角色ID相关联的记录，即删除权限记录
		roleMenuMapper.delete(wrapper);
	}
}
