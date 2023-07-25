package com.example.sys.service;

import com.example.sys.entity.CleanInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CXK
 * @since 2023-07-23
 */
public interface ICleanInfoService extends IService<CleanInfo> {

	//根据ID查询值日信息
	CleanInfo getCleanInfoById(Integer id);

	//新增值日信息
	void addCleanInfo(CleanInfo cleanInfo);

	//根据ID删除值日信息
	void deleteCleanInfoById(Integer id);

	//修改值日信息
	void updateCleanInfo(CleanInfo cleanInfo);
}
