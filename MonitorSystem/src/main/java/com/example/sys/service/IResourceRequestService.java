package com.example.sys.service;

import com.example.sys.entity.ResourceRequest;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CXK
 * @since 2023-07-23
 */
public interface IResourceRequestService extends IService<ResourceRequest> {

	//根据ID查询耗材申请信息
	ResourceRequest getResourceRequestById(Integer id);

	//新增耗材申请信息
	void addResourceRequest(ResourceRequest resourceRequest);

	//根据ID删除耗材申请信息
	void deleteResourceRequestById(Integer id);

	//修改耗材申请信息及审核状态
	void updateResourceRequest(ResourceRequest resourceRequest);
}
