package com.example.sys.service;

import com.example.sys.entity.ResourceInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CXK
 * @since 2023-07-23
 */
public interface IResourceInfoService extends IService<ResourceInfo> {

	//根据ID查询采购信息
	ResourceInfo getResourceInfoById(Integer id);

	//新增采购信息
	void addResourceInfo(ResourceInfo resourceInfo);

	//根据ID删除采购信息
	void deleteResourceInfoById(Integer id);

	//修改采购信息信息
	void updateResourceInfo(ResourceInfo resourceInfo);
}
