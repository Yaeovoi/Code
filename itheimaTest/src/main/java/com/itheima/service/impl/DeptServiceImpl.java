package com.itheima.service.impl;

import com.itheima.mapper.DeptMapper;
import com.itheima.pojo.Dept;
import com.itheima.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class DeptServiceImpl implements DeptService {

	@Autowired
	private DeptMapper deptMapper;

	//查询部门全部信息
	@Override
	public List<Dept> list() {
		return deptMapper.list();
	}

	//删除部门信息
	@Override
	public void delete(Integer id) {
		deptMapper.deleteById(id);
	}

	//添加部门信息
	@Override
	public void add(Dept dept) {
		dept.setCreateTime(LocalDateTime.now());
		dept.setUpdateTime(LocalDateTime.now());

		deptMapper.insert(dept);
	}

	//获取部门ID
	@Override
	public Dept getByID(Integer id) {
		return deptMapper.getByID(id);
	}

	//更新部门信息
	@Override
	public void update(Dept dept) {
		dept.setUpdateTime(LocalDateTime.now());
		deptMapper.update(dept);
	}
}
