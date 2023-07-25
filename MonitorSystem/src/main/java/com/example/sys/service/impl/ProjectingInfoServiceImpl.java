package com.example.sys.service.impl;

import com.example.sys.entity.ProjectInfo;
import com.example.sys.entity.ProjectingInfo;
import com.example.sys.mapper.ProjectingInfoMapper;
import com.example.sys.service.IProjectingInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 项目和用户中间表 服务实现类
 * </p>
 *
 * @author CXK
 * @since 2023-07-23
 */
@Service
public class ProjectingInfoServiceImpl extends ServiceImpl<ProjectingInfoMapper, ProjectingInfo> implements IProjectingInfoService {

	//根据ID查询在研项目信息
	@Override
	public ProjectingInfo getProjectingInfoById(Integer id) {
		ProjectingInfo projectingInfo = this.baseMapper.selectById(id);
		return projectingInfo;
	}

	//新增在研项目
	@Override
	public void addProjectingInfo(ProjectingInfo projectingInfo) {
		this.baseMapper.insert(projectingInfo);
	}

	//根据ID删除在研项目
	@Override
	public void deleteProjectingInfoById(Integer id) {
		this.baseMapper.deleteById(id);
	}

	//修改在研项目信息
	@Override
	public void updateProjectingInfo(ProjectingInfo projectingInfo) {
		this.baseMapper.updateById(projectingInfo);
	}
}
