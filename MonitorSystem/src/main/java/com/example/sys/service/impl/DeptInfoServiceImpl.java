package com.example.sys.service.impl;

import com.example.sys.entity.DeptInfo;
import com.example.sys.mapper.DeptInfoMapper;
import com.example.sys.service.IDeptInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author CXK
 * @since 2023-07-23
 */
@Service
public class DeptInfoServiceImpl extends ServiceImpl<DeptInfoMapper, DeptInfo> implements IDeptInfoService {

	//新增部门
	@Override
	public void addDeptInfo(DeptInfo deptInfo) {
		this.baseMapper.insert(deptInfo);
	}

	//删除部门
	@Override
	public void deleteDeptById(Integer id) {
		this.baseMapper.deleteById(id);
	}

	//修改部门
	@Override
	public void updateDept(DeptInfo deptInfo) {
		this.baseMapper.updateById(deptInfo);
	}

	//根据ID查询部门
	@Override
	public DeptInfo getDeptById(Integer id) {
		DeptInfo dept = this.baseMapper.selectById(id);
		return dept;
	}
}
