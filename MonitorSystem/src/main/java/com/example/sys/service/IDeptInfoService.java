package com.example.sys.service;

import com.example.sys.entity.DeptInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CXK
 * @since 2023-07-23
 */
public interface IDeptInfoService extends IService<DeptInfo> {

	//新增部门
	void addDeptInfo(DeptInfo deptInfo);

	//删除部门
	void deleteDeptById(Integer id);

	//修改部门
	void updateDept(DeptInfo deptInfo);

	//根据ID查询部门
	DeptInfo getDeptById(Integer id);
}
