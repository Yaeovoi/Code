package com.example.sys.service;

import com.example.sys.entity.LevelRequest;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CXK
 * @since 2023-07-23
 */
public interface ILevelRequestService extends IService<LevelRequest> {

	//根据ID查询请假信息
	LevelRequest getLevelRequestById(Integer id);

	//新增请假申请
	void addLevelRequest(LevelRequest levelRequest);

	//根据ID删除请假申请
	void deleteLevelRequestById(Integer id);

	//修改请假申请
	void updateLevelRequest(LevelRequest levelRequest);
}
