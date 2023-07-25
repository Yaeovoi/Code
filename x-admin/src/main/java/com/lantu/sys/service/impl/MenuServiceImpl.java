package com.lantu.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lantu.sys.entity.Menu;
import com.lantu.sys.mapper.MenuMapper;
import com.lantu.sys.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

	@Override
	public List<Menu> getAllMenu() {
		//一级菜单
		//创建了一个LambdaQueryWrapper对象wrapper，用于构建查询条件
		LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();

		//通过eq方法指定查询条件为parentId等于0，即一级菜单
		wrapper.eq(Menu::getParentId,0);

		//执行查询操作，返回符合条件的菜单列表
		List<Menu> menuList = this.list(wrapper);

		//填充子菜单
		setMenuChildren(menuList);
		return menuList;
	}

	//递归地填充菜单的子菜单列表
	//根据每个菜单的menuId作为parentId进行查询，
	//并将查询结果作为子菜单列表设置到对应的菜单对象中。
	//通过递归调用，可以不断地获取深层次的子菜单列表，从而构建出完整的菜单层级结构
	private void setMenuChildren(List<Menu> menuList) {
		if(menuList != null){
			//循环遍历menuList中的每个菜单对象
			for(Menu menu : menuList){
				LambdaQueryWrapper<Menu> subWrapper = new LambdaQueryWrapper<>();

				//子菜单的parentId与当前菜单的menuId相等
				subWrapper.eq(Menu::getParentId,menu.getMenuId());

				List<Menu> subMenuList = this.list(subWrapper);
				menu.setChildren(subMenuList);
				// 递归
				setMenuChildren(subMenuList);
			}
		}
	}

	//根据用户ID获取该用户的菜单列表 -> 动态路由
	@Override
	public List<Menu> getMenuListByUserId(Integer userId) {
		//一级菜单
		//通过用户ID和父级菜单ID为0进行查询
		List<Menu> menuList = this.baseMapper.getMenuListByUserId(userId, 0);

		//子菜单
		setMenuChildrenByUserId(userId, menuList);
		return menuList;
	}

	private void setMenuChildrenByUserId(Integer userId, List<Menu> menuList) {
		if(menuList != null){
			for(Menu menu : menuList){
				//通过用户ID和当前菜单的menuId进行查询，返回当前菜单的子菜单列表
				List<Menu> subMenuList = this.baseMapper.getMenuListByUserId(userId, menu.getMenuId());
				menu.setChildren(subMenuList);
				//递归
				setMenuChildrenByUserId(userId, subMenuList);
			}
		}
	}
}
