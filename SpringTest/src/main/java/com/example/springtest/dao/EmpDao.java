package com.example.springtest.dao;

import com.example.springtest.pojo.Emp;

import java.util.List;

public interface EmpDao {
	//获取员工列表数据
	public List<Emp> listEmp();
}