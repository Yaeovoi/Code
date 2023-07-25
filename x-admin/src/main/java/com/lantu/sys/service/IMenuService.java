package com.lantu.sys.service;

import com.lantu.sys.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author baomidou
 * @since 2023-06-20
 */
public interface IMenuService extends IService<Menu> {

	//查询所有菜单数据
	List<Menu> getAllMenu();

	//通过Id查询对应菜单
	List<Menu> getMenuListByUserId(Integer userId);
}
