package com.itheima.service;

import com.itheima.pojo.Dept;

import java.util.List;

/**
 * 部门管理
 */
public interface DeptService {
	/**
	 * 查询部门全部数据
	 */
	List<Dept> list();

	/**
	 * 删除部门
	 */
	void delete(Integer id);

	/**
	 * 新增部门
	 */
	void add(Dept dept);

	/**
	 * 查询部门ID
	 */
	Dept getByID(Integer id);

	/**
	 * 更新部门信息
	 */
	void update(Dept dept);
}
