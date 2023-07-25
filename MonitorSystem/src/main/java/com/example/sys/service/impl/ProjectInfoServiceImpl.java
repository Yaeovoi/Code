package com.example.sys.service.impl;

import com.example.sys.entity.DeptInfo;
import com.example.sys.entity.ProjectInfo;
import com.example.sys.mapper.ProjectInfoMapper;
import com.example.sys.service.IProjectInfoService;
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
public class ProjectInfoServiceImpl extends ServiceImpl<ProjectInfoMapper, ProjectInfo> implements IProjectInfoService {

	//根据ID查询项目
	@Override
	public ProjectInfo getProjectInfoById(Integer id) {
		ProjectInfo projectInfo = this.baseMapper.selectById(id);
		return projectInfo;
	}

	//新增项目
	@Override
	public void addProjectInfo(ProjectInfo projectInfo) {
		this.baseMapper.insert(projectInfo);
	}

	//根据ID删除项目
	@Override
	public void deleteProjectInfoById(Integer id) {
		this.baseMapper.deleteById(id);
	}

	//修改项目信息
	@Override
	public void updateProjectInfo(ProjectInfo projectInfo) {
		this.baseMapper.updateById(projectInfo);
	}
}
