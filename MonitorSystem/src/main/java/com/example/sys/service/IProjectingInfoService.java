package com.example.sys.service;

import com.example.sys.entity.ProjectingInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 项目和用户中间表 服务类
 * </p>
 *
 * @author CXK
 * @since 2023-07-23
 */
public interface IProjectingInfoService extends IService<ProjectingInfo> {

	//根据ID查询在研项目信息
	ProjectingInfo getProjectingInfoById(Integer id);

	//新增在研项目
	void addProjectingInfo(ProjectingInfo projectingInfo);

	//根据ID删除在研项目
	void deleteProjectingInfoById(Integer id);

	//修改在研项目信息
	void updateProjectingInfo(ProjectingInfo projectingInfo);
}
