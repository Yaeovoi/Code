package com.example.sys.service;

import com.example.sys.entity.ProjectInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CXK
 * @since 2023-07-23
 */
public interface IProjectInfoService extends IService<ProjectInfo> {

	//根据ID查询项目
	ProjectInfo getProjectInfoById(Integer id);

	//新增项目
	void addProjectInfo(ProjectInfo projectInfo);

	//根据ID删除项目
	void deleteProjectInfoById(Integer id);

	//修改项目信息
	void updateProjectInfo(ProjectInfo projectInfo);
}
