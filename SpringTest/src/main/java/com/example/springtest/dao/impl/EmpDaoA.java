package com.example.springtest.dao.impl;

import com.example.springtest.dao.EmpDao;
import com.example.springtest.pojo.Emp;
import com.example.springtest.utils.XmlParserUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository //将当前对象交给IOC容器管理,成为IOC容器的bean
public class EmpDaoA implements EmpDao {
	@Override
	public List<Emp> listEmp() {
		//1. 加载并解析emp.xml
		String file = this.getClass().getClassLoader().getResource("emp.xml").getFile();
		System.out.println(file);
		List<Emp> empList = XmlParserUtils.parse(file, Emp.class);
		return empList;
	}
}
