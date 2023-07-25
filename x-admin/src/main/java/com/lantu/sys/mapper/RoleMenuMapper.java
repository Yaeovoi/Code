package com.lantu.sys.mapper;

import com.lantu.sys.entity.RoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author baomidou
 * @since 2023-06-20
 */
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

	public List<Integer> getMenuIdListByRoleId(Integer roleId);
}
